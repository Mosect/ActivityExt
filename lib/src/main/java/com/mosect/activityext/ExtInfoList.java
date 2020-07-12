package com.mosect.activityext;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

class ExtInfoList {

    private ExtInfo top;
    private List<ExtInfo> list = new ArrayList<>();
    private boolean listChanged = false;

    ExtInfo find(Activity activity) {
        ExtInfo cur = top;
        while (null != cur) {
            if (cur.getActivity() == activity) {
                return cur;
            }
            cur = top.next;
        }
        return null;
    }

    ExtInfo remove(Activity activity) {
        ExtInfo extInfo = find(activity);
        if (null != extInfo) {
            remove(extInfo);
            return extInfo;
        }
        return null;
    }

    void remove(ExtInfo extInfo) {
        if (null != extInfo.last) {
            extInfo.last.next = extInfo.next;
        }
        if (null != extInfo.next) {
            extInfo.next.last = extInfo.last;
        }
        if (top == extInfo) {
            top = extInfo.next;
        }
        extInfo.next = null;
        extInfo.last = null;
        listChanged = true;
    }

    void add(ExtInfo extInfo) {
        if (null == top) {
            extInfo.last = null;
            extInfo.next = null;
            top = extInfo;
        } else {
            extInfo.last = null;
            extInfo.next = top;
            top.last = extInfo;
            top = extInfo;
        }
        listChanged = true;
    }

    ExtInfo moveToTop(Activity activity) {
        ExtInfo extInfo = find(activity);
        if (null != extInfo && extInfo != top) {
            remove(extInfo);
            add(extInfo);
        }
        return extInfo;
    }

    ExtInfo top() {
        return top;
    }

    private void checkList() {
        if (listChanged) {
            listChanged = false;
            list.clear();
            ExtInfo cur = top;
            while (null != cur) {
                list.add(cur);
                cur = cur.next;
            }
        }
    }

    int size() {
        checkList();
        return list.size();
    }

    ExtInfo get(int index) {
        checkList();
        return list.get(index);
    }
}
