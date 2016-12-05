package com.wang.so.love.android.app.request;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.os.Handler;
import android.os.Message;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.wang.so.love.android.app.SoLoveAPP;
import com.wang.so.love.android.app.utils.LoggerUtil;
import com.wang.so.love.android.app.utils.ToastUtil;
import com.wang.so.love.android.app.view.R;

/**
 * volley HttpClient
 * 
 * @author HeJiawang
 * @date   2016年12月3日
 */
public class BaseHttpClient {

	private RequestQueue rq;
	private Context mContext;
	private Handler mHandler;
	private Map<String, String> mParams = new HashMap<String, String>();
	private String urlAddress = "";

	public BaseHttpClient(Context context) {
		mContext = context;
		rq = SoLoveAPP.getRequestQueue();
	}

	public void setParams(String url, Map<String, String> params, Handler handler) {
		mParams = params;
		mHandler = handler;
		urlAddress = url;
	}

	public void sendPost() {

		StringRequest sr = new StringRequest(
				com.android.volley.Request.Method.POST, urlAddress,
				new Response.Listener<String>() {
					@Override
					public void onResponse(String arg0) {
						LoggerUtil.i("SoLoveRequest", urlAddress + " " + " result :"+ arg0);
						if(mHandler !=null){
							Message msg = mHandler.obtainMessage(0, arg0);
							mHandler.sendMessage(msg);
						}
					}
				}, new Response.ErrorListener() {
					@Override
					public void onErrorResponse(VolleyError error) {
						ToastUtil.show(R.string.net_error);
						if(mHandler !=null){
							Message msg = new Message();
							msg.what = 1;
							mHandler.sendMessage(msg);
						}
					}
				}) {

			@Override
			protected Map<String, String> getParams() {
				return mParams;
			}

			@Override
			public Map<String, String> getHeaders() throws AuthFailureError {
				Map<String, String> params = new HashMap<String, String>();

				return params;
			}
			@Override
			public RetryPolicy getRetryPolicy() {
				 return super.getRetryPolicy();
			}
			

		};
		rq.add(sr);
	}

	public void sendRequest() {
		StringRequest sr = new StringRequest(urlAddress,
				new Response.Listener<String>() {
					@Override
					public void onResponse(String arg0) {
						LoggerUtil.i("SoLoveRequest", urlAddress + " " + " result :"+ arg0);
						Message msg = mHandler.obtainMessage(0, arg0);
						mHandler.sendMessage(msg);
						parseData(arg0);
					}
				}, new Response.ErrorListener() {
					@Override
					public void onErrorResponse(VolleyError error) {
						ToastUtil.show(R.string.net_error);
						Message msg = new Message();
						msg.what = 1;
						mHandler.sendMessage(msg);
					}
				});
		rq.add(sr);
	}
	public void sendGet(){

			StringBuffer url = new StringBuffer();
			Iterator iter = mParams.entrySet().iterator();
			while (iter.hasNext()) {
				Entry entry = (Entry) iter.next();

				Object key =entry.getKey();
				url.append(key);
				url.append("=");
				Object value= entry.getValue();
				url.append(value);
				if(iter.hasNext()){
					url.append("&");
				}
			}
			urlAddress = urlAddress+url;

		StringRequest sr = new StringRequest(
				com.android.volley.Request.Method.GET, urlAddress,
				new Response.Listener<String>() {
					@Override
					public void onResponse(String arg0) {
						LoggerUtil.i("SoLoveRequest", urlAddress + " " + " result :"+ arg0);
						if(mHandler !=null){
							Message msg = mHandler.obtainMessage(0, arg0);
							mHandler.sendMessage(msg);
							parseData(arg0);
						}
					}
				}, new Response.ErrorListener() {
					@Override
					public void onErrorResponse(VolleyError error) {
						ToastUtil.show(R.string.net_error);
						if(mHandler !=null){
							Message msg = new Message();
							msg.what = 1;
							mHandler.sendMessage(msg);
						}
					}
				}) {
			@Override
			public Map<String, String> getHeaders() throws AuthFailureError {
				Map<String, String> params = new HashMap<String, String>();
				return params;
			}
			@Override
			public RetryPolicy getRetryPolicy() {
				 return super.getRetryPolicy();
			}
		};
		rq.add(sr);
	}
	
	private void parseData(String strResponse){
		JSONObject json;
		try {
			json = new JSONObject(strResponse);
			int code = json.getInt("code");
			if(code != 100&&(code==-200||code==-2)){
				String value = json.getString("data");
				if(value!= null&&!value.equals("null")&&!value.equals("")){
					ToastUtil.show(value);
				}
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
}
