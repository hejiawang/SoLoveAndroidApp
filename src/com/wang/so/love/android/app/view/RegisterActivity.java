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

import android.content.Intent;
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
 * 注册页
 * 
 * @author HeJiawang
 * @date 2016.12.03
 */
public class RegisterActivity extends BaseActivity {

	private EditText et_loginName;
	private EditText et_passWord;
	private EditText et_rePassWord;
	private Button bt_register;
	
	private String loginName;
	private String passWord;
	private String rePassWord;
	
	private RegisterHandler registerHandler;
	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);
		
		this.initView();
	}
	
	/**
	 * 页面初始化
	 */
	private void initView(){
		et_loginName = (EditText) findViewById(R.id.et_loginName);
		et_passWord = (EditText) findViewById(R.id.et_passWord);
		et_rePassWord = (EditText) findViewById(R.id.et_rePassWord);
		bt_register = (Button) findViewById(R.id.bt_register);

		bt_register.setOnClickListener( new RegisterOnclickListener() );

		registerHandler = new RegisterHandler();
	}
	
	/**
	 * 注册事件
	 * @author HeJiawang
	 * @date   2016年12月5日
	 */
	class RegisterOnclickListener implements OnClickListener{

		@Override
		public void onClick(View v) {
			loginName = et_loginName.getText().toString().trim();
			passWord = et_passWord.getText().toString().trim();
			rePassWord = et_rePassWord.getText().toString().trim();
			
			if( checkRegister() ){
				registerRequest();
			}
		}
	}
	
	/**
	 * 校验注册信息的正确性
	 * @return
	 */
	private Boolean checkRegister(){
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
		
		if( StringUtil.isBlank(rePassWord) ){
			ToastUtil.show("请输入重复密码");
			return false;
		}
		
		if( !passWord.equals(rePassWord) ){
			ToastUtil.show("两次密码不一致");
			return false;
		}
		
		return true;
	}
	
	/**
	 * 注册请求
	 */
	private void registerRequest(){
		StringRequest request = new StringRequest(Request.Method.POST,
				HttpUrlUtil.REGISTERURL, 
				new Response.Listener<String>() {

			@Override
			public void onResponse(String response) {
				LoggerUtil.i("SoLoveRequest", HttpUrlUtil.REGISTERURL + " result :"+ response);
				Message msg = registerHandler.obtainMessage(0, response);
				registerHandler.sendMessage(msg);
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
				map.put("rePassWord", rePassWord);
				return map;
			}
		};
		SoLoveAPP.getRequestQueue().add(request);
	}
	
	/**
	 * 注册 Handler
	 * @author HeJiawang
	 * @date   2016年12月3日
	 */
	class RegisterHandler extends Handler{
		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			
			if (msg.what == 0) {
				try {
					JSONObject jsonObject;
					jsonObject = new JSONObject(msg.obj.toString());
					boolean success = jsonObject.getBoolean("success");
					if( success ){
						ToastUtil.show(jsonObject.getString("message"));

						JSONObject userInfoJSON = jsonObject.getJSONObject("result");
						String userID = userInfoJSON.getString("userID");
						
						Intent intent = new Intent();
						intent.setClass(context, MainActivity.class);
						Bundle bundle = new Bundle();
						bundle.putSerializable("userID", userID);
						startActivity(intent);
						finish();
					} else {
						ToastUtil.show(jsonObject.getString("message"));
					}
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
