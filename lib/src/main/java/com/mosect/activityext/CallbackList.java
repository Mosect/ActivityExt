package com.mosect.activityext;

import android.view.KeyEvent;
import android.view.MotionEvent;

import java.util.LinkedList;

class CallbackList implements ActivityCallback {

    private LinkedList<ActivityCallback> callbacks = new LinkedList<>();
    private int locked;
    private LinkedList<Runnable> delayTasks = new LinkedList<>();

    void addCallback(final ActivityCallback callback) {
        if (null == callback) {
            throw new IllegalArgumentException("callback is null.");
        }
        if (locked > 0) {
            delayTasks.add(new Runnable() {
                @Override
                public void run() {
                    addCallback(callback);
                }
            });
        } else {
            callbacks.add(callback);
        }
    }

    void removeCallback(final ActivityCallback callback) {
        if (null == callback) {
            throw new IllegalArgumentException("callback is null.");
        }
        if (locked > 0) {
            delayTasks.add(new Runnable() {
                @Override
                public void run() {
                    removeCallback(callback);
                }
            });
        } else {
            callbacks.remove(callback);
        }
    }

    private void releaseLock() {
        if (locked > 0) {
            locked--;
            if (locked == 0) {
                while (delayTasks.size() > 0) {
                    delayTasks.removeFirst().run();
                }
            }
        }
    }

    @Override
    public void onCreated(ExtInfo extInfo) {
        locked++;
        for (ActivityCallback callback : callbacks) {
            callback.onCreated(extInfo);
        }
        releaseLock();
    }

    @Override
    public void onStarted(ExtInfo extInfo) {
        locked++;
        for (ActivityCallback callback : callbacks) {
            callback.onStarted(extInfo);
        }
        releaseLock();
    }

    @Override
    public void onResumed(ExtInfo extInfo) {
        locked++;
        for (ActivityCallback callback : callbacks) {
            callback.onResumed(extInfo);
        }
        releaseLock();
    }

    @Override
    public void onPaused(ExtInfo extInfo) {
        locked++;
        for (ActivityCallback callback : callbacks) {
            callback.onPaused(extInfo);
        }
        releaseLock();
    }

    @Override
    public void onStopped(ExtInfo extInfo) {
        locked++;
        for (ActivityCallback callback : callbacks) {
            callback.onStopped(extInfo);
        }
        releaseLock();
    }

    @Override
    public void onSaveInstanceState(ExtInfo extInfo) {
        locked++;
        for (ActivityCallback callback : callbacks) {
            callback.onSaveInstanceState(extInfo);
        }
        releaseLock();
    }

    @Override
    public void onDestroyed(ExtInfo extInfo) {
        locked++;
        for (ActivityCallback callback : callbacks) {
            callback.onDestroyed(extInfo);
        }
        releaseLock();
    }

    @Override
    public boolean beforeDispatchKeyEvent(ExtInfo extInfo, KeyEvent keyEvent) {
        locked++;
        boolean result = false;
        for (ActivityCallback callback : callbacks) {
            result = callback.beforeDispatchKeyEvent(extInfo, keyEvent);
            if (result) break;
        }
        releaseLock();
        return result;
    }

    @Override
    public boolean afterDispatchKeyEvent(ExtInfo extInfo, KeyEvent keyEvent) {
        locked++;
        boolean result = false;
        for (ActivityCallback callback : callbacks) {
            result = callback.afterDispatchKeyEvent(extInfo, keyEvent);
            if (result) break;
        }
        releaseLock();
        return result;
    }

    @Override
    public boolean beforeDispatchKeyShortcutEvent(ExtInfo extInfo, KeyEvent keyEvent) {
        locked++;
        boolean result = false;
        for (ActivityCallback callback : callbacks) {
            result = callback.beforeDispatchKeyShortcutEvent(extInfo, keyEvent);
            if (result) break;
        }
        releaseLock();
        return result;
    }

    @Override
    public boolean afterDispatchKeyShortcutEvent(ExtInfo extInfo, KeyEvent keyEvent) {
        locked++;
        boolean result = false;
        for (ActivityCallback callback : callbacks) {
            result = callback.afterDispatchKeyShortcutEvent(extInfo, keyEvent);
            if (result) break;
        }
        releaseLock();
        return result;
    }

    @Override
    public boolean beforeDispatchTouchEvent(ExtInfo extInfo, MotionEvent motionEvent) {
        locked++;
        boolean result = false;
        for (ActivityCallback callback : callbacks) {
            result = callback.beforeDispatchTouchEvent(extInfo, motionEvent);
            if (result) break;
        }
        releaseLock();
        return result;
    }

    @Override
    public boolean afterDispatchTouchEvent(ExtInfo extInfo, MotionEvent motionEvent) {
        locked++;
        boolean result = false;
        for (ActivityCallback callback : callbacks) {
            result = callback.afterDispatchTouchEvent(extInfo, motionEvent);
            if (result) break;
        }
        releaseLock();
        return result;
    }

    @Override
    public boolean beforeDispatchTrackballEvent(ExtInfo extInfo, MotionEvent motionEvent) {
        locked++;
        boolean result = false;
        for (ActivityCallback callback : callbacks) {
            result = callback.beforeDispatchTrackballEvent(extInfo, motionEvent);
            if (result) break;
        }
        releaseLock();
        return result;
    }

    @Override
    public boolean afterDispatchTrackballEvent(ExtInfo extInfo, MotionEvent motionEvent) {
        locked++;
        boolean result = false;
        for (ActivityCallback callback : callbacks) {
            result = callback.afterDispatchTrackballEvent(extInfo, motionEvent);
            if (result) break;
        }
        releaseLock();
        return result;
    }

    @Override
    public boolean beforeDispatchGenericMotionEvent(ExtInfo extInfo, MotionEvent motionEvent) {
        locked++;
        boolean result = false;
        for (ActivityCallback callback : callbacks) {
            result = callback.beforeDispatchGenericMotionEvent(extInfo, motionEvent);
            if (result) break;
        }
        releaseLock();
        return result;
    }

    @Override
    public boolean afterDispatchGenericMotionEvent(ExtInfo extInfo, MotionEvent motionEvent) {
        locked++;
        boolean result = false;
        for (ActivityCallback callback : callbacks) {
            result = callback.afterDispatchGenericMotionEvent(extInfo, motionEvent);
            if (result) break;
        }
        releaseLock();
        return result;
    }

    @Override
    public void onWindowFocusChanged(ExtInfo extInfo, boolean hasFocus) {
        locked++;
        for (ActivityCallback callback : callbacks) {
            callback.onWindowFocusChanged(extInfo, hasFocus);
        }
        releaseLock();
    }
}
