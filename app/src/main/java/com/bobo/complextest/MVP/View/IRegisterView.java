package com.bobo.complextest.MVP.View;

import com.bobo.complextest.Entitiy.UserBean;

/**
 * Created by Administrator on 2017/3/27.
 */

public interface IRegisterView {

    void registerSucceed(UserBean user);

    void registerFail(UserBean user);
}
