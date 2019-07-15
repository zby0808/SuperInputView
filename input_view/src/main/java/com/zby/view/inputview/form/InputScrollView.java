package com.zby.view.inputview.form;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.NestedScrollView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by zby on 2019/7/1.
 * 解决scrollView嵌套RecyclerView出现焦点自动定位问题
 */
public class InputScrollView extends NestedScrollView {

    public InputScrollView(@NonNull Context context) {
        super(context);
        initThisScrollView();
    }


    public InputScrollView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initThisScrollView();
    }

    public InputScrollView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initThisScrollView();
    }

    private void initThisScrollView() {
        setDescendantFocusability(ViewGroup.FOCUS_BEFORE_DESCENDANTS);
        setFocusable(true);
        setFocusableInTouchMode(true);
        setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                v.requestFocusFromTouch();
                return false;
            }
        });
    }
}
