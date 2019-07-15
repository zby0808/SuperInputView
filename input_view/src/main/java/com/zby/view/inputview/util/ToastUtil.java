package com.zby.view.inputview.util;

import android.content.Context;
import android.widget.Toast;

import es.dmoral.toasty.Toasty;

public class ToastUtil {

    public static void warningShort(Context context, String msg) {
        Toasty.warning(context, msg, Toast.LENGTH_SHORT).show();
    }

}
