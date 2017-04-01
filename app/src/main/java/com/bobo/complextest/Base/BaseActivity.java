
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
import permissions.dispatcher.OnShowRationale;
import permissions.dispatcher.PermissionRequest;
import permissions.dispatcher.RuntimePermissions;

/**
 * Created by Administrator on 2017/3/21.
 */
@RuntimePermissions
public class BaseActivity extends AppCompatActivity {

    private PermissionHandler mHandler;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions,
            int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        BaseActivityPermissionsDispatcher.onRequestPermissionsResult(this, requestCode,
                grantResults);
    }

    /**
     * 父类封装的方法，供子类调用 ：请求相机权限
     *
     * @param permissionHandler
     */
    protected void requestCameraPermission(PermissionHandler permissionHandler) {
        this.mHandler = permissionHandler;
        BaseActivityPermissionsDispatcher.handleCameraPermissionWithCheck(this);
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

    // @OnShowRationale(Manifest.permission.CAMERA)
    // void onShowRationaleEvent() {
    // showDialog("[相机]");
    // }

    @OnShowRationale(Manifest.permission.CAMERA)
    void showRationaleForCamera(final PermissionRequest request) {
        new AlertDialog.Builder(this)
                .setPositiveButton("好的", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        request.proceed();
                    }
                }).setNegativeButton("不给", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        request.cancel();
                    }
                }).setCancelable(false).setMessage("拍照需要相机权限，应用将要申请使用相机权限").show();
    }

    @OnNeverAskAgain(Manifest.permission.CAMERA)
    void onCameraNeverAskAgain() {
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
        BaseActivityPermissionsDispatcher.handleCallPermissionWithCheck(this);
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

    //
    // @OnShowRationale(Manifest.permission.CALL_PHONE)
    // void onShowRationale() {
    // showDialog("[电话]");
    // }
    @OnShowRationale(Manifest.permission.CALL_PHONE)
    void showRationaleForCall(final PermissionRequest request) {
        new AlertDialog.Builder(this)
                .setPositiveButton("好的", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        request.proceed();
                    }
                }).setNegativeButton("不给", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        request.cancel();
                    }
                }).setCancelable(false).setMessage("拍照需要电话权限，应用将要申请使用电话权限").show();
    }

    @OnNeverAskAgain(Manifest.permission.CALL_PHONE)
    void onCallNeverAskAgain() {
        showDialog("[电话]");
    }

    public void showDialog(String permission) {
        new AlertDialog.Builder(this).setTitle("权限申请")
                .setMessage("在设置-应用-Complex-权限中开启" + permission + "权限，以正常使用Complex")
                .setPositiveButton("去开启", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                        intent.setData(Uri.parse("package:" + getPackageName()));
                        intent.addCategory(Intent.CATEGORY_DEFAULT);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
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
