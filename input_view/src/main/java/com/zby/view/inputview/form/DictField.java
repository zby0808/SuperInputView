package com.zby.view.inputview.form;

import java.io.Serializable;

/**
 * Created by zby on 2019/7/1.
 */
public class DictField implements Serializable {
    private String name;
    private String value;
    private int type;

    @Override
    public String toString() {
        return name;
    }

    public DictField(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public DictField setType(int type) {
        this.type = type;
        return this;
    }
}
