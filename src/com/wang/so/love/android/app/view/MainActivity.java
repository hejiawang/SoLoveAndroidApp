package com.wang.so.love.android.app.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.wang.so.love.android.app.BaseActivity;
import com.wang.so.love.android.app.utils.SharedPreferencesUtil;
import com.wang.so.love.android.app.view.R;

/**
 * ��ҳ��
 * 
 * @author HeJiawang
 * @date 2016.11.29
 */
public class MainActivity extends BaseActivity {
	
	private Button button_login;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		this.checkGuidance();
		this.initView();
		
	}
	
	/**
	 * ����Ƿ�հ�װAPP,���Ƿ���Ҫ��������ҳ
	 */
	private void checkGuidance(){
		boolean guidanceActivity = sharedPreferences.getBoolean(SharedPreferencesUtil.KEY_GUIDE_ACTIVITY, true);
		if( guidanceActivity ){
			Intent intent = new Intent(context, GuidanceActivity.class);
			startActivity(intent);
			finish();
		} 
	}
	
	/**
	 * ҳ���ʼ��
	 */
	private void initView(){
		button_login = (Button) findViewById(R.id.button_login);
		
		button_login.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(context, LogInActivity.class);
				startActivity(intent);
			}
		});
	}
}
