
package com.bobo.complextest.Api;

import com.bobo.complextest.Constant.ApiConstant;
import com.bobo.complextest.Entitiy.BookInfoDto;
import com.bobo.complextest.Entitiy.HttpResult;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Administrator on 2017/3/10.
 */

public interface RESTApi {

    @GET(ApiConstant.Api.BOOK_INFO_API)
    Observable<HttpResult<BookInfoDto>> getBookInfo(@Query(ApiConstant.Key.ID) int id);

}
