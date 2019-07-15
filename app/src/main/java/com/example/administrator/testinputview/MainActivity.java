package com.example.administrator.testinputview;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;

import com.chad.library.adapter.base.BaseViewHolder;
import com.zby.view.inputview.adapter.InputViewAdapter;
import com.zby.view.inputview.form.DictField;
import com.zby.view.inputview.listener.OnInputSpinnerSelectedListener;
import com.zby.view.inputview.listener.OnInputTextViewListener;
import com.zby.view.inputview.listener.ViewType;
import com.zby.view.inputview.multi.InputViewMultiItem;
import com.zby.view.inputview.recycleview.InputRecyclerView;
import com.zby.view.inputview.util.PTimeUtil;
import com.zby.view.inputview.util.ToastUtil;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private InputRecyclerView mInputView1;
    private InputRecyclerView mInputView2;
    private InputRecyclerView mInputView3;
    private NestedScrollView mScrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mInputView1 = findViewById(R.id.inputRecyclerView1);
//        mScrollView = findViewById(R.id.netScrollView);
//        ScrollViewUtils.setTouchNestScroll(mScrollView);
        mInputView2 = findViewById(R.id.inputRecyclerView2);
        mInputView3 = findViewById(R.id.inputRecyclerView3);

        String document = "http://183.62.140.7:30099/personFile/2019-03-22/1553243455523519.jpg";
        List<String> images = new ArrayList<>();
        images.add(document);
        images.add(document);

        List<DictField> dicts = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            dicts.add(new DictField("1测试下拉" + i, i + ""));
        }

        List<DictField> dicts2 = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            dicts2.add(new DictField("2测试下拉" + i, i + ""));
        }

//        InputViewMultiItem.Builder(ViewType.TYPE_TITLE)
//                .b(new Object())
//                .imageSpanCount(1)
//                .maxSelectNum(2)
//                .requestCode(110)
//                .addListener(true)
//                .defaultRadioKey(1)
//                .fields(new ArrayList<DictField>())
//                .isMust(true)
//                .isEnable(true)
//                .isNeedHMS(true)
//                .isNumber(true)
//                .key("")
//                .value("")
//                .selectValue("")
//                .radioOne("")
//                .radioTwo("");

        List<InputViewMultiItem> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            list.add(InputViewMultiItem.Builder(1).key("测试" + i)
                    .hint("请输入测试" + i).isMust(true)
                    .keyViewBgAndWidth(R.color.toolbar_color, 100)
                    .create());
        }
        list.add(InputViewMultiItem.Builder(ViewType.TYPE_TIME)
                .key("选择时间")
                .value(PTimeUtil.getStringDateShort())
                .isNeedHMS(false)
                .create());
        list.add(InputViewMultiItem.Builder(ViewType.TYPE_TIME)
                .key("选择时间2")
                .isTimeDialog(true)
                .value(PTimeUtil.getStringDate())
                .isNeedHMS(true)
                .create());

        list.add(InputViewMultiItem.Builder(ViewType.TYPE_SPINNER)
                .key("测试下拉1")
                .fields(dicts)
                .addListener(true)
                .hint("请随便选择下")
                .valueTextColor(R.color.blue)
                .create());

        list.add(InputViewMultiItem.Builder(ViewType.TYPE_SPINNER)
                .key("测试下拉2")
                .fields(dicts2)
                .selectValue("5")
                .addListener(true)
                .valueTextColor(R.color.btn_org)
                .create());

        list.add(InputViewMultiItem.Builder(ViewType.TYPE_TEXT)
                .key("测试选择1")
                .addListener(true)
                .create());

        list.add(InputViewMultiItem.Builder(ViewType.TYPE_TEXT)
                .key("测试选择2")
                .addListener(true)
                .create());

        list.add(InputViewMultiItem.Builder(ViewType.TYPE_REASON)
                .key("测试")
                .create());

        list.add(InputViewMultiItem.Builder(ViewType.TYPE_IMAGE)
                .key("图片")
                .b(images)
                .maxSelectNum(1)
                .imageSpanCount(4)
                .requestCode(110)
                .create());

        mInputView1.setViewData(list);
        mInputView1.setScrollEnable(false);

        final InputViewAdapter adapter = mInputView1.getAdapter();

        adapter.setOnInputSpinnerSelectedListener(new OnInputSpinnerSelectedListener() {
            @Override
            public void onItemSelected(String key, AdapterView<?> var1, View var2, int var3, long var4) {
                DictField selectedItem = (DictField) var1.getSelectedItem();
                if (key.equals("测试下拉1")) {
                    ToastUtil.warningShort(MainActivity.this, selectedItem.getName());
                } else if (key.equals("测试下拉2")) {
                    ToastUtil.warningShort(MainActivity.this, selectedItem.getName());
                }
            }

            @Override
            public void onNothingSelected(String key, AdapterView<?> var1) {

            }
        });

        adapter.setOnInputTextViewListener(new OnInputTextViewListener() {
            @Override
            public void clickItemView(BaseViewHolder holder, int position) {
                InputViewMultiItem item = adapter.getItem(position);
                item.setValue("选择了第" + position + "项------的值");
                adapter.notifyItemChanged(position);
            }
        });


        List<InputViewMultiItem> list2 = new ArrayList<>();
        for (int i = 5; i < 10; i++) {
            list2.add(InputViewMultiItem.Builder(ViewType.TYPE_EDIT).key("测试" + i).isMust(true).create());
        }

        list2.add(InputViewMultiItem.Builder(ViewType.TYPE_TIME)
                .key("选择时间2")
                .value(PTimeUtil.getStringDate())
                .isNeedHMS(true)
                .create());
        mInputView2.setViewData(list2);
        mInputView2.setScrollEnable(false);

        List<InputViewMultiItem> list3 = new ArrayList<>();
        for (int i = 10; i < 15; i++) {
            list3.add(InputViewMultiItem.Builder(1).key("测试" + i).isMust(true).create());
        }

        mInputView3.setViewData(list3);
        mInputView3.setScrollEnable(false);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 110) {
            mInputView1.getAdapter().refreshInputImages(requestCode, resultCode, data);
        }
    }
}
