package com.mosect.activityext;

import android.view.KeyEvent;
import android.view.MotionEvent;

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
