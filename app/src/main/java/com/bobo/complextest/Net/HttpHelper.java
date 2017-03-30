
package com.bobo.complextest.Net;

import com.bobo.complextest.Api.CacheApi;
import com.bobo.complextest.Api.RESTApi;
import com.bobo.complextest.Entitiy.BookInfoDto;
import com.bobo.complextest.Entitiy.HttpResult;
import com.bobo.complextest.Utils.FileUtil;
import com.orhanobut.logger.Logger;

import io.rx_cache.DynamicKey;
import io.rx_cache.EvictDynamicKey;
import io.rx_cache.Reply;
import io.rx_cache.internal.RxCache;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2017/3/13.
 */

public class HttpHelper {

    private RESTApi mRequestRxJava1 = new RetrofitHelperRxJava1().getRetrofit()
            .create(RESTApi.class);

    private CacheApi mCache = new RxCache.Builder().persistence(FileUtil.getCacheDirectory())
            .using(CacheApi.class);

    private HttpHelper() {
    }

    private static class SingletonHolder {
        private final static HttpHelper mInstance = new HttpHelper();
    }

    // 在访问时创建单例
    public static HttpHelper getHttpHelper() {
        return SingletonHolder.mInstance;
    }

    // 获取书籍详情by RxJava1
    public void getBookInfoByRxJava1(int id, Observer<BookInfoDto> observer) {
        Observable<BookInfoDto> observable = mRequestRxJava1.getBookInfo(id)
                .map(new HttpResultFunc<BookInfoDto>());
        Observable observableCache = mCache
                .getBookInfo(observable, new DynamicKey(id), new EvictDynamicKey(false))
                .map(new HttpRequestFuncCache<BookInfoDto>());
        setSubscribe(observableCache, observer);
    }

    private static <T> void setSubscribe(Observable<T> observable, Observer<T> observer) {
        observable.subscribeOn(Schedulers.io()).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(observer);
    }

    /**
     * 用来统一处理Http的resultCode,并将HttpResult的Data部分剥离出来返回给subscriber
     *
     * @param <T> Subscriber真正需要的数据类型，也就是Data部分的数据类型
     */
    private class HttpResultFunc<T> implements Func1<HttpResult<T>, T> {

        @Override
        public T call(HttpResult<T> httpResult) {
            Logger.w("Request-Result  ==> ", httpResult.getData().toString());
            return httpResult.getData();
        }
    }

    /**
     * 用来统一处理RxCacha的结果
     */
    private class HttpRequestFuncCache<T> implements Func1<Reply<T>, T> {

        @Override
        public T call(Reply<T> tReply) {
            Logger.w("Cache-Result  ==> " + tReply.getData().toString());
            return tReply.getData();
        }
    }
}
