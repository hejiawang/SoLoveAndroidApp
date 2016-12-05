package com.wang.so.love.android.app;


import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.wang.so.love.android.app.utils.ToastUtil;

import android.app.Application;
import android.content.Context;

/**
 * 处理全局     Application
 * 
 * @author HeJiawang
 * @date   2016年12月3日
 */
public class SoLoveAPP extends Application {
	private static Context mContext;
    private static RequestQueue mRequestQueue;
    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
        ToastUtil.init(mContext);
        
        // 处理程序全局异常
     	Thread.currentThread().setUncaughtExceptionHandler(new MyUncaughtExceptionHandler());
    }
    
    /**
     * Volley请求链</br>
     * @return
     */
    public static RequestQueue getRequestQueue() {
        // lazy initialize the request queue, the queue instance will be
        // created when it is accessed for the first time
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(mContext);
        }
        return mRequestQueue;
    }
}
