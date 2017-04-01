
package com.bobo.complextest.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.bobo.complextest.Base.BaseActivity;
import com.bobo.complextest.R;
import com.bobo.complextest.Utils.ToastUtil;
import com.jakewharton.rxbinding.view.RxView;

import java.util.concurrent.TimeUnit;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.functions.Action1;

public class MainActivity extends BaseActivity {
    // ButterKnife
    @BindString(R.string.app_name)
    String string;

    @BindView(R.id.btn_butter_knife_test)
    Button mButterKnifeTest;

    @BindView(R.id.btn_rx_binding_test)
    Button mRxBindingTest;

    @BindView(R.id.btn_to_glide_activity)
    Button button6;

    // ButterKnife
    @OnClick(R.id.btn_butter_knife_test)
    void b3Click() {
        Toast.makeText(MainActivity.this, "ButterKnife Test : Btn " + string, Toast.LENGTH_SHORT)
                .show();
    }

    @OnClick(R.id.RxJava_And_Retrofit)
    void rxJava() {
        Intent intent = new Intent(MainActivity.this, RxJavaActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.btn_dagger2_activity)
    void dagger2Click() {
        Intent intent = new Intent(MainActivity.this, Dagger2Activity.class);
        startActivity(intent);
    }

    @OnClick(R.id.btn_permission)
    void permissionEvent() {
        Intent intent = new Intent(MainActivity.this, PermissionsDispatcherActivity.class);
        startActivity(intent);
    }

    private int mCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        // RxBinding
        RxView.clicks(mRxBindingTest).throttleFirst(5, TimeUnit.SECONDS)
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        mCount += 1;
                        ToastUtil.show("ButterKnife + RxBinding Test : 点击第 " + mCount + " 次");
                    }
                });

    }

    @OnClick({
            R.id.btn_to_glide_activity, R.id.btn_download_activity,
    })
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_to_glide_activity:
                Intent intent = new Intent(MainActivity.this, GlideActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_download_activity:
                Intent download = new Intent(MainActivity.this, DownloadActivity.class);
                startActivity(download);
                break;
        }
    }
}
