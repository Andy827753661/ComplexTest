
package com.bobo.complextest.MVP.Model;

import android.text.TextUtils;

import com.bobo.complextest.Entitiy.UserBean;
import com.bobo.complextest.MVP.View.ILoginView;

/**
 * Created by Administrator on 2017/3/27.
 */

public class LoginModel implements ILoginModel {

    private UserBean userBean;

    private ILoginView iLoginView;

    public LoginModel(ILoginView iLoginView) {
        this.iLoginView = iLoginView;
    }

    @Override
    public void setUser(UserBean user) {
        this.userBean = user;
        if (TextUtils.equals("123", user.getUserName())
                && TextUtils.equals("456", user.getPassword())) {
            iLoginView.onSucceed();
        } else {
            iLoginView.onFail();
        }
    }

    @Override
    public UserBean getUser(UserBean user) {
        return userBean;
    }
}
