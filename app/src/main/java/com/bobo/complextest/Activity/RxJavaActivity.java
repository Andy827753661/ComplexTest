
package com.bobo.complextest.Activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.bobo.complextest.Base.BaseActivity;
import com.bobo.complextest.Entitiy.BookInfoDto;
import com.bobo.complextest.Net.HttpHelper;
import com.bobo.complextest.R;
import com.orhanobut.logger.Logger;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observer;

/**
 * Created by Administrator on 2017/3/30.
 */

public class RxJavaActivity extends BaseActivity {

    @BindView(R.id.tv_book_name)
    TextView mBookName;

    @OnClick(R.id.btn_request_rxjava1)
    void requestData() {
        requestByRxJava();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rxjava);
        ButterKnife.bind(this);
    }

    private void requestByRxJava() {
        HttpHelper.getHttpHelper().getBookInfoByRxJava1(40788, new Observer<BookInfoDto>() {
            @Override
            public void onCompleted() {
                Logger.e("  Observer onCompleted  ");
            }

            @Override
            public void onError(Throwable e) {
                Logger.e("  Observer onError  " + e.toString());
            }

            @Override
            public void onNext(BookInfoDto bookInfoDto) {
                Logger.e("bookInfoDto == " + bookInfoDto.toString());
                mBookName.setText("Retrofit Request BookName:" + bookInfoDto.getBookName());
            }
        });
    }
}
