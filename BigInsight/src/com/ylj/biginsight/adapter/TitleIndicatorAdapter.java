package com.ylj.biginsight.adapter;

import java.util.List;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

public class TitleIndicatorAdapter extends PagerAdapter {

	private List<View> list;
	private static final String[] TITLES = new String[] { "头条", "科技", "房产", "体育", "财经", "数码", "娱乐" };

	public TitleIndicatorAdapter(List<View> list) {
		this.list = list;
	}

	@Override
	public CharSequence getPageTitle(int position) {
		return TITLES[position % TITLES.length];
	}

	@Override
	public int getCount() {
		return TITLES.length;
	}

	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		return arg0 == arg1;
	}

	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		((ViewPager) container).removeView(list.get(position));
	}

	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		((ViewPager) container).addView(list.get(position));
		return list.get(position);
	}
}
