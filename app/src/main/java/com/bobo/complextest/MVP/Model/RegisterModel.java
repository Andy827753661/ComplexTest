
package com.bobo.complextest.MVP.Model;

import android.text.TextUtils;

import com.bobo.complextest.Entitiy.UserBean;
import com.bobo.complextest.Interface.HttpListener;

/**
 * Created by Administrator on 2017/3/27.
 */

public class RegisterModel implements IRegisterModel {

    HttpListener listener;

    public RegisterModel(HttpListener listener) {
        this.listener = listener;
    }

    @Override
    public void registerUser(UserBean user) {
        // TODO 注册的网络请求
        if (!TextUtils.isEmpty(user.getUserName()) && !TextUtils.isEmpty(user.getPassword())) {
            listener.onSucceed(user);
        } else {
            listener.onFail(user);
        }
    }
}
