
package com.bobo.complextest.Application;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.support.multidex.MultiDex;

import com.orhanobut.logger.Logger;
import com.tencent.tinker.anno.DefaultLifeCycle;
import com.tencent.tinker.lib.tinker.TinkerInstaller;
import com.tencent.tinker.loader.app.DefaultApplicationLike;
import com.tencent.tinker.loader.shareutil.ShareConstants;
import com.yanzhenjie.nohttp.NoHttp;
import com.yanzhenjie.nohttp.OkHttpNetworkExecutor;
import com.yanzhenjie.nohttp.cache.DBCacheStore;
import com.yanzhenjie.nohttp.cookie.DBCookieStore;

/**
 * Created by Administrator on 2017/3/10.
 */
@DefaultLifeCycle(application = "ook.yzx.tinker.Application", flags = ShareConstants.TINKER_ENABLE_ALL)
public class MyApplication extends DefaultApplicationLike {

    private static Context mContext;

    private static final String TAG = "MyTag";

    public MyApplication(Application application, int tinkerFlags, boolean tinkerLoadVerifyFlag,
            long applicationStartElapsedTime, long applicationStartMillisTime,
            Intent tinkerResultIntent) {
        super(application, tinkerFlags, tinkerLoadVerifyFlag, applicationStartElapsedTime,
                applicationStartMillisTime, tinkerResultIntent);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplication().getApplicationContext();
        initLogger();
        initNoHttp();
    }

    private void initNoHttp() {
        NoHttp.initialize(mContext,
                new NoHttp.Config().setNetworkExecutor(new OkHttpNetworkExecutor())
                        // 设置全局连接超时时间，单位毫秒
                        .setConnectTimeout(30 * 1000)
                        // 设置全局服务器响应超时时间，单位毫秒
                        .setReadTimeout(30 * 1000)
                        .setCacheStore(
                                // 保存到数据库
                                new DBCacheStore(mContext).setEnable(true) // 如果不使用缓存，设置false禁用。
                        // 或者保存到SD卡：new DiskCacheStore(this)
                        )
                        // 默认保存数据库DBCookieStore，开发者也可以自己实现CookieStore接口。
                        .setCookieStore(new DBCookieStore(mContext).setEnable(false) // 如果不维护cookie，设置false禁用。
                ));
        com.yanzhenjie.nohttp.Logger.setDebug(true);
        com.yanzhenjie.nohttp.Logger.setTag("NoHttp");
    }

    void initLogger() {
        Logger.init(TAG) // default_img PRETTYLOGGER or use just init()
                .methodCount(3); // default_img 2 ==> 输出几行
        // .hideThreadInfo() // default_img shown 是否显示线程信息
        // .logLevel(LogLevel.NONE) // default_img LogLevel.FULL
        // .methodOffset(2); // default_img 0 log输出每行的偏移量
        // .logAdapter(new AndroidLogAdapter()); //default_img AndroidLogAdapter
    }

    public static Context getContext() {
        return mContext;
    }

    @Override
    public void onBaseContextAttached(Context base) {
        super.onBaseContextAttached(base);
        MultiDex.install(base);
        TinkerInstaller.install(this);
//                new DefaultLoadReporter(base),
//                new DefaultPatchReporter(base),
//                new DefaultPatchListener(base),
//                TinkerResultService.class,
//                new UpgradePatch());
    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }
}
