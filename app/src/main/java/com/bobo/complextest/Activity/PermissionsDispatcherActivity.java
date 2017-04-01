
package com.bobo.complextest.Activity;

import com.bobo.complextest.R;
import com.bobo.complextest.Base.BaseActivity;
import com.bobo.complextest.Interface.PermissionHandler;
import com.bobo.complextest.Utils.ToastUtil;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/3/30.
 */
// @RuntimePermissions
public class PermissionsDispatcherActivity extends BaseActivity {

    @OnClick(R.id.btn_call_10086)
    void callEvent() {
        // PermissionsDispatcherActivityPermissionsDispatcher.startCallWithCheck(this);
        requestCallPermission(new PermissionHandler() {
            @Override
            public void onGranted() {
                ToastUtil.show("取得 电话 权限");
                startCall();
            }

            @Override
            public void onDenied() {
                ToastUtil.show("拒绝 电话 权限");
            }
        });
    }

    private void startCall() {
        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + 10086));
        startActivity(intent);
    }

    @OnClick(R.id.btn_camera)
    void cameraEvent() {
        requestCameraPermission(new PermissionHandler() {
            @Override
            public void onGranted() {
                ToastUtil.show("取得 拍照 权限");
            }

            @Override
            public void onDenied() {
                ToastUtil.show("拒绝 拍照 权限");
            }
        });
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permission_dispatch);
        ButterKnife.bind(this);
    }
}
