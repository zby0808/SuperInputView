package com.zby.view.inputview.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.bigkoo.pickerview.TimePickerView;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.input_view.R;
import com.zby.view.inputview.form.DictField;
import com.zby.view.inputview.form.FieldSpinner;
import com.zby.view.inputview.form.InputClearEditText;
import com.zby.view.inputview.imageview.InputImageRecyclerView;
import com.zby.view.inputview.listener.OnInputEditViewListener;
import com.zby.view.inputview.listener.OnInputSpinnerSelectedListener;
import com.zby.view.inputview.listener.OnInputTextViewListener;
import com.zby.view.inputview.listener.ViewType;
import com.zby.view.inputview.model.BaseSubmitModel;
import com.zby.view.inputview.model.SubmitViewModel;
import com.zby.view.inputview.multi.InputViewMultiItem;
import com.zby.view.inputview.util.PListUtils;
import com.zby.view.inputview.util.PTimeUtil;
import com.zby.view.inputview.util.TimeBottomSelectHelp;
import com.zby.view.inputview.util.ToastUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.zby.view.inputview.listener.ViewType.TYPE_EDIT;
import static com.zby.view.inputview.listener.ViewType.TYPE_IMAGE;
import static com.zby.view.inputview.listener.ViewType.TYPE_RADIO;
import static com.zby.view.inputview.listener.ViewType.TYPE_REASON;
import static com.zby.view.inputview.listener.ViewType.TYPE_SPINNER;
import static com.zby.view.inputview.listener.ViewType.TYPE_TEXT;
import static com.zby.view.inputview.listener.ViewType.TYPE_TIME;
import static com.zby.view.inputview.listener.ViewType.TYPE_TITLE;

/**
 * Created by zby on 2019/7/1.
 */
public class InputViewAdapter extends BaseMultiItemQuickAdapter<InputViewMultiItem, BaseViewHolder> {
    Context context;
    boolean isEnable = true;

    List<InputViewMultiItem> list;


    public InputViewAdapter(List<InputViewMultiItem> data, Context context) {
        super(data);
        this.list = data;
        this.context = context;
        addItemType(TYPE_TITLE, R.layout.adapter_title_view_item);
        addItemType(TYPE_TEXT, R.layout.adapter_text_view_item);
        addItemType(TYPE_EDIT, R.layout.adapter_edit_view_item);
        addItemType(TYPE_SPINNER, R.layout.adapter_spinner_view_item);
        addItemType(TYPE_TIME, R.layout.adapter_text_view_item);
        addItemType(TYPE_RADIO, R.layout.adapter_radio_view_item);
        addItemType(TYPE_REASON, R.layout.adapter_edit_reason_view_item);
        addItemType(TYPE_IMAGE, R.layout.adapter_image_view_item);
    }

    public void isViewEnable(boolean isEnable) {
        this.isEnable = isEnable;
    }

    @Override
    protected void convert(BaseViewHolder helper, final InputViewMultiItem bean) {
        TextView keyView = helper.getView(R.id.tv_input_key);
        ImageView view = helper.getView(R.id.tv_input_star);

        keyView.setTextSize(bean.getKeyTextSize());
        helper.setText(R.id.tv_input_key, bean.getKey())
                .setTextColor(R.id.tv_input_key, getColor(bean.getKeyTextColor()));
        view.setVisibility(bean.isMust() ? View.VISIBLE : View.GONE);
        switch (helper.getItemViewType()) {
            case TYPE_TEXT:
                initItemTextView(helper, bean);
                break;
            case TYPE_EDIT:
                InputClearEditText inputEditView = helper.getView(R.id.et_input_value);
                setViewTextAttr(helper, inputEditView, bean);
                addEditListener(helper, bean);
                inputEditView.setText(bean.getValue());
                inputEditView.setInputType(bean.isNumber() ? InputType.TYPE_CLASS_NUMBER : InputType.TYPE_CLASS_TEXT);
                setViewEnable(inputEditView, view);
                break;
            case TYPE_REASON:
                EditText reasonEditView = helper.getView(R.id.et_input_value);
                setViewTextAttr(helper, reasonEditView, bean);
                addEditListener(helper, bean);
                reasonEditView.setText(bean.getValue());
                setViewEnable(reasonEditView, view);
                break;
            case TYPE_SPINNER:
                FieldSpinner spinner = helper.getView(R.id.sp_input_value);
                if (!bean.isEnable()) {
                    spinner.setEnabled(false);
                }
                setViewTextAttr(helper, spinner, bean);
                initSpinnerView(keyView, spinner, bean);
                setViewEnable(spinner, view);
                break;
            case TYPE_TIME:
                final TextView tv = helper.getView(R.id.tv_input_value);
                setViewTextAttr(helper, tv, bean);
                tv.setText(bean.getValue());
                tv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        showTimeSelect(tv, bean);
                    }
                });
                setViewEnable(tv, view);
                break;
            case TYPE_RADIO:
                intRadioView(helper, bean);
                break;
            case TYPE_TITLE:
                break;
            case TYPE_IMAGE:
                InputImageRecyclerView inputImageRecyclerView = helper.getView(R.id.mInputImageView);
                inputImageRecyclerView.with((Activity) context).startCamera(bean);
                if (bean.getB() != null) {
                    inputImageRecyclerView.setValue(bean.getB());
                }
                break;
        }
    }

    private void addEditListener(BaseViewHolder helper, InputViewMultiItem bean) {
        if (!bean.isAddListener()) return;
        EditText editText = helper.getView(R.id.et_input_value);
        TextView keyView = helper.getView(R.id.tv_input_key);
        final String keyValue = keyView.getText().toString();

        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                if (editListener != null)
                    editListener.beforeTextChanged(keyValue, s, start, count, after);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (editListener != null)
                    editListener.onTextChanged(keyValue, s, start, before, count);
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (editListener != null) editListener.afterTextChanged(keyValue, s);
            }
        };
        editText.addTextChangedListener(textWatcher);
    }

    private void setViewEnable(View viewEnable, ImageView view) {
        if (isEnable) return;
        viewEnable.setEnabled(false);
        viewEnable.setClickable(false);
        view.setVisibility(View.GONE);
        if (viewEnable instanceof EditText) ((EditText) viewEnable).setHint("");
        if (viewEnable instanceof TextView) ((TextView) viewEnable).setHint("");
    }

    private void setViewTextAttr(BaseViewHolder helper, View view, InputViewMultiItem bean) {
        if (view instanceof EditText) {
            EditText editText = (EditText) view;
            editText.setTextColor(getColor(bean.getValueTextColor()));
            editText.setTextSize(bean.getValueTextSize());
            if (!TextUtils.isEmpty(bean.getHint())) editText.setHint(bean.getHint());
        } else if (view instanceof TextView) {
            TextView textView = (TextView) view;
            textView.setTextSize(bean.getValueTextSize());
            textView.setTextColor(getColor(bean.getValueTextColor()));
            if (!TextUtils.isEmpty(bean.getHint())) textView.setHint(bean.getHint());
        } else if (view instanceof FieldSpinner) {
            FieldSpinner spinner = (FieldSpinner) view;
            spinner.setTextSizeAndColor(bean.getValueTextSize(), bean.getValueTextColor());
        }

        initBgView(helper, view, bean);
    }

    private void initBgView(BaseViewHolder helper, View view, InputViewMultiItem bean) {
        //设置valueView背景颜色
//        view.setBackgroundColor(context.getResources().getColor(bean.getValueViewBackground()));

        //设置keyView 背景颜色
        int length = bean.getKeyViewLength();
        if (length == 0) return;
        LinearLayout keyRootView = helper.getView(R.id.key_viewRoot);
        keyRootView.setBackgroundColor(context.getResources().getColor(bean.getKeyViewBackground()));
        ViewGroup.LayoutParams params = keyRootView.getLayoutParams();
        params.width = PListUtils.dip2px(context, bean.getKeyViewLength());
        keyRootView.setLayoutParams(params);
    }

    private void intRadioView(BaseViewHolder helper, InputViewMultiItem bean) {
        RadioGroup radioGroup = helper.getView(R.id.rg_check);
        RadioButton radioOne = helper.getView(R.id.radio_one);
        RadioButton radioTwo = helper.getView(R.id.radio_two);

        radioOne.setTextSize(bean.getValueTextSize());
        radioOne.setTextColor(getColor(bean.getValueTextColor()));
        radioTwo.setTextSize(bean.getValueTextSize());
        radioTwo.setTextColor(getColor(bean.getValueTextColor()));

        initBgView(helper, radioGroup, bean);

        ImageView view = helper.getView(R.id.tv_input_star);
        radioOne.setText(bean.getRadioOne());
        radioTwo.setText(bean.getRadioTwo());

        if (bean.getDefaultRadioKey() == 1) {
            radioOne.setChecked(true);
        } else {
            radioTwo.setChecked(true);
        }

        setViewEnable(radioGroup, view);
    }


    private void initItemTextView(final BaseViewHolder helper, final InputViewMultiItem bean) {
        TextView textView = helper.getView(R.id.tv_input_value);
        textView.setText(bean.getValue());
        setViewTextAttr(helper, textView, bean);
        if (!bean.isAddListener()) return;
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (textListener != null)
                    textListener.clickItemView(helper, helper.getLayoutPosition());
            }
        });
    }

    private void initSpinnerView(TextView keyView, FieldSpinner mResultSp, InputViewMultiItem bean) {
        final String keyValue = keyView.getText().toString().trim();
        mResultSp.setData(bean.getFields(), bean.getSelectValue(), bean.getHint());
        if (!bean.isAddListener()) return;
        mResultSp.setOnSpinnerSelectedListener(new FieldSpinner.OnSpinnerSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> var1, View var2, int var3, long var4) {
                if (spinnerListener != null)
                    spinnerListener.onItemSelected(keyValue, var1, var2, var3, var4);
            }

            @Override
            public void onNothingSelected(AdapterView<?> var1) {
                if (spinnerListener != null) spinnerListener.onNothingSelected(keyValue, var1);
            }
        });
    }

    private void showTimeSelect(final TextView view, InputViewMultiItem bean) {
        InputMethodManager imm = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);

        final String pattern = bean.isNeedHMS() ? "yyyy-MM-dd HH:mm:ss" : "yyyy-MM-dd";
        Date selectDate = PTimeUtil.strToDate(view.getText().toString(), pattern);
        TimeBottomSelectHelp.showBottomTimeDialog((AppCompatActivity) context, selectDate, bean.isNeedHMS(), bean.isTimeDialog(), new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {//选中事件回调
                String time = PTimeUtil.dateToStr(date, pattern);
                if (!TextUtils.isEmpty(time)) view.setText(time);
            }
        });
    }

    /**
     * @param requestCode
     * @param resultCode
     * @param data        type = 7表示图片
     */
    public void refreshInputImages(int requestCode, int resultCode, Intent data) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getType() == TYPE_IMAGE) {
                InputImageRecyclerView superPickView = (InputImageRecyclerView) getViewByPosition(i, R.id.mInputImageView);
                superPickView.onActivityResult(requestCode, resultCode, data);
                break;
            }
        }
    }


    /**
     * 获取提交list集合
     *
     * @return
     */
    public List<SubmitViewModel> getSubmitListData() {
        List<SubmitViewModel> dataList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            InputViewMultiItem multiItem = list.get(i);
            switch (multiItem.getType()) {
                case TYPE_TEXT:
                    TextView textView = (TextView) getViewByPosition(i, R.id.tv_input_value);
                    SubmitViewModel textModel = new SubmitViewModel(textView.getText().toString().trim());
                    textModel.setSubKey(multiItem.getSubKey());
                    BaseSubmitModel baseBean = (BaseSubmitModel) list.get(i).getB();
                    if (baseBean != null) textModel.setSubValue(baseBean.getSubValue());
                    if (multiItem.getB() != null)
                        textModel.setBean((BaseSubmitModel) list.get(i).getB());

                    if (list.get(i).isMust() && TextUtils.isEmpty(textView.getText().toString().trim())) {
                        textModel.setMust(true);
                    }
                    dataList.add(textModel);
                    break;
                case TYPE_EDIT:
                case TYPE_REASON:
                    EditText editText = (EditText) getViewByPosition(i, R.id.et_input_value);
                    String editValue = editText.getText().toString().trim();
                    SubmitViewModel model = new SubmitViewModel(editValue, multiItem.getSubKey(), editValue);
                    if (list.get(i).isMust() && TextUtils.isEmpty(editText.getText().toString().trim())) {
                        model.setMust(true);
                    }
                    dataList.add(model);
                    break;
                case TYPE_SPINNER:
                    FieldSpinner spinner = (FieldSpinner) getViewByPosition(i, R.id.sp_input_value);
                    DictField field = (DictField) spinner.getSelectedItem();
                    SubmitViewModel spinnerModel = new SubmitViewModel();
                    if (field != null) {
                        spinnerModel.setSelectKey(field.getName());
                        spinnerModel.setValue((String) spinner.getValue());
                        spinnerModel.setSubKey(multiItem.getSubKey());
                        spinnerModel.setSubValue((String) spinner.getValue());
                    }
                    dataList.add(spinnerModel);
                    break;
                case TYPE_TIME:
                    TextView timeView = (TextView) getViewByPosition(i, R.id.tv_input_value);
                    String time = timeView.getText().toString().trim();
                    SubmitViewModel model1 = new SubmitViewModel(time, multiItem.getSubKey(), time);

                    if (list.get(i).isMust() && TextUtils.isEmpty(time)) {
                        model1.setMust(true);
                    }
                    dataList.add(model1);
                    break;
                case TYPE_RADIO:
                    RadioGroup group = (RadioGroup) getViewByPosition(i, R.id.rg_check);
                    boolean isOne = group.getCheckedRadioButtonId() == R.id.radio_one;
                    String type = isOne ? ViewType.RADIO_ONE + "" : ViewType.RADIO_TWO + "";
                    SubmitViewModel radioModel = new SubmitViewModel(type);
                    radioModel.setSelectKey(multiItem.getSubKey());
                    radioModel.setSubValue(type);
                    dataList.add(radioModel);
                    break;
            }
        }
        return dataList;
    }

    /**
     * 注：当传入subKey的时候才会有对应的键值关系
     *
     * @return
     */
    public Map<String, Object> getSubmitMapData() {
        List<SubmitViewModel> commitViewData = getSubmitListData();
        Map<String, Object> map = new HashMap<>();
        for (SubmitViewModel model : commitViewData) {
            map.put(model.getSubKey(), model.getSubValue());
        }
        return map;
    }

    /**
     * 注：需传入subKey字段
     * 返回json字符串
     *
     * @return
     */
    public String getSubmitJsonData() {
        return new JSONObject(getSubmitMapData()).toString();
    }


    /**
     * 将封装值转化为对象类型
     * 注：需传入subKey
     *
     * @param clazz 传入对象
     * @param <T>
     * @return
     */
    public <T> T getSubmitClassData(Class<T> clazz) {
        JSONObject jsonObject = new JSONObject(getSubmitMapData());
        return jsonObject.toJavaObject(clazz);
    }

    //判断是否必填
    public boolean isMustInput() {
        boolean isMust = true;
        List<SubmitViewModel> commitViewData = getSubmitListData();
        if (PListUtils.isEmpty(commitViewData)) isMust = false;
        for (SubmitViewModel model : commitViewData) {
            if (model.isMust()) {
                ToastUtil.warningShort(mContext, "请填写必填项");
                isMust = false;
                break;
            }
        }
        return isMust;
    }


    private int getColor(int color) {
        return context.getResources().getColor(color);
    }

    OnInputTextViewListener textListener;
    OnInputEditViewListener editListener;
    OnInputSpinnerSelectedListener spinnerListener;

    public void setOnInputTextViewListener(OnInputTextViewListener listener) {
        this.textListener = listener;
    }

    public void setOnInputEditViewListener(OnInputEditViewListener listener) {
        this.editListener = listener;
    }

    public void setOnInputSpinnerSelectedListener(OnInputSpinnerSelectedListener listener) {
        this.spinnerListener = listener;
    }

}
