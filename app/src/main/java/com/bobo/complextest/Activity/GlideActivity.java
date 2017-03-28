
package com.bobo.complextest.Activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bobo.complextest.Base.BaseActivity;
import com.bobo.complextest.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/3/16.
 */

public class GlideActivity extends BaseActivity {

    private static final String mImgUrl = "http://www.kunmma.com/d/file/shishang/95d8dad83ef40ff5ce05c2f5bc0b9174.jpg";

    private static final String mGifUrl = "http://qq.yh31.com/tp/zjbq/201703050124231145.gif";

    private static final String mGifUrl2 = "http://b.hiphotos.baidu.com/zhidao/pic/item/faedab64034f78f066abccc57b310a55b3191c67.jpg";

    // 图片太大
    private static final String mGifUrl3 = "http://s1.dwstatic.com/group1/M00/B7/01/af7f1d365b5b91f99b1baed5fd5be032.gif";

    // 获取assets文件夹中的文件
    // private String assets1 = "file:///assets/jsy.jpg";

    @OnClick(R.id.btn_load_static_img_from_net)
    public void clickImg() {
        // Glide.with(context).load(url).thumbnail(0.1f).skipMemoryCache(true).into(imageView);
        Glide.with(this).load(mImgUrl).asBitmap().into(mImgContainer);
    }

    @BindView(R.id.iv_gif_container_drawable)
    ImageView mGifContainerDrawable;

    @OnClick(R.id.btn_load_gif_from_drawable)
    void loadGifFromDrawable() {
        Glide.with(this).load(R.drawable.test).placeholder(R.drawable.default_img)// 加载占位图
                .error(R.drawable.error_img)//
                .diskCacheStrategy(DiskCacheStrategy.NONE)// 禁用Glide的缓存功能！！！
                .into(mGifContainerDrawable);
    }

    @BindView(R.id.btn_load_gif_from_net)
    Button mLoadGif;

    public void clickGif() {
        Glide.with(this).load(mGifUrl).asGif().into(mGifContainer);
    }
    @BindView(R.id.iv_static_img_container)
    ImageView mImgContainer;

    @BindView(R.id.iv_gif_container_net)
    ImageView mGifContainer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_glide_test);
        ButterKnife.bind(this);
        mLoadGif.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickGif();
            }
        });
    }
}
