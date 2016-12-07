package com.wang.so.love.android.app.view.fragment;

import com.wang.so.love.android.app.view.R;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ContactsFragment extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View contactsLayout = inflater.inflate(R.layout.alluser_layout,
				container, false);
		return contactsLayout;
	}

}
