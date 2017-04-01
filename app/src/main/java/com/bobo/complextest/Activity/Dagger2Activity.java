
package com.bobo.complextest.Activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.EditText;

import com.bobo.complextest.Base.BaseActivity;
import com.bobo.complextest.Components.DaggerLoginComponent;
import com.bobo.complextest.Entitiy.UserBean;
import com.bobo.complextest.MVP.Presenter.LoginPresenter;
import com.bobo.complextest.MVP.Presenter.LoginPresenterByDagger2;
import com.bobo.complextest.MVP.Presenter.PresenterTestQualifier;
import com.bobo.complextest.MVP.Presenter.RegisterPresenter;
import com.bobo.complextest.MVP.View.ILoginView;
import com.bobo.complextest.MVP.View.IRegisterView;
import com.bobo.complextest.Module.LoginModule;
import com.bobo.complextest.Module.ModuleTestQualifier;
import com.bobo.complextest.R;
import com.bobo.complextest.Utils.ToastUtil;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/3/24.
 */

public class Dagger2Activity extends BaseActivity implements ILoginView, IRegisterView {

    private LoginPresenter mLoginPresenter;

    private RegisterPresenter mRegisterPresenter;

    @Inject
    LoginPresenterByDagger2 loginPresenterByDagger2;

    @Inject
    PresenterTestQualifier presenterTestQualifier;

    @BindView(R.id.et_username)
    EditText mUserName;

    @BindView(R.id.et_password)
    EditText mPassword;

    private int mIndex;

    @OnClick(R.id.btn_login)
    void loginEvent() {// 节俭型MVP：把module的interface直接implements在presenter中
        String userName = mUserName.getText().toString();
        String password = mPassword.getText().toString();
        mLoginPresenter.setUser(new UserBean(userName, password));
    }

    @OnClick(R.id.btn_register)
    void registerEvent() {// 常规型MVP：持有Module和View的对象
        String userName = mUserName.getText().toString();
        String password = mPassword.getText().toString();
        mRegisterPresenter.setUser(new UserBean(userName, password));
    }

    @OnClick(R.id.btn_dagger2_test)
    void dagger2LoginTest() {
        String userName = mUserName.getText().toString();
        String password = mPassword.getText().toString();
        if (loginPresenterByDagger2 == null) {
            ToastUtil.show("dagger2 fail");
            return;
        }
        loginPresenterByDagger2.setUser(new UserBean(userName, password));
    }

    @OnClick(R.id.btn_dagger2_qualifier)
    void qualifierTest() {
        mIndex += 1;
        if (presenterTestQualifier == null) {
            ToastUtil.show("dagger2 Qualifier fail");
            return;
        }
        presenterTestQualifier.setUser(mIndex);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dagger2);
        ButterKnife.bind(this);
        mLoginPresenter = new LoginPresenter(this);
        mRegisterPresenter = new RegisterPresenter(this);
        // dagger2
        UserBean user1 = new UserBean("1", "1");
        UserBean user2 = new UserBean("2", "2");
        DaggerLoginComponent.builder()
                .moduleTestQualifier(new ModuleTestQualifier(this, user1, user2))
                .loginModule(new LoginModule(this)).build().inject(this);
    }

    @Override
    public void onSucceed() {
        ToastUtil.show("登录成功");
    }

    @Override
    public void onFail() {
        ToastUtil.show("用户名或者密码错误");
        // mUserName.setError();
    }

    @Override
    public void registerSucceed(UserBean user) {
        ToastUtil.show("注册成功" + "：" + user.getUserName());
    }

    @Override
    public void registerFail(UserBean user) {
        ToastUtil.show("注册失败");
    }
}
