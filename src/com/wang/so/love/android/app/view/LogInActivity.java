package com.wang.so.love.android.app.view;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.wang.so.love.android.app.BaseActivity;
import com.wang.so.love.android.app.SoLoveAPP;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.wang.so.love.android.app.utils.HttpUrlUtil;
import com.wang.so.love.android.app.utils.LoggerUtil;
import com.wang.so.love.android.app.utils.PhoneFormatCheckUtil;
import com.wang.so.love.android.app.utils.StringUtil;
import com.wang.so.love.android.app.utils.ToastUtil;
import com.wang.so.love.android.app.view.R;

/**
 * 登陆页
 * 
 * @author HeJiawang
 * @date 2016.11.29
 */
public class LogInActivity extends BaseActivity {

	private EditText et_loginName;
	private EditText et_passWord;
	private Button bt_login;
	
	private String loginName;
	private String passWord;
	
	private LoginHandler loginHandler;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		
		this.initView();
	}
	
	/**
	 * 页面初始化
	 */
	private void initView(){
		et_loginName = (EditText) findViewById(R.id.et_loginName);
		et_passWord = (EditText) findViewById(R.id.et_passWord);
		bt_login = (Button) findViewById(R.id.bt_login);
		
		loginHandler = new LoginHandler();
		
		bt_login.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				loginName = et_loginName.getText().toString().trim();
				passWord = et_passWord.getText().toString().trim();
				if( checkLogin() ){
					loginRequest();
				}
			}
		});
	}

	/**
	 * 验证输入的登陆手机号与密码
	 * @return
	 */
	private Boolean checkLogin(){
		if( StringUtil.isBlank(loginName) ){
			ToastUtil.show("请输入注册手机号");
			return false;
		}
		
		if( !PhoneFormatCheckUtil.isPhoneLegal(loginName) ){
			ToastUtil.show("请输入正确格式的手机号");
			return false;
		}
		
		if( StringUtil.isBlank(passWord) ){
			ToastUtil.show("请输入密码");
			return false;
		}
		
		if( passWord.length()<6 || passWord.length()>18 ){
			ToastUtil.show("请输入6-18位的密码");
			return false;
		}
		return true;
	}
	
	/**
	 * 登陆请求
	 */
	private void loginRequest(){
		StringRequest request = new StringRequest(Request.Method.POST,
				HttpUrlUtil.LOGINURL, 
				new Response.Listener<String>() {

			@Override
			public void onResponse(String response) {
				LoggerUtil.i("SoLoveRequest", HttpUrlUtil.LOGINURL + " result :"+ response);
				Message msg = loginHandler.obtainMessage(0, response);
				loginHandler.sendMessage(msg);
			}
		}, new Response.ErrorListener(){

			@Override
			public void onErrorResponse(VolleyError error) {
				ToastUtil.show(R.string.net_error);
			}}
		){
			@Override
			protected Map<String, String> getParams() {
				Map<String, String> map = new HashMap<>();
				map.put("loginName", loginName);
				map.put("passWord", passWord);
				return map;
			}
		};
		SoLoveAPP.getRequestQueue().add(request);
	}
	
	/**
	 * 登陆 Handler
	 * @author HeJiawang
	 * @date   2016年12月5日
	 */
	class LoginHandler extends Handler{
		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			if( msg.what == 0 ){
				
				try {
					JSONObject jsonObject;
					jsonObject = new JSONObject(msg.obj.toString());
					boolean success = jsonObject.getBoolean("success");
					if( success ){
						ToastUtil.show(jsonObject.getString("message") + "success");
					} else {
						ToastUtil.show(jsonObject.getString("message") + "error");
					}
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
