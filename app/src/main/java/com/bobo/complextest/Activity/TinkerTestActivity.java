
package com.bobo.complextest.Activity;

import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;

import com.bobo.complextest.Base.BaseActivity;
import com.bobo.complextest.R;
import com.bobo.complextest.Utils.ToastUtil;
import com.tencent.tinker.lib.tinker.TinkerInstaller;

import java.io.File;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/4/4.
 */

public class TinkerTestActivity extends BaseActivity {

    @OnClick(R.id.btn_download_bd)
    void downloadEvent() {

    }

    @OnClick(R.id.btn_delete_bd)
    void deleteEvent() {

    }

    @OnClick(R.id.btn_install_bd)
    void installEvent() {
        String path =
        Environment.getExternalStorageDirectory().getAbsolutePath() + "/patch_signed_7zip.apk";
        File file = new File(path);
        if (file.exists()) {
            ToastUtil.show("哈哈，存在！");
            TinkerInstaller.onReceiveUpgradePatch(getApplicationContext(), path);
        }else{
            ToastUtil.show("唉~不存在！");
        }
    }

    @OnClick(R.id.btn_toast)
    void toastEvent() {
        ToastUtil.show("补丁！补丁！");
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tinker);
        ButterKnife.bind(this);
    }
}
