
package com.bobo.complextest.Base;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

import com.bobo.complextest.Interface.PermissionHandler;

import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.OnNeverAskAgain;
import permissions.dispatcher.OnPermissionDenied;
import permissions.dispatcher.RuntimePermissions;

/**
 * Created by Administrator on 2017/3/31. 对权限适配的封装
 */
@RuntimePermissions
public class BasePermissionActivity extends AppCompatActivity {

    private PermissionHandler mHandler;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions,
            int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        BasePermissionActivityPermissionsDispatcher.onRequestPermissionsResult(this, requestCode,
                grantResults);
    }

    /**
     * 请求相机权限
     *
     * @param permissionHandler
     */
    protected void requestCameraPermission(PermissionHandler permissionHandler) {
        this.mHandler = permissionHandler;
        BasePermissionActivityPermissionsDispatcher.handleCameraPermissionWithCheck(this);
    }

    @NeedsPermission(Manifest.permission.CAMERA)
    void handleCameraPermission() {
        if (mHandler != null)
            mHandler.onGranted();
    }

    @OnPermissionDenied(Manifest.permission.CAMERA)
    void deniedCameraPermission() {
        if (mHandler != null)
            mHandler.onDenied();
    }

    @OnNeverAskAgain(Manifest.permission.CAMERA)
    void OnCameraNeverAskAgain() {
        showDialog("[相机]");
    }

    // -----------------------------------------------------------
    /**
     * 请求电话权限
     *
     * @param permissionHandler
     */
    protected void requestCallPermission(PermissionHandler permissionHandler) {
        this.mHandler = permissionHandler;
        BasePermissionActivityPermissionsDispatcher.handleCallPermissionWithCheck(this);
    }

    @NeedsPermission(Manifest.permission.CALL_PHONE)
    void handleCallPermission() {
        if (mHandler != null)
            mHandler.onGranted();
    }

    @OnPermissionDenied(Manifest.permission.CALL_PHONE)
    void deniedCallPermission() {
        if (mHandler != null)
            mHandler.onDenied();
    }

    @OnNeverAskAgain(Manifest.permission.CALL_PHONE)
    void OnCallNeverAskAgain() {
        showDialog("[电话]");
    }

    public void showDialog(String permission) {
        new AlertDialog.Builder(this).setTitle("权限申请")
                .setMessage("在设置-应用-荟医医生-权限中开启" + permission + "权限，以正常使用荟医功能")
                .setPositiveButton("去开启", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                        Uri uri = Uri.fromParts("package", getPackageName(), null);
                        intent.setData(uri);
                        startActivity(intent);

                        dialog.dismiss();
                    }
                }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (mHandler != null)
                            mHandler.onDenied();
                        dialog.dismiss();
                    }
                }).setCancelable(false).show();
    }

}
