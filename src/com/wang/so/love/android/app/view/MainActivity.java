package com.wang.so.love.android.app.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

import com.wang.so.love.android.app.BaseActivity;
import com.wang.so.love.android.app.view.R;

/**
 * 主页
 * 
 * @author HeJiawang
 * @date 2016.11.29
 */
public class MainActivity extends BaseActivity {
	
	private SlidingMenu mMenu;
	
	private Button button_login;
	private ImageView tv_one;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		this.initView();
	}
	
	/**
	 * 页面初始化
	 */
	private void initView(){
		mMenu = (SlidingMenu) findViewById(R.id.id_menu);
		
		button_login = (Button) findViewById(R.id.button_login);
		tv_one = (ImageView) findViewById(R.id.one);
		
		button_login.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(context, LogInActivity.class);
				startActivity(intent);
			}
		});
		
		tv_one.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(context, LogInActivity.class);
				startActivity(intent);
			}
		});
	}
	
	/**
	 * 主页面上的"切换滑动菜单"的点击事件
	 * @param view
	 */
	public void toggleMenu(View view){
		mMenu.toggle();
	}
}
