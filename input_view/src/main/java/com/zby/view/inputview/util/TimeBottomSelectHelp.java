package com.zby.view.inputview.util;

import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;

import com.bigkoo.pickerview.TimePickerView;
import com.zby.view.inputview.R;

import java.util.Calendar;
import java.util.Date;


public class TimeBottomSelectHelp {

    /**
     * @param activity
     * @param selectTime 默认选择的时间
     * @param isNeedHMS  是否需要时分秒
     * @param listener
     */
    public static void showBottomTimeDialog(AppCompatActivity activity, Date selectTime, boolean isNeedHMS, boolean isDialog, TimePickerView.OnTimeSelectListener listener) {
        Calendar selectCalendar = Calendar.getInstance();
        if (selectTime != null) {
            selectCalendar.setTime(selectTime);
        }

        //时间选择器
        TimePickerView pvTime = new TimePickerView.Builder(activity, listener)
                .setType(new boolean[]{true, true, true, isNeedHMS, isNeedHMS, isNeedHMS})// 默认全部显示
                .setCancelText("取消")//取消按钮文字
                .setSubmitText("确定")//确认按钮文字
                .setContentSize(18)//滚轮文字大小
                .setTitleSize(20)//标题文字大小
                .setTitleText("时间选择")//标题文字
                .setOutSideCancelable(true)//点击屏幕，点在控件外部范围时，是否取消显示
                .isCyclic(true)//是否循环滚动
                .setTitleColor(Color.BLACK)//标题文字颜色
                .setSubmitColor(ContextCompat.getColor(activity, R.color.colorAccent))
                .setCancelColor(ContextCompat.getColor(activity, R.color.comm_blue600))//取消按钮文字颜色
                .setTitleBgColor(Color.WHITE)//标题背景颜色 Night mode
                .setBgColor(ContextCompat.getColor(activity, R.color.comm_grey100))//滚轮背景颜色 Night mode
                .setDate(selectCalendar)// 如果不设置的话，默认是系统时间*/
                // .setRangDate(startDate,endDate)//起始终止年月日设定
                //.setLabel("年", "月", "日", "时", "分", "秒")//默认设置为年月日时分秒
                .setLabel("", "", isNeedHMS ? "日" : "", "", "", "")//默认设置为年月日时分秒
                .isCenterLabel(true) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                .isDialog(isDialog)//是否显示为对话框样式
                .build();
        pvTime.show();
    }
}
