package com.mosect.activityext;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.view.Window;

import java.util.LinkedList;

/**
 * Activity拓展
 */
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

    private static ActivityExt instance;

    /**
     * 获取单例
     *
     * @return 单例对象
     */
    public static ActivityExt getInstance() {
        if (null == instance) {
            synchronized (ActivityExt.class) {
                if (null == instance) {
                    instance = new ActivityExt();
                }
            }
        }
        return instance;
    }

    private boolean initialed = false;
    private int tagCount;
    private ExtInfoList extInfoList;
    private ExtInfoList extInfoListOnCreate;
    CallbackList callbackList;
    private LinkedList<ActivityTask>[] tasksList;

    private ActivityExt() {
    }

    /**
     * 注册一个Activity的标识
     *
     * @return 标识id
     */
    public int registerTag() {
        return tagCount++;
    }

    /**
     * 初始化，需要在Application.onCreate中执行
     *
     * @param context 上下文
     */
    public void init(Context context) {
        if (initialed) return;
        initialed = true;
        extInfoList = new ExtInfoList();
        extInfoListOnCreate = new ExtInfoList();
        callbackList = new CallbackList();
        //noinspection unchecked
        tasksList = new LinkedList[ActivityState.values().length];
        Application app = (Application) context.getApplicationContext();
        app.registerActivityLifecycleCallbacks(this);
    }

    /**
     * 获取activity拓展信息的数量
     *
     * @return activity拓展信息的数量
     */
    public int getInfoSize() {
        return extInfoList.size();
    }

    /**
     * 获取Activity的拓展信息
     *
     * @param index 下标
     * @return 拓展信息
     */
    public ExtInfo getInfo(int index) {
        return extInfoList.get(index);
    }

    /**
     * 获取Activity的拓展信息
     *
     * @param activity 上下文
     * @return 拓展信息
     */
    public ExtInfo getInfo(Activity activity) {
        return extInfoList.find(activity);
    }

    /**
     * 获取栈顶的Activity拓展信息
     *
     * @return 拓展信息
     */
    public ExtInfo topInfo() {
        return extInfoList.top();
    }

    /**
     * 在onCreate方法中，获取Activity拓展信息
     *
     * @param activity 上下文
     * @return 拓展信息
     */
    public ExtInfo getInfoOnCreate(Activity activity) {
        ExtInfo extInfo = extInfoListOnCreate.find(activity);
        if (null == extInfo) {
            extInfo = new ExtInfo(activity, tagCount);
            extInfoListOnCreate.add(extInfo);
        }
        return extInfo;
    }

    /**
     * 增加一个activity回调
     *
     * @param callback 回调
     */
    public void addCallback(ActivityCallback callback) {
        callbackList.addCallback(callback);
    }

    /**
     * 移除一个activity回调
     *
     * @param callback 回调
     */
    public void removeCallback(ActivityCallback callback) {
        callbackList.removeCallback(callback);
    }

    /**
     * 在指定状态执行一个activity任务
     *
     * @param state 状态
     * @param task  任务
     * @param delay 是否可以延迟执行
     * @return {@link #RUN_NOW RUN_NOW} {@link #RUN_DELAY RUN_DELAY} {@link #RUN_CANCELED RUN_CANCELED}
     */
    public int run(ActivityState state, ActivityTask task, boolean delay) {
        if (null == task) {
            throw new IllegalArgumentException("task is null.");
        }
        if (null == state) {
            throw new IllegalArgumentException("state is null");
        }
        if (state == ActivityState.NONE) {
            throw new IllegalArgumentException("unsupported state:" + state);
        }
        ExtInfo extInfo = extInfoList.top();
        ActivityState activityState = null == extInfo ? ActivityState.NONE : extInfo.getState();
        if (activityState == state) {
            task.onRun(extInfo);
            return RUN_NOW;
        } else if (delay) {
            LinkedList<ActivityTask> tasks = tasksList[state.ordinal()];
            if (null == tasks) {
                tasks = new LinkedList<>();
                tasksList[state.ordinal()] = tasks;
            }
            tasks.add(task);
            return RUN_DELAY;
        }
        return RUN_CANCELED;
    }

    private ExtInfo createInfo(Activity activity) {
        ExtInfo extInfo = extInfoListOnCreate.remove(activity);
        if (null == extInfo) {
            extInfo = new ExtInfo(activity, tagCount);
            Window.Callback target = activity.getWindow().getCallback();
            ExtWindowCallback extWindowCallback = new ExtWindowCallback(target, extInfo);
            activity.getWindow().setCallback(extWindowCallback);
        }
        extInfoList.add(extInfo);
        return extInfo;
    }

    private ExtInfo getInfoAfterOnCreate(Activity activity) {
        ExtInfo extInfo = extInfoList.moveToTop(activity);
        if (null == extInfo) {
            extInfo = createInfo(activity);
        }
        return extInfo;
    }

    private void handleState(ActivityState state, ExtInfo extInfo) {
        extInfo.state = state;
        LinkedList<ActivityTask> tasks = tasksList[state.ordinal()];
        if (null != tasks) {
            while (tasks.size() > 0) {
                tasks.removeFirst().onRun(extInfo);
            }
        }
    }

    @Override
    public void onActivityCreated(Activity activity, Bundle bundle) {
        ExtInfo extInfo = createInfo(activity);
        if (null != extInfo.callbackList) {
            extInfo.callbackList.onCreated(extInfo);
        }
        callbackList.onCreated(extInfo);
        handleState(ActivityState.CREATED, extInfo);
    }

    @Override
    public void onActivityStarted(Activity activity) {
        ExtInfo extInfo = getInfoAfterOnCreate(activity);
        if (null != extInfo.callbackList) {
            extInfo.callbackList.onStarted(extInfo);
        }
        callbackList.onStarted(extInfo);
        handleState(ActivityState.STARTED, extInfo);
    }

    @Override
    public void onActivityResumed(Activity activity) {
        ExtInfo extInfo = getInfoAfterOnCreate(activity);
        if (null != extInfo.callbackList) {
            extInfo.callbackList.onResumed(extInfo);
        }
        callbackList.onResumed(extInfo);
        handleState(ActivityState.RESUMED, extInfo);
    }

    @Override
    public void onActivityPaused(Activity activity) {
        ExtInfo extInfo = getInfo(activity);
        if (null != extInfo.callbackList) {
            extInfo.callbackList.onPaused(extInfo);
        }
        callbackList.onPaused(extInfo);
        handleState(ActivityState.PAUSED, extInfo);
    }

    @Override
    public void onActivityStopped(Activity activity) {
        ExtInfo extInfo = getInfo(activity);
        if (null != extInfo.callbackList) {
            extInfo.callbackList.onStopped(extInfo);
        }
        callbackList.onStopped(extInfo);
        handleState(ActivityState.STOPPED, extInfo);
    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        ExtInfo extInfo = getInfo(activity);
        if (null != extInfo.callbackList) {
            extInfo.callbackList.onSaveInstanceState(extInfo);
        }
        callbackList.onSaveInstanceState(extInfo);
    }

    @Override
    public void onActivityDestroyed(Activity activity) {
        ExtInfo extInfo = getInfo(activity);
        if (null != extInfo.callbackList) {
            extInfo.callbackList.onDestroyed(extInfo);
        }
        callbackList.onDestroyed(extInfo);
        handleState(ActivityState.DESTROYED, extInfo);
        // 摧毁的activity，需要移除ExtInfo
        extInfoList.remove(extInfo);
    }
}
