package com.zby.view.inputview.listener;

import android.view.View;
import android.widget.AdapterView;

public interface OnInputSpinnerSelectedListener {
    void onItemSelected(String key, AdapterView<?> adapter, View view, int position, long var4);

    void onNothingSelected(String key, AdapterView<?> adapter);
}
