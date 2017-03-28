
package com.bobo.complextest.Api;

import com.bobo.complextest.Entitiy.BookInfoDto;

import java.util.concurrent.TimeUnit;

import io.rx_cache.DynamicKey;
import io.rx_cache.EvictDynamicKey;
import io.rx_cache.LifeCache;
import io.rx_cache.Reply;
import rx.Observable;

/**
 * Created by Administrator on 2017/3/13.
 */

public interface CacheApi {

    @LifeCache(duration = 5, timeUnit = TimeUnit.MINUTES)
    Observable<Reply<BookInfoDto>> getBookInfo(Observable<BookInfoDto> oRepos,
                                               DynamicKey userName, EvictDynamicKey evictDynamicKey);

}
