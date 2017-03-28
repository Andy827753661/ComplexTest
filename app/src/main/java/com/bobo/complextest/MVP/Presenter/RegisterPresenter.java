
package com.bobo.complextest.MVP.Presenter;

import com.bobo.complextest.Entitiy.UserBean;
import com.bobo.complextest.Interface.HttpListener;
import com.bobo.complextest.MVP.Model.RegisterModel;
import com.bobo.complextest.MVP.View.IRegisterView;

/**
 * Created by Administrator on 2017/3/27.
 */

public class RegisterPresenter implements HttpListener {
    private IRegisterView iRegisterView;

    private RegisterModel module;

    public RegisterPresenter(IRegisterView iRegisterView) {
        this.iRegisterView = iRegisterView;
        module = new RegisterModel(this);
    }

    public void setUser(UserBean user) {
        module.registerUser(user);
    }

    @Override
    public void onSucceed(Object o) {
        iRegisterView.registerSucceed((UserBean) o);
    }

    @Override
    public void onFail(Object o) {
        iRegisterView.registerFail((UserBean)o);
    }

    @Override
    public void onFinish() {

    }
}
