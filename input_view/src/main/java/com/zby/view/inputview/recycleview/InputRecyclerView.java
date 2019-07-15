package com.zby.view.inputview.recycleview;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

import com.zby.view.inputview.R;
import com.zby.view.inputview.adapter.InputViewAdapter;
import com.zby.view.inputview.form.InputScrollView;
import com.zby.view.inputview.multi.InputViewMultiItem;
import com.zby.view.inputview.recycleview.divider.HorizontalDividerItemDecoration;

import java.util.List;

/**
 * Created by zby on 2019/7/1.
 */
public class InputRecyclerView extends FrameLayout {
    private RecyclerView mRecyclerView;
    private InputViewAdapter mAdapter;
    private boolean isEnable;//自定义属性，是否可编辑
    private boolean isScroll;//自定义属性，是否支持滑动，注：当该view外层以上套有scrollView时，设为false
    private int dividedColor;//自定义属性，recyclerView下滑线颜色

    private InputScrollView mScrollView;

    public InputRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }


    private void init(AttributeSet attrs) {
        TypedArray ta = getContext().obtainStyledAttributes(attrs, R.styleable.comm_input);
        isEnable = ta.getBoolean(R.styleable.comm_input_input_enable, true);
        isScroll = ta.getBoolean(R.styleable.comm_input_scroll_enable, true);
        dividedColor = ta.getColor(R.styleable.comm_input_divided_color, -1);

        View rootView = View.inflate(getContext(), R.layout.base_recycler_view, this);
        mRecyclerView = rootView.findViewById(R.id.recyclerView);
        mScrollView = rootView.findViewById(R.id.rootView);
        mScrollView.setNestedScrollingEnabled(isScroll);
    }

    public void setViewData(@NonNull List<InputViewMultiItem> listData) {
        mAdapter = new InputViewAdapter(listData, getContext());
        mAdapter.bindToRecyclerView(mRecyclerView);
        mAdapter.isViewEnable(isEnable);//是否可编辑，为false时，所有控件都不可编辑
        RecyclerViewHelper.initRecyclerViewV(mRecyclerView, mAdapter, true);
        if (dividedColor != -1) {//控制下划线颜色
            mRecyclerView.addItemDecoration(new HorizontalDividerItemDecoration.Builder(mRecyclerView.getContext())
                    .color(dividedColor)
                    .showLastDivider()
                    .build());
        }
    }

    public RecyclerView getRecyclerView() {
        return mRecyclerView;
    }

    public InputViewAdapter getAdapter() {
        return mAdapter;
    }

    /**
     * 所有控件是否可编辑和点击默认true
     * <p>
     * 也可在xml设置：app:input_enable="false"控制所有控件不可编辑
     *
     * @param isEnable true可以，false不可以
     */
    public void setViewEnable(boolean isEnable) {
        mAdapter.isViewEnable(isEnable);
        mAdapter.notifyDataSetChanged();
    }

    /**
     * 包裹的ScrollView是否滑动，默认可以滑动true，当该布局外部控件包含有ScrollView时，
     * 此属性最好设置为false。因为当包裹的RecyclerView过多时，可能会出现滑动冲突现象。
     * <p>
     * 也可在xml设置：app:scroll_enable="false"控制该控件不可滑动
     *
     * @param isScroll true可以，false不可以
     */
    public void setScrollEnable(boolean isScroll) {
        mScrollView.setNestedScrollingEnabled(isScroll);
    }

    /**
     * 设置分割线的颜色
     *
     * <p>
     * * 也可在xml设置：app:divided_color控制下划线颜色
     *
     * @param dividedColor R.color.xxx分割线颜色
     */
    public void setViewDivided(int dividedColor) {
        RecyclerViewHelper.setRecyclerViewDivided(mRecyclerView, dividedColor);
    }
}
