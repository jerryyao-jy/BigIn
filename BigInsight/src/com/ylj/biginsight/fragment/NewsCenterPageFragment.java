package com.ylj.biginsight.fragment;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.viewpagerindicator.TitlePageIndicator;
import com.ylj.biginsight.activity.R;
import com.ylj.biginsight.adapter.TitleIndicatorAdapter;

public class NewsCenterPageFragment extends Fragment {
	
	private ViewPager pager;
	private TitleIndicatorAdapter adapter;
	private View view;
	private TitlePageIndicator indicator;
	private List<View> list;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.fragment_page_news, null);
		list = new ArrayList<View>();
		list.add(inflater.inflate(R.layout.fragment_page_toutiao, null));
		list.add(inflater.inflate(R.layout.fragment_page_keji, null));
		list.add(inflater.inflate(R.layout.fragment_page_fangchan, null));
		list.add(inflater.inflate(R.layout.fragment_page_tiyu, null));
		list.add(inflater.inflate(R.layout.fragment_page_caijing, null));
		list.add(inflater.inflate(R.layout.fragment_page_shuma, null));
		list.add(inflater.inflate(R.layout.fragment_page_yule, null));
		
		adapter = new TitleIndicatorAdapter(list);  
        pager = (ViewPager)view.findViewById(R.id.viewPager);  
        pager.setAdapter(adapter); 
        
		indicator = (TitlePageIndicator) view.findViewById(R.id.indicator);
		indicator.setViewPager(pager);

		// 如果我们要对ViewPager设置监听，用indicator设置就行了
		indicator.setOnPageChangeListener(new OnPageChangeListener() {
			
			@Override
			public void onPageSelected(int position) {
				pager.setCurrentItem(position);
			}
			
			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
			}
			
			@Override
			public void onPageScrollStateChanged(int arg0) {
			}
		});
		
		return view;
	}
}
