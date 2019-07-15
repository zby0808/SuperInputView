package com.zby.view.inputview.listener;

/**
 * Created by zby on 2019/7/1.
 */
public interface ViewType {

    //选择类型
    int TYPE_TEXT = 0;//文本---textView
    int TYPE_EDIT = 1;//编辑----editText
    int TYPE_SPINNER = 2;//下拉---spinner
    int TYPE_TIME = 3;//选择时间
    int TYPE_RADIO = 4;//单选 ----radio
    int TYPE_REASON = 5;//副本框
    int TYPE_TITLE = 6;//标题
    int TYPE_IMAGE = 7;//图片


    //一些控件字段radio
    int RADIO_ONE = 1;//radio选择第一个
    int RADIO_TWO = 2;//radio选择第二个


//    TEXTVIEW(0), EDITTEXT(1);
//
//    int type;
//
//    public int getType() {
//        return type;
//    }
//
//    private ViewType(int type) {
//        this.type = type;
//    }

}
