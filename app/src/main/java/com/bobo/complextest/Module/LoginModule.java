
package com.bobo.complextest.Module;

import com.bobo.complextest.MVP.View.ILoginView;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Administrator on 2017/3/27.
 */

@Module
public class LoginModule {

    private final ILoginView view;

    public LoginModule(ILoginView view) {
        this.view = view;
    }

    @Provides
    ILoginView provideILoginView() {
        return view;
    }
}
