
package com.bobo.complextest.Components;

import com.bobo.complextest.Activity.Dagger2Activity;
import com.bobo.complextest.Module.LoginModule;
import com.bobo.complextest.Module.ModuleTestQualifier;

import dagger.Component;

/**
 * Created by Administrator on 2017/3/27.
 */
@Component(modules = {LoginModule.class, ModuleTestQualifier.class})
public interface LoginComponent {
    public void inject(Dagger2Activity activity);
}
