package com.mosect.example.activityext;

import android.app.Application;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;

import com.mosect.activityext.ActivityCallback;
import com.mosect.activityext.ActivityExt;
import com.mosect.activityext.ExtInfo;

public class MainApplication extends Application {

    private static final String TAG = "ActivityExt.App";

    @Override
    public void onCreate() {
        super.onCreate();
        ActivityExt.getInstance().init(this);
        ActivityExt.getInstance().addCallback(new ActivityCallback() {
            @Override
            public void onCreated(ExtInfo extInfo) {
                Log.d(TAG, "onCreated:" + extInfo.getActivity().toString());
            }

            @Override
            public void onStarted(ExtInfo extInfo) {
                Log.d(TAG, "onStarted:" + extInfo.getActivity().toString());
            }

            @Override
            public void onResumed(ExtInfo extInfo) {
                Log.d(TAG, "onResumed:" + extInfo.getActivity().toString());
            }

            @Override
            public void onPaused(ExtInfo extInfo) {
                Log.d(TAG, "onPaused:" + extInfo.getActivity().toString());
            }

            @Override
            public void onStopped(ExtInfo extInfo) {
                Log.d(TAG, "onStopped:" + extInfo.getActivity().toString());
            }

            @Override
            public void onSaveInstanceState(ExtInfo extInfo) {
                Log.d(TAG, "onSaveInstanceState:" + extInfo.getActivity().toString());
            }

            @Override
            public void onDestroyed(ExtInfo extInfo) {
                Log.d(TAG, "onDestroyed:" + extInfo.getActivity().toString());
            }

            @Override
            public boolean beforeDispatchKeyEvent(ExtInfo extInfo, KeyEvent keyEvent) {
                Log.d(TAG, "beforeDispatchKeyEvent:" + extInfo.getActivity().toString());
                return false;
            }

            @Override
            public boolean afterDispatchKeyEvent(ExtInfo extInfo, KeyEvent keyEvent) {
                Log.d(TAG, "afterDispatchKeyEvent:" + extInfo.getActivity().toString());
                return false;
            }

            @Override
            public boolean beforeDispatchKeyShortcutEvent(ExtInfo extInfo, KeyEvent keyEvent) {
                Log.d(TAG, "beforeDispatchKeyShortcutEvent:" + extInfo.getActivity().toString());
                return false;
            }

            @Override
            public boolean afterDispatchKeyShortcutEvent(ExtInfo extInfo, KeyEvent keyEvent) {
                Log.d(TAG, "afterDispatchKeyShortcutEvent:" + extInfo.getActivity().toString());
                return false;
            }

            @Override
            public boolean beforeDispatchTouchEvent(ExtInfo extInfo, MotionEvent motionEvent) {
                Log.d(TAG, "beforeDispatchTouchEvent:" + extInfo.getActivity().toString());
                return false;
            }

            @Override
            public boolean afterDispatchTouchEvent(ExtInfo extInfo, MotionEvent motionEvent) {
                Log.d(TAG, "afterDispatchTouchEvent:" + extInfo.getActivity().toString());
                return false;
            }

            @Override
            public boolean beforeDispatchTrackballEvent(ExtInfo extInfo, MotionEvent motionEvent) {
                Log.d(TAG, "beforeDispatchTrackballEvent:" + extInfo.getActivity().toString());
                return false;
            }

            @Override
            public boolean afterDispatchTrackballEvent(ExtInfo extInfo, MotionEvent motionEvent) {
                Log.d(TAG, "afterDispatchTrackballEvent:" + extInfo.getActivity().toString());
                return false;
            }

            @Override
            public boolean beforeDispatchGenericMotionEvent(ExtInfo extInfo, MotionEvent motionEvent) {
                Log.d(TAG, "beforeDispatchGenericMotionEvent:" + extInfo.getActivity().toString());
                return false;
            }

            @Override
            public boolean afterDispatchGenericMotionEvent(ExtInfo extInfo, MotionEvent motionEvent) {
                Log.d(TAG, "afterDispatchGenericMotionEvent:" + extInfo.getActivity().toString());
                return false;
            }

            @Override
            public void onWindowFocusChanged(ExtInfo extInfo, boolean hasFocus) {
                Log.d(TAG, "onWindowFocusChanged:" + extInfo.getActivity().toString());
            }
        });
/*        int myTag1 = ActivityExt.getInstance().registerTag();
        int myTag2 = ActivityExt.getInstance().registerTag();*/

/*        ExtInfo extInfo = ActivityExt.getInstance().getInfo(activity);
        extInfo.setTag(myTag1, tagObj1);
        Object tagObj2 = extInfo.getTag(myTag2);*/
    }
}
