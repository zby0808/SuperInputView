package com.zby.view.inputview.listener;

import android.text.Editable;

/**
 * 针对TYPE_EDIT或者TYPE_REASON监听事件
 */
public interface OnInputEditViewListener {
    void beforeTextChanged(String key, CharSequence s, int start, int count, int after);

    void onTextChanged(String key, CharSequence s, int start, int before, int count);

    void afterTextChanged(String key, Editable s);
}
