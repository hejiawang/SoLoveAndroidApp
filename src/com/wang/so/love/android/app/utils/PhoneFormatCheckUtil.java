package com.wang.so.love.android.app.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 判断是否为正确格式的手机号
 * @author HeJiawang
 * @date   2016.12.03
 */
public class PhoneFormatCheckUtil {
	/**
	 * 大陆号码或香港号码均可
	 */
	public static boolean isPhoneLegal(String str) {
		return isChinaPhoneLegal(str) || isHKPhoneLegal(str);
	}

	/**
	 * 大陆手机号码11位数，</br>
	 * 匹配格式： 前三位固定格式+后8位任意数 此方法中前三位格式有：</br> 
	 * 13+任意数 </br>
	 * 15+除4的任意数 </br>
	 * 18+除1和4的任意数</br>
	 * 17+除9的任意数 </br>
	 * 147</br>
	 */
	public static boolean isChinaPhoneLegal(String str) {
		String regExp = "^((13[0-9])|(15[^4])|(18[0,2,3,5-9])|(17[0-8])|(147))\\d{8}$";
		Pattern p = Pattern.compile(regExp);
		Matcher m = p.matcher(str);
		return m.matches();
	}

	/**
	 * 香港手机号码8位数，5|6|8|9开头+7位任意数
	 */
	public static boolean isHKPhoneLegal(String str) {
		String regExp = "^(5|6|8|9)\\d{7}$";
		Pattern p = Pattern.compile(regExp);
		Matcher m = p.matcher(str);
		return m.matches();
	}
	
	public static void main(String[] args) {
		System.out.println(PhoneFormatCheckUtil.isPhoneLegal("13889259343"));
	}
}
