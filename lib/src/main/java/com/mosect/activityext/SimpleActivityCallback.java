package com.mosect.activityext;

import android.view.KeyEvent;
import android.view.MotionEvent;

public abstract class SimpleActivityCallback implements ActivityCallback {

    @Override
    public void onCreated(ExtInfo extInfo) {

    }

    @Override
    public void onStarted(ExtInfo extInfo) {

    }

    @Override
    public void onResumed(ExtInfo extInfo) {

    }

    @Override
    public void onPaused(ExtInfo extInfo) {

    }

    @Override
    public void onStopped(ExtInfo extInfo) {

    }

    @Override
    public void onSaveInstanceState(ExtInfo extInfo) {

    }

    @Override
    public void onDestroyed(ExtInfo extInfo) {

    }

    @Override
    public boolean beforeDispatchKeyEvent(ExtInfo extInfo, KeyEvent keyEvent) {
        return false;
    }

    @Override
    public boolean afterDispatchKeyEvent(ExtInfo extInfo, KeyEvent keyEvent) {
        return false;
    }

    @Override
    public boolean beforeDispatchKeyShortcutEvent(ExtInfo extInfo, KeyEvent keyEvent) {
        return false;
    }

    @Override
    public boolean afterDispatchKeyShortcutEvent(ExtInfo extInfo, KeyEvent keyEvent) {
        return false;
    }

    @Override
    public boolean beforeDispatchTouchEvent(ExtInfo extInfo, MotionEvent motionEvent) {
        return false;
    }

    @Override
    public boolean afterDispatchTouchEvent(ExtInfo extInfo, MotionEvent motionEvent) {
        return false;
    }

    @Override
    public boolean beforeDispatchTrackballEvent(ExtInfo extInfo, MotionEvent motionEvent) {
        return false;
    }

    @Override
    public boolean afterDispatchTrackballEvent(ExtInfo extInfo, MotionEvent motionEvent) {
        return false;
    }

    @Override
    public boolean beforeDispatchGenericMotionEvent(ExtInfo extInfo, MotionEvent motionEvent) {
        return false;
    }

    @Override
    public boolean afterDispatchGenericMotionEvent(ExtInfo extInfo, MotionEvent motionEvent) {
        return false;
    }

    @Override
    public void onWindowFocusChanged(ExtInfo extInfo, boolean hasFocus) {

    }
}
