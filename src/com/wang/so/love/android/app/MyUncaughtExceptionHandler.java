package com.wang.so.love.android.app;

import java.lang.Thread.UncaughtExceptionHandler;

/**
 * 处理全局异常
 * @author HJW
 *
 * @date 2015年11月30日
 */
public class MyUncaughtExceptionHandler implements UncaughtExceptionHandler {

	@Override
	public void uncaughtException(Thread thread, Throwable ex) {
		// 绕过生命周期的顺序,强制关闭.
		android.os.Process.killProcess(android.os.Process.myPid());
	}
}
