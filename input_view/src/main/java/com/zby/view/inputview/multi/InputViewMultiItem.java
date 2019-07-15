package com.zby.view.inputview.multi;

import android.support.annotation.NonNull;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.example.input_view.R;
import com.zby.view.inputview.form.DictField;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zby on 2019/7/1.
 */
public class InputViewMultiItem<B> implements MultiItemEntity, Serializable {
    //公共属性
    private B b;
    private String key;//描述----必填
    private String value;//要填入的值
    private int type;//0 textView 1 EditText  2 spinner....必填
    private boolean isMust = false;//是否必须，默认不必填
    private boolean isEnable = true;//是否可编辑
    private String hint;//默认值
    private String subKey;//提交服务器的key值，生成json需要字段，若是无，则生成json不规则

    private int keyTextSize = 16;//key文字大小sp
    private int valueTextSize = 16;//value值文字大小sp
    private int keyTextColor = R.color.text_black;//key文字颜色
    private int valueTextColor = R.color.text_color2;//value文字颜色
    private int keyViewLength = 0; // keyView长度dp
    private int keyViewBackground = R.color.white;//keyView背景颜色
    private int valueViewBackground = R.color.white;//valueView背景颜色

    //TYPE_EDIT和TYPE_REASON----编辑输入框特有的属性
    private boolean isNumber = false;//输入数字或者字符串（editText）

    //TYPE_TIME----选择时间特有的属性
    private boolean isNeedHMS = true;//选择时间的时候，是否需要时分秒
    private boolean isTimeDialog = false;//选择时间以dialog形式。（当view以dialog形式添加数据是，设为true，否则时间选择会显示在dialog下层）

    //TYPE_SPINNER ----下拉选择框spinner特有的属性
    private List<DictField> fields;//当spinner是下拉列表
    private String selectValue;//默认下拉选中下表

    //TYPE_RADIO--------单选radio特有的属性
    private String radioOne;//第一个radio默认选择名称
    private String radioTwo;//第二个radio默认选择名称
    private int defaultRadioKey = 1;//默认选择key值

    //TYPE_IMAGE-----选择图片特有的属性
    private int maxSelectNum = 4;//最大选择图片个数，默认最多只能选4张图片
    private int imageSpanCount = 4;//每行显示图片个数
    private int requestCode;//请求图片----当需要此控件时，该参数必填

    //textView ---->listener,editText---->listener
    private boolean addListener = false;//是否需要点击事件

    private InputViewMultiItem(Builder builder) {
        this.b = (B) builder.b;
        this.key = builder.key;
        this.value = builder.value;
        this.type = builder.type;
        this.fields = builder.fields;
        this.selectValue = builder.selectValue;
        this.radioOne = builder.radioOne;
        this.radioTwo = builder.radioTwo;
        this.defaultRadioKey = builder.defaultRadioKey;
        this.addListener = builder.addListener;
        this.isNeedHMS = builder.isNeedHMS;
        this.isMust = builder.isMust;
        this.isEnable = builder.isEnable;
        this.isNumber = builder.isNumber;
        this.maxSelectNum = builder.maxSelectNum;
        this.imageSpanCount = builder.imageSpanCount;
        this.requestCode = builder.requestCode;
        this.keyTextColor = builder.keyTextColor;
        this.valueTextColor = builder.valueTextColor;
        this.keyTextSize = builder.keyTextSize;
        this.valueTextSize = builder.valueTextSize;
        this.hint = builder.hint;
        this.subKey = builder.subKey;
        this.keyViewLength = builder.keyViewLength;
        this.keyViewBackground = builder.keyViewBackground;
        this.valueViewBackground = builder.valueViewBackground;
        this.isTimeDialog = builder.isTimeDialog;
    }


    /**
     * @param type 类型
     * @return
     */
    public static Builder Builder(@NonNull int type) {
        return new Builder(type);
    }

    @Override
    public int getItemType() {
        return getType();
    }

    public static class Builder<B> {
        private B b;
        private String key;//描述
        private String value;//要填入的值
        private int type;//0 textView 1 EditText  2 spinner
        private List<DictField> fields;//当spinner是下拉列表
        private String selectValue;//默认下拉选中下表
        private String radioOne;//radio默认选择名称
        private String radioTwo;
        private int defaultRadioKey = 1;//默认选择key值
        private boolean addListener = false;//是否需要点击事件 eg:textView ---->listener,editText---->listener
        private boolean isNeedHMS = true;//选择时间的时候，是否需要时分秒
        private boolean isMust = false;//是否必须，默认不必填
        private boolean isEnable = true;//是否可编辑
        private boolean isNumber = false;//输入数字或者字符串（editText）
        private int maxSelectNum = 4;//最大选择图片个数，默认最多只能选4张图片
        private int imageSpanCount = 4;//每行显示图片个数
        private int requestCode;//请求图片----当需要此控件时，该参数必填
        private int keyTextSize = 16;//key文字大小
        private int valueTextSize = 16;//value值文字大小
        private int keyTextColor = R.color.text_black;//key文字颜色
        private int valueTextColor = R.color.text_color2;//value文字颜色
        private String hint;//默认值
        private String subKey;//提交服务器的key值
        private int keyViewLength = 0; // keyView长度
        private int keyViewBackground = R.color.white;//keyView背景颜色
        private int valueViewBackground = R.color.white;//valueView背景颜色
        private boolean isTimeDialog = false;//选择时间以dialog形式。（当view以dialog形式添加数据是，设为true，否则时间选择会显示在dialog下层）


        public Builder(int val) {
            type = val;
        }

        public InputViewMultiItem create() {
            return new InputViewMultiItem(this);
        }


        public Builder b(B val) {
            b = val;
            return this;
        }

        public Builder key(String val) {
            key = val;
            return this;
        }

        public Builder value(String val) {
            value = val;
            return this;
        }

        public Builder isNeedHMS(boolean val) {
            isNeedHMS = val;
            return this;
        }


        public Builder fields(List<DictField> val) {
            fields = val;
            return this;
        }

        public Builder selectValue(String val) {
            selectValue = val;
            return this;
        }

        public Builder radioOne(String val) {
            radioOne = val;
            return this;
        }

        public Builder radioTwo(String val) {
            radioTwo = val;
            return this;
        }

        public Builder defaultRadioKey(int val) {
            defaultRadioKey = val;
            return this;
        }


        public Builder addListener(boolean val) {
            addListener = val;
            return this;
        }

        public Builder isMust(boolean val) {
            isMust = val;
            return this;
        }

        public Builder isEnable(boolean val) {
            isEnable = val;
            return this;
        }

        public Builder isNumber(boolean val) {
            isNumber = val;
            return this;
        }

        public Builder maxSelectNum(int val) {
            maxSelectNum = val;
            return this;
        }

        public Builder requestCode(int val) {
            requestCode = val;
            return this;
        }

        public Builder imageSpanCount(int val) {
            imageSpanCount = val;
            return this;
        }

        public Builder keyTextSize(int val) {
            keyTextSize = val;
            return this;
        }

        public Builder valueTextSize(int val) {
            valueTextSize = val;
            return this;
        }

        public Builder keyTextColor(int val) {
            keyTextColor = val;
            return this;
        }

        public Builder valueTextColor(int val) {
            valueTextColor = val;
            return this;
        }

        public Builder hint(String val) {
            hint = val;
            return this;
        }

        public Builder subKey(String val) {
            subKey = val;
            return this;
        }

        public Builder keyViewBgAndWidth(int bg, int lg) {
            keyViewBackground = bg;
            keyViewLength = lg;
            return this;
        }

        public Builder valueViewBackground(int val) {
            valueViewBackground = val;
            return this;
        }

        public Builder isTimeDialog(boolean val) {
            isTimeDialog = val;
            return this;
        }
    }


    /**
     * @param key  键值
     * @param type 类型
     */
    public InputViewMultiItem(String key, int type) {
        this.key = key;
        this.type = type;
    }

    /**
     * @param key   键值
     * @param value 默认value
     * @param type  类型
     */
    public InputViewMultiItem(String key, String value, int type) {
        this.key = key;
        this.value = value;
        this.type = type;
    }

    /**
     * @param key    键值
     * @param value  默认value
     * @param type   类型
     * @param isMust 是否必填
     */
    public InputViewMultiItem(String key, String value, int type, boolean isMust) {
        this.key = key;
        this.value = value;
        this.type = type;
        this.isMust = isMust;
    }

    public void setNeedHMS(boolean needHMS) {
        isNeedHMS = needHMS;
    }

    public int getMaxSelectNum() {
        return maxSelectNum;
    }

    public void setMaxSelectNum(int maxSelectNum) {
        this.maxSelectNum = maxSelectNum;
    }

    public int getImageSpanCount() {
        return imageSpanCount;
    }

    public void setImageSpanCount(int imageSpanCount) {
        this.imageSpanCount = imageSpanCount;
    }

    public int getRequestCode() {
        return requestCode;
    }

    public void setRequestCode(int requestCode) {
        this.requestCode = requestCode;
    }

    public String getSelectValue() {
        return selectValue;
    }

    public void setSelectValue(String selectValue) {
        this.selectValue = selectValue;
    }

    public boolean isEnable() {
        return isEnable;
    }

    public void setEnable(boolean enable) {
        isEnable = enable;
    }

    public boolean isMust() {
        return isMust;
    }

    public void setMust(boolean must) {
        isMust = must;
    }

    public boolean isAddListener() {
        return addListener;
    }

    public void setAddListener(boolean addListener) {
        this.addListener = addListener;
    }

    public int getDefaultRadioKey() {
        return defaultRadioKey;
    }

    public void setDefaultRadioKey(int defaultRadioKey) {
        this.defaultRadioKey = defaultRadioKey;
    }

    public boolean isNeedHMS() {
        return isNeedHMS;
    }

    public void setNeddHMS(boolean needHMS) {
        isNeedHMS = needHMS;
    }

    public String getRadioOne() {
        return radioOne;
    }

    public void setRadioOne(String radioOne) {
        this.radioOne = radioOne;
    }

    public String getRadioTwo() {
        return radioTwo;
    }

    public void setRadioTwo(String radioTwo) {
        this.radioTwo = radioTwo;
    }

    public List<DictField> getFields() {
        return fields;
    }

    public void setFields(List<DictField> fields) {
        this.fields = fields;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public boolean isNumber() {
        return isNumber;
    }

    public void setNumber(boolean number) {
        isNumber = number;
    }

    public B getB() {
        return b;
    }

    public void setB(B b) {
        this.b = b;
    }

    public int getKeyTextSize() {
        return keyTextSize;
    }

    public void setKeyTextSize(int keyTextSize) {
        this.keyTextSize = keyTextSize;
    }

    public int getValueTextSize() {
        return valueTextSize;
    }

    public void setValueTextSize(int valueTextSize) {
        this.valueTextSize = valueTextSize;
    }

    public int getKeyTextColor() {
        return keyTextColor;
    }

    public void setKeyTextColor(int keyTextColor) {
        this.keyTextColor = keyTextColor;
    }

    public int getValueTextColor() {
        return valueTextColor;
    }

    public void setValueTextColor(int valueTextColor) {
        this.valueTextColor = valueTextColor;
    }

    public String getHint() {
        return hint;
    }

    public void setHint(String hint) {
        this.hint = hint;
    }

    public String getSubKey() {
        return subKey;
    }

    public void setSubKey(String subKey) {
        this.subKey = subKey;
    }

    public int getKeyViewLength() {
        return keyViewLength;
    }

    public void setKeyViewLength(int keyViewLength) {
        this.keyViewLength = keyViewLength;
    }

    public int getKeyViewBackground() {
        return keyViewBackground;
    }

    public void setKeyViewBackground(int keyViewBackground) {
        this.keyViewBackground = keyViewBackground;
    }

    public int getValueViewBackground() {
        return valueViewBackground;
    }

    public void setValueViewBackground(int valueViewBackground) {
        this.valueViewBackground = valueViewBackground;
    }

    public boolean isTimeDialog() {
        return isTimeDialog;
    }

    public void setTimeDialog(boolean timeDialog) {
        isTimeDialog = timeDialog;
    }
}
