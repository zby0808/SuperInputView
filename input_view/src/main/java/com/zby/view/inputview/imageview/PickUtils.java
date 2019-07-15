package com.zby.view.inputview.imageview;

import android.app.Activity;

import com.example.input_view.R;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;

import java.util.List;

/**
 * Created by zby on 2019/7/1.
 */
public class PickUtils {
    public static void comm(Activity activity, List<LocalMedia> list, int maxSelectNum, int imageSpanCount, int requestCode) {
        PictureSelector.create(activity)
                .openGallery(PictureMimeType.ofImage())// 全部.PictureMimeType.ofAll()、图片.ofImage()、视频.ofVideo()、音频.ofAudio()
                .theme(R.style.picture_my_style)// 主题样式设置 具体参考 values/styles   用法：R.style.picture.white.style
                .maxSelectNum(maxSelectNum)// 最大图片选择数量
                .minSelectNum(1)// 最小选择数量
                .imageSpanCount(imageSpanCount)// 每行显示个数
                .selectionMode(PictureConfig.MULTIPLE)// 多选 or 单选
                .previewImage(true)// 是否可预览图片
                .previewVideo(false)// 是否可预览视频
                .enablePreviewAudio(false) // 是否可播放音频
                .isCamera(true)// 是否显示拍照按钮
                .isZoomAnim(true)// 图片列表点击 缩放效果 默认true
                .enableCrop(false)// 是否裁剪
                .compress(true)// 是否压缩
                .synOrAsy(true)//同步true或异步false 压缩 默认同步
                .glideOverride(160, 160)// glide 加载宽高，越小图片列表越流畅，但会影响列表图片浏览的清晰度
                .isGif(false)// 是否显示gif图片
                .openClickSound(false)// 是否开启点击声音
                .selectionMedia(list)// 是否传入已选图片
                .minimumCompressSize(100)// 小于100kb的图片不压缩
                .forResult(requestCode);//结果回调onActivityResult code
    }
}
