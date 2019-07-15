package com.zby.view.inputview.util;

import android.support.v4.widget.NestedScrollView;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;

public class ScrollViewUtils {

    /**
     * 当超出屏幕时候，解决焦点冲突自动回滚到上一个焦点位置问题
     *
     * @param scrollView
     */
    public static void setTouchNestScroll(NestedScrollView scrollView) {
        scrollView.setDescendantFocusability(ViewGroup.FOCUS_BEFORE_DESCENDANTS);
        scrollView.setFocusable(true);
        scrollView.setFocusableInTouchMode(true);
        scrollView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                v.requestFocusFromTouch();
                return false;
            }
        });
    }

    /**
     * 当超出屏幕时候，解决焦点冲突自动回滚到上一个焦点位置问题
     *
     * @param scrollView
     */
    public static void setTouchScroll(ScrollView scrollView) {
        scrollView.setDescendantFocusability(ViewGroup.FOCUS_BEFORE_DESCENDANTS);
        scrollView.setFocusable(true);
        scrollView.setFocusableInTouchMode(true);
        scrollView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                v.requestFocusFromTouch();
                return false;
            }
        });
    }

}
