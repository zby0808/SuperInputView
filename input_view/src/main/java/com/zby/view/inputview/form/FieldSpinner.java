package com.zby.view.inputview.form;

import android.content.Context;
import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatSpinner;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;

import com.example.input_view.R;
import com.zby.view.inputview.adapter.SpinnerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zby on 2019/7/1.
 * 重写spinner，解决原生spinner各种问题
 */
public class FieldSpinner extends AppCompatSpinner implements IFormField, AdapterView.OnItemSelectedListener {
    private int textSize = 16;
    private int textColor = R.color.text_color2;
    private String mDefaultSelectValue;//默认选择
    private boolean isFirst = true;
    public boolean isDropDownMenuShown = true;//标志下拉列表是否正在显示

    List<DictField> listData = new ArrayList<>();
    private SpinnerAdapter adapter;

    public FieldSpinner(Context context) {
        super(context);
    }

    public FieldSpinner(Context context, int mode) {
        super(context, mode);
    }

    public FieldSpinner(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FieldSpinner(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public FieldSpinner(Context context, AttributeSet attrs, int defStyleAttr, int mode) {
        super(context, attrs, defStyleAttr, mode);
    }

    public FieldSpinner(Context context, AttributeSet attrs, int defStyleAttr, int mode, Resources.Theme popupTheme) {
        super(context, attrs, defStyleAttr, mode, popupTheme);
    }

    @Override
    public boolean performClick() {
        this.isDropDownMenuShown = true;
        return super.performClick();
    }

    public boolean isDropDownMenuShown() {
        return isDropDownMenuShown;
    }

    public void setDropDownMenuShown(boolean isDropDownMenuShown) {
        this.isDropDownMenuShown = isDropDownMenuShown;
    }

    @Override
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
    }

    @Override
    public void setSelection(int position, boolean animate) {
        boolean sameSelected = position == getSelectedItemPosition();
        super.setSelection(position, animate);
        if (sameSelected) {
            // 如果选择项是Spinner当前已选择的项,手动触发回调
            getOnItemSelectedListener().onItemSelected(this, getSelectedView(), position, getSelectedItemId());
            //刷新数据，不刷新，当选择同位置字段时候，会触发listener但不会填充数据
            adapter.notifyDataSetChanged();
        }
    }


    @Override
    public void setSelection(int position) {
        boolean sameSelected = position == getSelectedItemPosition();
        super.setSelection(position);
        if (sameSelected) {//手动触发回调
            getOnItemSelectedListener().onItemSelected(this, getSelectedView(), position, getSelectedItemId());
            //刷新数据
            adapter.notifyDataSetChanged();
        }
    }

    //设置数据源
    public FieldSpinner(Context context, String spinnerContent) {
        super(context);
        String defaultSelectValue = null;
        if (spinnerContent != null) {
            for (String name_value : spinnerContent.split(",")) {
                final String[] split = name_value.split("_");
                if ("check".equals(split[0])) {
                    defaultSelectValue = split[1];
                    continue;
                }
                listData.add(new DictField(split[0], split[1]));
            }

            attachDataSource();
            setValue(defaultSelectValue);
        }
    }

    /**
     * 获取key值，即显示的值
     *
     * @return
     */
    public String getKey() {
        final int selectedItemPosition = getSelectedItemPosition();
        if (selectedItemPosition > -1) {
            return listData.get(selectedItemPosition).getName();
        } else {
            return null;
        }
    }


    /**
     * 获取value值，即显示的值
     *
     * @return
     */
    @Override
    public Object getValue() {
        final int selectedItemPosition = getSelectedItemPosition();
        if (selectedItemPosition > -1) {
            return listData.get(selectedItemPosition).getValue();
        } else {
            return null;
        }
    }

    @Override
    public void setValue(@NonNull Object value) {
        if (value == null) return;
        int i = 0;
        for (DictField data : listData) {
            if (value instanceof String) {
                if (value.equals(data.getValue())) {
                    setSelection(i);
                    break;
                }
                i++;
            }
        }
    }

    public void setTextSizeAndColor(int textSize, int textColor) {
        this.textColor = textColor;
        this.textSize = textSize;
    }


    public void setData(List<DictField> listData, String defaultSelectValue, String hint) {
        this.mDefaultSelectValue = defaultSelectValue;
        if (listData == null) listData = new ArrayList<>();
        if (TextUtils.isEmpty(hint)) hint = "请选择";
        listData.add(0, new DictField(hint, ""));
        this.listData = listData;
        attachDataSource();
        setValue(defaultSelectValue);
    }

    public void setData(List<DictField> listData, String defautSelectValue) {
        this.mDefaultSelectValue = defautSelectValue;
        isFirst = false;
        this.listData = listData;
        attachDataSource();
        setValue(defautSelectValue);
    }

    public void setData(List<DictField> listData) {
        setData(listData, null);
    }

    private void attachDataSource() {
//        ArrayAdapter<DictField> adapter = new ArrayAdapter<>(getContext(), R.layout.comm_spinner_item, listData);
        adapter = new SpinnerAdapter(getContext(), R.layout.comm_spinner_item, listData);
        adapter.setTextSizeAndColor(textSize, textColor);
        adapter.setDropDownViewResource(R.layout.comm_spinner_dropdown_item);
        setAdapter(adapter);
        this.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        if (isFirst) {//只有设置有hint属性才会进入
            TextView textView = (TextView) view;
            textView.setTextColor(TextUtils.isEmpty(mDefaultSelectValue) ?
                    getResources().getColor(R.color.text_color1) :
                    getResources().getColor(textColor)
            );
            listData.remove(0);
            isFirst = false;
            return;
        }
        if (listener != null) listener.onItemSelected(adapterView, view, i, l);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        if (listener != null) listener.onNothingSelected(adapterView);
    }

    OnSpinnerSelectedListener listener;

    public void setOnSpinnerSelectedListener(OnSpinnerSelectedListener listener) {
        this.listener = listener;
    }

    public interface OnSpinnerSelectedListener {
        void onItemSelected(AdapterView<?> var1, View var2, int var3, long var4);

        void onNothingSelected(AdapterView<?> var1);
    }
}
