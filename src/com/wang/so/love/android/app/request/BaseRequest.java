package com.wang.so.love.android.app.request;

/**
 * abstract request 
 * 
 * @author HeJiawang
 * @date   2016.12.03
 */
public abstract class BaseRequest {
	
	public abstract void dopost();
	
	public abstract void doget();
	
	public abstract void doRequest();
	
	public abstract String getUrl();
}
