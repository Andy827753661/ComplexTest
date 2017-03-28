
package com.bobo.complextest.Utils;

import android.widget.Toast;

import com.bobo.complextest.Application.MyApplication;

/**
 * Created by Administrator on 2017/3/13.
 */

public class ToastUtil {

    public static void show(String msg) {
        Toast.makeText(MyApplication.getContext(), msg, Toast.LENGTH_SHORT).show();
    }
}
