package com.wang.so.love.android.app.view;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.widget.TextView;

import com.wang.so.love.android.app.BaseActivity;
import com.wang.so.love.android.app.utils.SharedPreferencesUtil;

/**
 * 启动页面</br>
 * 1、显示版本号<br>
 * 2、是否需要进入引导页</br>
 * 3、检查版本更新</br>
 * 
 * @author wang
 * @date   2016.12.02
 */
public class SplashActivity extends BaseActivity {
	
	private TextView tv_splash_version;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);

		tv_splash_version = (TextView) findViewById(R.id.tv_splash_version);
		tv_splash_version.setText("版本：" + this.getAppVersion());
		
		new Thread(new CheckVersionTask()).start();

		// splash页面淡出 2秒钟
		AlphaAnimation aa = new AlphaAnimation(0.2f, 1.0f);
		aa.setDuration(2000);
		findViewById(R.id.rl_splash).startAnimation(aa);
		
	}
	
	/**
	 * 做一些线程的事</br>
	 * 如：检查版本更新</br>
	 * @author HeJiawang
	 * @date   2016.12.02
	 */
	private class CheckVersionTask implements Runnable {

		@Override
		public void run() {
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			loadMainUI();
		}
		
	}
	
	/**
	 * 若首次打开APP，则进入引导页，否则进入主页
	 */
	private void loadMainUI() {
		Intent intent = null;
		boolean guidanceActivity = sharedPreferences.getBoolean(SharedPreferencesUtil.KEY_GUIDE_ACTIVITY, true);
		if( guidanceActivity ){
			intent = new Intent(context, GuidanceActivity.class);
		} else {
			intent = new Intent(context, MainActivity.class);
		}
		startActivity(intent);
		overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
		
		finish();
	}
	
	/**
	 * 获取应用程序版本号
	 */
	private String getAppVersion() {
		PackageManager pm = getPackageManager();
		try {
			PackageInfo info = pm.getPackageInfo(getPackageName(), 0);
			return info.versionName;
		} catch (NameNotFoundException e) {
			e.printStackTrace();
			// 如果抛出异常，返回空字符串
			return "";
		}
	}
}
