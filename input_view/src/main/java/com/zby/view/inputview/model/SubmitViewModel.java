package com.zby.view.inputview.model;

import java.io.Serializable;

/**
 * Created by zby on 2019/7/1.
 */
public class SubmitViewModel<B extends BaseSubmitModel> implements Serializable {
    /**
     * 传入的实体对象
     */
    public B bean;//提交对象
    private String selectKey;//只有spinner才会用到该字段key值

    private String value;//显示的值

    private String subKey;//提交服务器key值
    private String subValue;//需要提交到value的

    private boolean isMust = false;//用作检测必填值是否已填

    public SubmitViewModel(String value) {
        this.value = value;
    }

    //spinner专用
    public SubmitViewModel(String selectKey, String value) {
        this.selectKey = selectKey;
        this.value = value;
    }

    //提交服务器字段专用
    public SubmitViewModel(String value, String subKey, String subValue) {
        this.value = value;
        this.subKey = subKey;
        this.subValue = subValue;
    }

    public SubmitViewModel() {
    }

    public B getBean() {
        return bean;
    }

    public void setBean(B bean) {
        this.bean = bean;
    }

    public boolean isMust() {
        return isMust;
    }

    public void setMust(boolean must) {
        isMust = must;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getSelectKey() {
        return selectKey;
    }

    public void setSelectKey(String selectKey) {
        this.selectKey = selectKey;
    }

    public String getSubValue() {
        return subValue;
    }

    public void setSubValue(String subValue) {
        this.subValue = subValue;
    }

    public String getSubKey() {
        return subKey;
    }

    public void setSubKey(String subKey) {
        this.subKey = subKey;
    }
}
