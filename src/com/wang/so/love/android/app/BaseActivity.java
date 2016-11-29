package com.wang.so.love.android.app;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Window;

/**
 * BaseActivity
 * 
 * @author HeJiawang
 * @date 2016.11.29
 */
public abstract class BaseActivity extends Activity {

	protected Activity context;
	protected SharedPreferences sharedPreferences;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		
		context = this;
		sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
	}

	@Override
	protected void onStart() {
		super.onStart();
	}

	@Override
	protected void onResume() {
		super.onResume();

	}

	@Override
	protected void onStop() {
		super.onStop();
	}

	@Override
	protected void onPause() {
		super.onPause();

	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
	}
}
