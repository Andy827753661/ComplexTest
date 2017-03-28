
package com.bobo.complextest.MVP.Presenter;

import com.bobo.complextest.Entitiy.UserBean;
import com.bobo.complextest.MVP.Model.LoginModel;
import com.bobo.complextest.MVP.View.ILoginView;

import javax.inject.Inject;

/**
 * Created by Administrator on 2017/3/27.
 */

public class LoginPresenterByDagger2 {

    private LoginModel loginModule;

    @Inject
    public LoginPresenterByDagger2(ILoginView iLoginView) {
        loginModule = new LoginModel(iLoginView);
    }

    public void setUser(UserBean user) {
        loginModule.setUser(user);
    }
}
