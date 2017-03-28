
package com.bobo.complextest.MVP.Model;

import com.bobo.complextest.Entitiy.UserBean;

/**
 * Created by Administrator on 2017/3/27.
 */

public interface ILoginModel {

    void setUser(UserBean user);

    UserBean getUser(UserBean user);
}
