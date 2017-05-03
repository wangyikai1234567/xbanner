package wangyikai.bwei.com.a1;

import android.app.Application;

import org.xutils.x;

/**
 * date: 2017/5/3.
 * author: 王艺凯 (lenovo )
 * function:
 */

public class MyAppLication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        x.Ext.init(this);
    }
}
