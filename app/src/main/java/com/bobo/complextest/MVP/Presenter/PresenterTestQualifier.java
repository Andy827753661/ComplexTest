
package com.bobo.complextest.MVP.Presenter;

import com.bobo.complextest.Entitiy.UserBean;
import com.bobo.complextest.Interface.A;
import com.bobo.complextest.Interface.B;
import com.bobo.complextest.MVP.Model.LoginModel;
import com.bobo.complextest.MVP.View.ILoginView;

import javax.inject.Inject;

/**
 * Created by Administrator on 2017/3/28.
 */

public class PresenterTestQualifier {
    UserBean user1;

    UserBean user2;

    LoginModel model;

    @Inject
    public PresenterTestQualifier(ILoginView view, @A UserBean user1, @B UserBean user2) {
        this.user1 = user1;
        this.user2 = user2;
        model = new LoginModel(view);
    }

    public void setUser(int index) {
        if ((index % 2) == 0) {
            model.setUser(user1);
        } else if ((index % 2) == 1) {
            model.setUser(user2);
        }
    }
}
