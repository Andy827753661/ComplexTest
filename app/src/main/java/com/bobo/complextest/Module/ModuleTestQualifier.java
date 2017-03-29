
package com.bobo.complextest.Module;

import com.bobo.complextest.Entitiy.UserBean;
import com.bobo.complextest.Annotation.A;
import com.bobo.complextest.Annotation.B;
import com.bobo.complextest.MVP.View.ILoginView;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Administrator on 2017/3/28.
 */

@Module
public class ModuleTestQualifier {
    ILoginView view;

    UserBean user1;

    UserBean user2;

    public ModuleTestQualifier(ILoginView view, UserBean user1, UserBean user2) {
        this.view = view;
        this.user1 = user1;
        this.user2 = user2;
    }

    @Provides
    @A
    public UserBean provideUser1() {
        return user1;
    }

    @Provides
    @B
    public UserBean provideUser2() {
        return user2;
    }
}
