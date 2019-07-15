package com.zby.view.inputview.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.zby.view.inputview.R;
import com.zby.view.inputview.form.DictField;
import com.zby.view.inputview.util.PListUtils;

import java.util.List;

/**
 * Created by zby on 2019/7/1
 * 重写adapter可动态设置属性值
 */
public class SpinnerAdapter extends ArrayAdapter<DictField> {
    private int textSize = 16;
    private int textColor = R.color.text_color2;

    public SpinnerAdapter(Context context, int resource, List<DictField> objects) {
        super(context, resource, objects);
    }

    public void setTextSizeAndColor(int textSize, int textColor) {
        this.textColor = textColor;
        this.textSize = textSize;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView tv = (TextView) super.getView(position, convertView, parent);
        tv.setTextColor(tv.getContext().getResources().getColor(textColor));
        tv.setTextSize(textSize);
        return tv;
    }


    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        TextView tv = (TextView) super.getView(position, convertView, parent);
        tv.setTextColor(tv.getContext().getResources().getColor(textColor));
        tv.setPadding(PListUtils.dip2px(tv.getContext(), 10),
                PListUtils.dip2px(tv.getContext(), 10),
                PListUtils.dip2px(tv.getContext(), 10),
                PListUtils.dip2px(tv.getContext(), 10));
        tv.setTextSize(textSize);
        return tv;
    }


}
