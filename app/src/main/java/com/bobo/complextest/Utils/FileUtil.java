
package com.bobo.complextest.Utils;

import com.bobo.complextest.Application.MyApplication;
import com.orhanobut.logger.Logger;

import java.io.File;

/**
 * Created by Administrator on 2017/3/13.
 */

public class FileUtil {

    public static File getCacheDirectory() {
        // SDCard/Android/mData/你的应用包名/cache/目录
        File file = new File(MyApplication.getContext().getExternalCacheDir(), "MyCache");
        // 当你的应用在被用户卸载后，SDCard/Android/mData/你的应用的包名/ 这个目录下的所有文件都会被删除，不会留下垃圾信息。
        if (!file.exists()) {
            boolean b = file.mkdirs();
            Logger.e("文件不存在  创建文件    " + b + "   file.path = " + file.getPath());
        } else {
            Logger.e("文件存在 " + "   file.path = " + file.getPath());
        }
        return file;
    }
}
