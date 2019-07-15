package com.zby.view.inputview.imageview;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

import com.zby.view.inputview.R;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.entity.LocalMedia;
import com.zby.view.inputview.adapter.GridInputImageAdapter;
import com.zby.view.inputview.form.IFormField;
import com.zby.view.inputview.multi.InputViewMultiItem;
import com.zby.view.inputview.util.PListUtils;

import java.util.ArrayList;
import java.util.List;

import static android.app.Activity.RESULT_OK;

/**
 * Created by zby on 2019/7/1.
 */
public class InputImageRecyclerView extends FrameLayout implements IFormField, GridInputImageAdapter.OnAddPicClickListener {
    private List<LocalMedia> mImageList = new ArrayList<>();
    private GridInputImageAdapter mAdapter;
    private Activity activity;

    private View rootView;
    private RecyclerView mRecyclerView;

    private InputViewMultiItem mItem;

    public InputImageRecyclerView(@NonNull Context context) {
        super(context);
        init();
    }

    public InputImageRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public InputImageRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        rootView = View.inflate(getContext(), R.layout.adapter_image_recyler, this);
        mRecyclerView = rootView.findViewById(R.id.recyclerView);
    }

    public InputImageRecyclerView with(Activity activity) {
        this.activity = activity;
        return this;
    }

    //图片选择加载
    public void startCamera(InputViewMultiItem item) {
        this.mItem = item;
        mAdapter = new GridInputImageAdapter(getContext(), item.getRequestCode(), this);
        mAdapter.setList(mImageList);
        mAdapter.setSelectMax(item.getMaxSelectNum());

        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), item.getImageSpanCount(), LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setNestedScrollingEnabled(false);
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new GridInputImageAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                PictureSelector.create(activity).externalPicturePreview(position, mImageList);
            }
        });
    }

    /**
     * 选择图片回调
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK && requestCode == mItem.getRequestCode() && data != null) {
            mImageList = PictureSelector.obtainMultipleResult(data);
            mAdapter.setList(mImageList);
            mAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onAddPicClick(int requestCode, List<LocalMedia> list) {
        PickUtils.comm(activity, mImageList, mItem.getMaxSelectNum(), mItem.getImageSpanCount(), requestCode);
    }

    @Override
    public Object getValue() {
        //获取图片集合List<String>
        if (PListUtils.isEmpty(mImageList)) {
            return null;
        } else if (mImageList.size() == 1) {
            return mImageList.get(0).getPath();
        } else {
            final List<String> strings = new ArrayList<>();
            for (LocalMedia m : mImageList) {
                strings.add(TextUtils.isEmpty(m.getCompressPath()) ? m.getCompressPath() : m.getPath());
            }
            return strings;
        }
    }

    @Override
    public void setValue(Object value) {
        //传入的是List<String>
        if (value != null) {
            if (value instanceof List) {
                final List<String> strs = (List<String>) value;
                for (String s : strs) {
                    final LocalMedia localMedia = new LocalMedia();
                    localMedia.setPath(s);
                    localMedia.setCompressPath(s);
                    localMedia.setMimeType(MediaType.getMediaTypeByPath(s));
                    localMedia.setPictureType("image/jpeg");
                    mImageList.add(localMedia);
                }
            } else if (value instanceof String) {
                String tempValue = (String) value;
                final LocalMedia localMedia = new LocalMedia();
                localMedia.setPath(tempValue);
                localMedia.setCompressPath(tempValue);
                localMedia.setMimeType(MediaType.getMediaTypeByPath(tempValue));
                localMedia.setPictureType("image/jpeg");
                mImageList.add(localMedia);
            }
            mAdapter.setList(mImageList);
            mAdapter.notifyDataSetChanged();
        }
    }
}
