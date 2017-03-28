
package com.bobo.complextest.Entitiy;

import com.orhanobut.logger.Logger;

import javax.inject.Inject;

/**
 * Created by Administrator on 2017/3/24.
 */

public class ClassA {

    @Inject
    ClassB b;

    @Inject
    ClassC c;

    @Inject
    ClassD d;

    @Inject
    public ClassA() {
    }

    public void printStr() {
        String s = b.toString() + c.toString() + d.toString();
        Logger.e("TEST-DATA"+s);
    }
}
