package com.bobo.complextest.Interface;

/**
 * Created by Administrator on 2017/3/31.
 * 权限回调接口
 */

public interface PermissionHandler {

    /**
     * 权限通过
     */
    void onGranted();

    /**
     * 权限拒绝
     */
    void onDenied();
}
