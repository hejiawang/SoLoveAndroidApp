package com.wang.so.love.android.app.view;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.wang.so.love.android.app.BaseActivity;
import com.wang.so.love.android.app.utils.SharedPreferencesUtil;
import com.wang.so.love.android.app.view.R;

/**
 * 引导页
 * 
 * @author HeJIawang
 * @date 2016.11.29
 */
public class GuidanceActivity extends BaseActivity{

	private ViewPager mViewPager ;
	private Button btn_goMain;
	
	private PagerAdapter mPagerAdapter ;
	private List<View> mListViews ;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.guide_layout) ;
		
		LayoutInflater inflater = getLayoutInflater() ;
		View view1 = inflater.inflate(R.layout.guide_item_view1, null);
		View view2 = inflater.inflate(R.layout.guide_item_view2, null);
		View view3 = inflater.inflate(R.layout.guide_item_view3, null);
		View view4 = inflater.inflate(R.layout.guide_item_view4, null);
		btn_goMain = (Button) view4.findViewById(R.id.btn_goMain);
		btn_goMain.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
	            SharedPreferences.Editor editor = sharedPreferences.edit();
	            editor.putBoolean(SharedPreferencesUtil.KEY_GUIDE_ACTIVITY, false);
	            editor.commit();
				
				Intent intent = new Intent(context, MainActivity.class);
				startActivity(intent);
				finish();
			}
		});
		
		mListViews = new ArrayList<View>() ;
		mListViews.add(view1) ;
		mListViews.add(view2) ;
		mListViews.add(view3) ;
		mListViews.add(view4) ;
		
		mViewPager = (ViewPager) findViewById(R.id.guide_view_group); 
		mPagerAdapter = new PagerAdapter(mListViews) ;
		mViewPager.setAdapter(mPagerAdapter) ;
	}
	
	private class PagerAdapter extends android.support.v4.view.PagerAdapter {

		private List<View> mViews ;
		
		public PagerAdapter(List<View> views) {
			super() ;
			mViews = views ;
		}
		
		@Override
		public int getCount() {
			return mViews.size();
		}

		@Override
		public boolean isViewFromObject(View v, Object obj) {
			return v == obj;
		}
		
		@Override
		public void destroyItem(View view, int position, Object object) {
			((ViewPager) view).removeView(mViews.get(position)); 
		}
		
		@Override
		public Object instantiateItem(View view, int position) {
			
			((ViewPager) view).addView(mViews.get(position), 0);  
	        return mViews.get(position);  
		}
	}
}
