package com.wang.so.love.android.app.utils;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;

/**
 * Toast
 * 
 * @author HeJiawang
 * @date 2016.11.29
 */
public class ToastUtil {
	private static Context context;
	private static Handler handler;

	public static final void init(final Context context) {
		ToastUtil.context = context;
		handler = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				Toast.makeText(context, msg.obj.toString(), Toast.LENGTH_SHORT).show();
			}
		};
	}

	public static final void show(String text) {
		handler.obtainMessage(0, text).sendToTarget();
	}

	public static final void show(int resId) {
		handler.obtainMessage(0, context.getString(resId)).sendToTarget();
	}

}
