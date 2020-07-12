package com.mosect.activityext;

import android.annotation.TargetApi;
import android.os.Build;
import android.view.ActionMode;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.SearchEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.accessibility.AccessibilityEvent;

class ExtWindowCallback implements Window.Callback {

    private Window.Callback target;
    private ExtInfo extInfo;

    ExtWindowCallback(Window.Callback target, ExtInfo extInfo) {
        this.target = target;
        this.extInfo = extInfo;
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        if (null != extInfo.callbackList &&
                extInfo.callbackList.beforeDispatchKeyEvent(extInfo, keyEvent)) {
            return true;
        }
        if (ActivityExt.getInstance().callbackList.beforeDispatchKeyEvent(extInfo, keyEvent)) {
            return true;
        }
        boolean result = target.dispatchKeyEvent(keyEvent);
        if (null != extInfo.callbackList &&
                extInfo.callbackList.afterDispatchKeyEvent(extInfo, keyEvent)) {
            return true;
        }
        if (ActivityExt.getInstance().callbackList.afterDispatchKeyEvent(extInfo, keyEvent)) {
            return true;
        }
        return result;
    }

    @Override
    public boolean dispatchKeyShortcutEvent(KeyEvent keyEvent) {
        if (null != extInfo.callbackList &&
                extInfo.callbackList.beforeDispatchKeyShortcutEvent(extInfo, keyEvent)) {
            return true;
        }
        if (ActivityExt.getInstance().callbackList.beforeDispatchKeyShortcutEvent(extInfo, keyEvent)) {
            return true;
        }
        boolean result = target.dispatchKeyShortcutEvent(keyEvent);
        if (null != extInfo.callbackList &&
                extInfo.callbackList.afterDispatchKeyShortcutEvent(extInfo, keyEvent)) {
            return true;
        }
        if (ActivityExt.getInstance().callbackList.afterDispatchKeyShortcutEvent(extInfo, keyEvent)) {
            return true;
        }
        return result;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        if (null != extInfo.callbackList &&
                extInfo.callbackList.beforeDispatchTouchEvent(extInfo, motionEvent)) {
            return true;
        }
        if (ActivityExt.getInstance().callbackList.beforeDispatchTouchEvent(extInfo, motionEvent)) {
            return true;
        }
        boolean result = target.dispatchTouchEvent(motionEvent);
        if (null != extInfo.callbackList &&
                extInfo.callbackList.afterDispatchTouchEvent(extInfo, motionEvent)) {
            return true;
        }
        if (ActivityExt.getInstance().callbackList.afterDispatchTouchEvent(extInfo, motionEvent)) {
            return true;
        }
        return result;
    }

    @Override
    public boolean dispatchTrackballEvent(MotionEvent motionEvent) {
        if (null != extInfo.callbackList &&
                extInfo.callbackList.beforeDispatchTrackballEvent(extInfo, motionEvent)) {
            return true;
        }
        if (ActivityExt.getInstance().callbackList.beforeDispatchTrackballEvent(extInfo, motionEvent)) {
            return true;
        }
        boolean result = target.dispatchTrackballEvent(motionEvent);
        if (null != extInfo.callbackList &&
                extInfo.callbackList.afterDispatchTrackballEvent(extInfo, motionEvent)) {
            return true;
        }
        if (ActivityExt.getInstance().callbackList.afterDispatchTrackballEvent(extInfo, motionEvent)) {
            return true;
        }
        return result;
    }

    @Override
    public boolean dispatchGenericMotionEvent(MotionEvent motionEvent) {
        if (null != extInfo.callbackList &&
                extInfo.callbackList.beforeDispatchGenericMotionEvent(extInfo, motionEvent)) {
            return true;
        }
        if (ActivityExt.getInstance().callbackList.beforeDispatchGenericMotionEvent(extInfo, motionEvent)) {
            return true;
        }
        boolean result = target.dispatchGenericMotionEvent(motionEvent);
        if (null != extInfo.callbackList &&
                extInfo.callbackList.afterDispatchGenericMotionEvent(extInfo, motionEvent)) {
            return true;
        }
        if (ActivityExt.getInstance().callbackList.afterDispatchGenericMotionEvent(extInfo, motionEvent)) {
            return true;
        }
        return result;
    }

    @Override
    public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        return target.dispatchPopulateAccessibilityEvent(accessibilityEvent);
    }

    @Override
    public View onCreatePanelView(int i) {
        return target.onCreatePanelView(i);
    }

    @Override
    public boolean onCreatePanelMenu(int i, Menu menu) {
        return target.onCreatePanelMenu(i, menu);
    }

    @Override
    public boolean onPreparePanel(int i, View view, Menu menu) {
        return target.onPreparePanel(i, view, menu);
    }

    @Override
    public boolean onMenuOpened(int i, Menu menu) {
        return target.onMenuOpened(i, menu);
    }

    @Override
    public boolean onMenuItemSelected(int i, MenuItem menuItem) {
        return target.onMenuItemSelected(i, menuItem);
    }

    @Override
    public void onWindowAttributesChanged(WindowManager.LayoutParams layoutParams) {
        target.onWindowAttributesChanged(layoutParams);
    }

    @Override
    public void onContentChanged() {
        target.onContentChanged();
    }

    @Override
    public void onWindowFocusChanged(boolean b) {
        target.onWindowFocusChanged(b);
        if (null != extInfo.callbackList) {
            extInfo.callbackList.onWindowFocusChanged(extInfo, b);
        }
        ActivityExt.getInstance().callbackList.onWindowFocusChanged(extInfo, b);
    }

    @Override
    public void onAttachedToWindow() {
        target.onAttachedToWindow();
    }

    @Override
    public void onDetachedFromWindow() {
        target.onDetachedFromWindow();
    }

    @Override
    public void onPanelClosed(int i, Menu menu) {
        target.onPanelClosed(i, menu);
    }

    @Override
    public boolean onSearchRequested() {
        return target.onSearchRequested();
    }

    @TargetApi(Build.VERSION_CODES.M)
    @Override
    public boolean onSearchRequested(SearchEvent searchEvent) {
        return target.onSearchRequested(searchEvent);
    }

    @Override
    public ActionMode onWindowStartingActionMode(ActionMode.Callback callback) {
        return target.onWindowStartingActionMode(callback);
    }

    @TargetApi(Build.VERSION_CODES.M)
    @Override
    public ActionMode onWindowStartingActionMode(ActionMode.Callback callback, int i) {
        return target.onWindowStartingActionMode(callback, i);
    }

    @Override
    public void onActionModeStarted(ActionMode actionMode) {
        target.onActionModeStarted(actionMode);
    }

    @Override
    public void onActionModeFinished(ActionMode actionMode) {
        target.onActionModeFinished(actionMode);
    }
}
