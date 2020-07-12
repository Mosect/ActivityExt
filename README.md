# ActivityExt
对Android Activity进行拓展，可以全局监听Activity生命周期、事件、为Activity添加tag对象等。
ActivityExt为一个单例，提供了以下内容：
```
public class ActivityExt implements Application.ActivityLifecycleCallbacks {

    /**
     * 取消执行
     */
    public static final int RUN_CANCELED = 0;
    /**
     * 立即执行了
     */
    public static final int RUN_NOW = 1;
    /**
     * 延迟执行
     */
    public static final int RUN_DELAY = 2;

    /**
     * 获取单例
     *
     * @return 单例对象
     */
    public static ActivityExt getInstance();

    /**
     * 注册一个Activity的标识
     *
     * @return 标识id
     */
    public int registerTag();

    /**
     * 初始化，需要在Application.onCreate中执行
     *
     * @param context 上下文
     */
    public void init(Context context);

    /**
     * 获取activity拓展信息的数量
     *
     * @return activity拓展信息的数量
     */
    public int getInfoSize();

    /**
     * 获取Activity的拓展信息
     *
     * @param index 下标
     * @return 拓展信息
     */
    public ExtInfo getInfo(int index);

    /**
     * 获取Activity的拓展信息
     *
     * @param activity 上下文
     * @return 拓展信息
     */
    public ExtInfo getInfo(Activity activity);
    
    /**
     * 获取栈顶的Activity拓展信息
     *
     * @return 拓展信息
     */
    public ExtInfo topInfo();

    /**
     * 在onCreate方法中，获取Activity拓展信息
     *
     * @param activity 上下文
     * @return 拓展信息
     */
    public ExtInfo getInfoOnCreate(Activity activity);

    /**
     * 增加一个activity回调
     *
     * @param callback 回调
     */
    public void addCallback(ActivityCallback callback);

    /**
     * 移除一个activity回调
     *
     * @param callback 回调
     */
    public void removeCallback(ActivityCallback callback);

    /**
     * 在指定状态执行一个activity任务
     *
     * @param state 状态
     * @param task  任务
     * @param delay 是否可以延迟执行
     * @return {@link #RUN_NOW RUN_NOW} {@link #RUN_DELAY RUN_DELAY} {@link #RUN_CANCELED RUN_CANCELED}
     */
    public int run(ActivityState state, ActivityTask task, boolean delay);
}
```
# 初始化
需要在Application.onCreate中进行初始化：
```
    public void onCreate() {
        super.onCreate();
        // 对拓展进行初始化
        ActivityExt.getInstance().init(this);
        // 初始化后可以监听回调
        ActivityExt.getInstance().addCallback(new ActivityCallback() { ... });
        // 可以换成SimpleActivityCallback
        ActivityExt.getInstance().addCallback(new SimpleActivityCallback() { ... });
        // 如果需要为activity添加一个tag对象，需要在此注册所需tag，注册后所有activity都包含此tag
        // 注册返回的是tag的id，可以根据这个id对某个activity进行get或者set tag
        int myTag1 = ActivityExt.getInstance().registerTag();
        int myTag2 = ActivityExt.getInstance().registerTag();
    }
```
# 回调-ActivityCallback
以下是可以监听到的回调：
```
public interface ActivityCallback {

    void onCreated(ExtInfo extInfo);

    void onStarted(ExtInfo extInfo);

    void onResumed(ExtInfo extInfo);

    void onPaused(ExtInfo extInfo);

    void onStopped(ExtInfo extInfo);

    void onSaveInstanceState(ExtInfo extInfo);

    void onDestroyed(ExtInfo extInfo);

    boolean beforeDispatchKeyEvent(ExtInfo extInfo, KeyEvent keyEvent);

    boolean afterDispatchKeyEvent(ExtInfo extInfo, KeyEvent keyEvent);

    boolean beforeDispatchKeyShortcutEvent(ExtInfo extInfo, KeyEvent keyEvent);

    boolean afterDispatchKeyShortcutEvent(ExtInfo extInfo, KeyEvent keyEvent);

    boolean beforeDispatchTouchEvent(ExtInfo extInfo, MotionEvent motionEvent);

    boolean afterDispatchTouchEvent(ExtInfo extInfo, MotionEvent motionEvent);

    boolean beforeDispatchTrackballEvent(ExtInfo extInfo, MotionEvent motionEvent);

    boolean afterDispatchTrackballEvent(ExtInfo extInfo, MotionEvent motionEvent);

    boolean beforeDispatchGenericMotionEvent(ExtInfo extInfo, MotionEvent motionEvent);

    boolean afterDispatchGenericMotionEvent(ExtInfo extInfo, MotionEvent motionEvent);

    void onWindowFocusChanged(ExtInfo extInfo, boolean hasFocus);
}
```
# 拓展信息-ExtInfo
一个Activity对应一个ExtInfo，ExtInfo提供以下内容：
```
public class ExtInfo {

    /**
     * 获取activity的状态
     *
     * @return 状态
     */
    public ActivityState getState();

    /**
     * 获取activity
     *
     * @return activity
     */
    public Activity getActivity();

    /**
     * 获取标识
     *
     * @param id  标识id
     * @param <T> 标识类型
     * @return 标识对象
     */
    public <T> T getTag(int id);

    /**
     * 设置标识
     *
     * @param id  标识id
     * @param tag 标识对象
     */
    public void setTag(int id, Object tag);

    /**
     * 为activity添加一个回调
     *
     * @param callback 回调
     */
    public void addCallback(ActivityCallback callback);

    /**
     * 移除activity回调
     *
     * @param callback 回调
     */
    public void removeCallback(ActivityCallback callback);
}
```
# 获取ExtInfo
可以根据Activity对象获取到对应的ExtInfo：
```
ExtInfo extInfo = ActivityExt.getInstance().getInfo(MainActivity.this);
```
也可以遍历列表：
```
int count = ActivityExt.getInstance().getInfoSize();
for (int i = 0; i < count; i++) {
    ExtInfo extInfo = ActivityExt.getInstance().getInfo(i);
}
```
获取栈顶ExtInfo：
```
ExtInfo extInfo = ActivityExt.getInstance().topInfo();
```
# 为Activity设置tag对象
首先需要注册tag，在Application.onCreate中：
```
int myTag1 = ActivityExt.getInstance().registerTag();
int myTag2 = ActivityExt.getInstance().registerTag();
```
然后再需要用到的地方：
```
ExtInfo extInfo = ActivityExt.getInstance().getInfo(activity);
extInfo.setTag(myTag1, tagObj1);
Object tagObj2 = extInfo.getTag(myTag2);
```
# 添加Activity回调
全局回调：
```
ActivityExt.getInstance().addCallback(new SimpleActivityCallback() { ... });
```
针对某个页面的回调：
```
ExtInfo extInfo = ActivityExt.getInstance().getInfo(activity);
extInfo.addCallback(new SimpleActivityCallback() { ... });
```
# 再Activity特定状态执行一个任务：
在ActivityExt中，提供了一个方法：
```

/**
 * 在指定状态执行一个activity任务
 *
 * @param state 状态
 * @param task  任务
 * @param delay 是否可以延迟执行
 * @return {@link #RUN_NOW RUN_NOW} {@link #RUN_DELAY RUN_DELAY} {@link #RUN_CANCELED RUN_CANCELED}
 */
public int run(ActivityState state, ActivityTask task, boolean delay);
```
这个方法可以在栈顶的activity指定状态执行一个任务，例如：在onResume后执行：
```
ActivityExt.getInstance().run(ActivityState.RESUMED, new ActivityTask(){ ... }, true);
```
