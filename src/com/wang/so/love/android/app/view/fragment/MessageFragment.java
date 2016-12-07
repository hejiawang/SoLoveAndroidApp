package com.wang.so.love.android.app.view.fragment;

import com.wang.so.love.android.app.view.R;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class MessageFragment extends Fragment {

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View messageLayout = inflater.inflate(R.layout.choice_layout,
				container, false);
		return messageLayout;
	}

}
