
package com.bobo.complextest.Component;

import com.bobo.complextest.Activity.Dagger2Activity;
import com.bobo.complextest.Module.LoginModule;

import dagger.Component;

/**
 * Created by Administrator on 2017/3/27.
 */
@Component(modules = LoginModule.class)
public interface LoginComponent {
    public void inject(Dagger2Activity activity);
}
