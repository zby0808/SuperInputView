package com.zby.view.inputview.recycleview;

import android.graphics.Rect;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

import com.zby.view.inputview.R;
import com.zby.view.inputview.recycleview.divider.HorizontalDividerItemDecoration;
import com.zby.view.inputview.recycleview.divider.VerticalDividerItemDecoration;


/**
 * 视图帮助类
 */
public class RecyclerViewHelper {

    private RecyclerViewHelper() {
        throw new RuntimeException("RecyclerViewHelper cannot be initialized!");
    }


    /**
     * 垂直型
     *
     * @param view
     * @param isDivided 是否需要分割线
     */
    public static void initRecyclerViewV(RecyclerView view, RecyclerView.Adapter adapter, boolean isDivided) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        view.setLayoutManager(layoutManager);
        view.setItemAnimator(new DefaultItemAnimator());
        if (isDivided) {
            view.addItemDecoration(new HorizontalDividerItemDecoration.Builder(view.getContext())
                    .colorResId(R.color.line)
                    .showLastDivider()
                    .build());
        }
        view.setAdapter(adapter);
    }

    /**
     * 设置分割线的颜色
     *
     * @param view
     * @param dividedColor R.color.xxx分割线颜色
     */
    public static void setRecyclerViewDivided(RecyclerView view, int dividedColor) {
        view.addItemDecoration(new HorizontalDividerItemDecoration.Builder(view.getContext())
                .colorResId(dividedColor == -1 ? R.color.line : dividedColor)
                .showLastDivider()
                .build());
    }


    /**
     * 配置水平列表RecyclerView
     */
    public static void initRecyclerViewH(RecyclerView view, boolean isDivided, RecyclerView.Adapter adapter) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        view.setLayoutManager(layoutManager);
        view.setItemAnimator(new DefaultItemAnimator());
        if (isDivided) {
            view.addItemDecoration(new VerticalDividerItemDecoration.Builder(view.getContext()).colorResId(R.color.line).build());
        }
        view.setAdapter(adapter);
    }

    /**
     * 配置网格列表RecyclerView
     */
    public static void initRecyclerViewG(RecyclerView view, boolean isDivided, RecyclerView.Adapter adapter, final int column) {
        GridLayoutManager layoutManager = new GridLayoutManager(view.getContext(), column, LinearLayoutManager.VERTICAL, false);
        view.setLayoutManager(layoutManager);
        view.setItemAnimator(new DefaultItemAnimator());
//        view.setBackgroundColor(ContextCompat.getColor(view.getContext(), R.color.line));
        if (isDivided) {
            view.addItemDecoration(new HorizontalDividerItemDecoration.Builder(view.getContext())
                    .colorResId(R.color.line).build());
            view.addItemDecoration(new VerticalDividerItemDecoration.Builder(view.getContext())
                    .colorResId(R.color.line).build());
        }
        view.setAdapter(adapter);
    }

    /**
     * 配置瀑布流列表RecyclerView
     */
    public static void initRecyclerViewSV(RecyclerView view, boolean isDivided,
                                          RecyclerView.Adapter adapter, int column) {
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(column, StaggeredGridLayoutManager.VERTICAL);
        view.setLayoutManager(layoutManager);
        view.setItemAnimator(new DefaultItemAnimator());
        if (isDivided) {
            view.addItemDecoration(new RecyclerView.ItemDecoration() {
                @Override
                public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                    outRect.set(4, 4, 4, 4);
                }
            });
        }
        view.setAdapter(adapter);
    }


    public static void initRecyclerViewC(RecyclerView view, boolean isDivided, RecyclerView.Adapter adapter,
                                         final int column, int color) {
        GridLayoutManager layoutManager = new GridLayoutManager(view.getContext(), column, LinearLayoutManager.VERTICAL, false);
        view.setLayoutManager(layoutManager);
        view.setItemAnimator(new DefaultItemAnimator());
        view.setBackgroundColor(ContextCompat.getColor(view.getContext(), color));
        if (isDivided) {
            view.addItemDecoration(new HorizontalDividerItemDecoration.Builder(view.getContext())
                    .colorResId(R.color.line).build());
            view.addItemDecoration(new VerticalDividerItemDecoration.Builder(view.getContext())
                    .colorResId(R.color.line).build());
        }
        view.setAdapter(adapter);
    }
}
