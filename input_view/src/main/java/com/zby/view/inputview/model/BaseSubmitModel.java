package com.zby.view.inputview.model;

import java.io.Serializable;

public abstract class BaseSubmitModel implements Serializable {
    private String subKey;//提交服务器key值
    private String subValue;//需要提交到value的

    public String getSubKey() {
        return subKey;
    }

    public void setSubKey(String subKey) {
        this.subKey = subKey;
    }

    public String getSubValue() {
        return subValue;
    }

    public void setSubValue(String subValue) {
        this.subValue = subValue;
    }
}
