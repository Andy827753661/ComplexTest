
package com.bobo.complextest.Entitiy;

/**
 * Created by Administrator on 2017/3/27.
 */

public class UserBean {

    private String mUserName;

    private String mPassword;

    public UserBean(String mUserName, String mPassword) {
        this.mUserName = mUserName;
        this.mPassword = mPassword;
    }

    public String getUserName() {
        return mUserName;
    }

    public void setUserName(String userName) {
        this.mUserName = userName;
    }

    public String getPassword() {
        return mPassword;
    }

    public void setPassword(String password) {
        this.mPassword = password;
    }
}
