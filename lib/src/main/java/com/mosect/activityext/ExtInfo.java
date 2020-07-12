package com.mosect.activityext;

import android.app.Activity;

public class ExtInfo {

    private Activity activity;
    private Object[] tags;
    ActivityState state = ActivityState.NONE;
    CallbackList callbackList;
    ExtInfo last;
    ExtInfo next;

    ExtInfo(Activity activity, int tagCount) {
        this.activity = activity;
        if (tagCount > 0) {
            this.tags = new Object[tagCount];
        }
    }

    /**
     * 获取activity的状态
     *
     * @return 状态
     */
    public ActivityState getState() {
        return state;
    }

    /**
     * 获取activity
     *
     * @return activity
     */
    public Activity getActivity() {
        return activity;
    }

    /**
     * 获取标识
     *
     * @param id  标识id
     * @param <T> 标识类型
     * @return 标识对象
     */
    @SuppressWarnings("unchecked")
    public <T> T getTag(int id) {
        return (T) tags[id];
    }

    /**
     * 设置标识
     *
     * @param id  标识id
     * @param tag 标识对象
     */
    public void setTag(int id, Object tag) {
        tags[id] = tag;
    }

    /**
     * 为activity添加一个回调
     *
     * @param callback 回调
     */
    public void addCallback(ActivityCallback callback) {
        if (null == callbackList) {
            callbackList = new CallbackList();
        }
        callbackList.addCallback(callback);
    }

    /**
     * 移除activity回调
     *
     * @param callback 回调
     */
    public void removeCallback(ActivityCallback callback) {
        if (null != callbackList) {
            callbackList.removeCallback(callback);
        }
    }

    ExtInfo findInfo(Activity activity) {
        return null;
    }
}
