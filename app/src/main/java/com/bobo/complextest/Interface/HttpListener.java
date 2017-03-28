package com.bobo.complextest.Interface;

/**
 * Created by Administrator on 2017/3/27.
 */

public interface HttpListener {

    void onSucceed(Object o);

    void onFail(Object o);

    void onFinish();

}
