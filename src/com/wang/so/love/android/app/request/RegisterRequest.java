package com.wang.so.love.android.app.request;

import java.util.HashMap;
import java.util.Map;

import android.content.Context;
import android.os.Handler;

import com.wang.so.love.android.app.utils.HttpUrlUtil;

/**
 * 注册请求
 * 
 * @author HeJiawang
 * @date 2016.12.03
 */
public class RegisterRequest extends BaseRequest {

	private static Map<String, String> params = new HashMap<String, String>();
	private static Context mContext;
	private Handler mHandler;

	public RegisterRequest(Context mContext, Handler mHandler, 
			String loginName, String passWord, String rePassWord) {
		this.mContext = mContext;
		this.mHandler = mHandler;
		
		params.put("loginName", loginName);
		params.put("passWord", passWord);
		params.put("rePassWord", rePassWord);
	}

	@Override
	public void dopost() {
		BaseHttpClient httpClient = new BaseHttpClient(mContext);
        httpClient.setParams(getUrl(), params, mHandler);
        httpClient.sendPost();
	}

	@Override
	public void doget() {
		BaseHttpClient httpClient = new BaseHttpClient(mContext);
        httpClient.setParams(getUrl(), params, mHandler);
        httpClient.sendGet();
	}

	@Override
	public void doRequest() {

	}

	@Override
	public String getUrl() {
		return HttpUrlUtil.REGISTERURL;
	}
}
