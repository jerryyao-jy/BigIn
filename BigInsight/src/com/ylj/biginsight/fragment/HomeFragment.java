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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.ylj.biginsight.activity.R;
import com.ylj.biginsight.adapter.HomeViewPagerAdapter;

public class HomeFragment extends Fragment implements OnCheckedChangeListener {
	private RadioGroup rg_tab;
	private ViewPager viewPager;
	private List<Fragment> pages;
	private HomeViewPagerAdapter adapter;
	private SlidingMenu menu;
	private RadioButton rb_home, rb_newsCenter, rb_govOffical, rb_smartService, rb_settings;

	public HomeFragment(SlidingMenu menu) {
		this.menu = menu;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_main, null);

		pages = new ArrayList<Fragment>();
		pages.add(new HomePageFragment(menu));
		pages.add(new NewsCenterPageFragment());
		pages.add(new GovOfficalPageFragment());
		pages.add(new SmartServicePageFragment());
		pages.add(new SettingsPageFragment());

		rg_tab = (RadioGroup) view.findViewById(R.id.rg_tab);
		rg_tab.setOnCheckedChangeListener(this);

		rb_settings = (RadioButton) view.findViewById(R.id.rb_settings);
		rb_smartService = (RadioButton) view.findViewById(R.id.rb_smartService);
		rb_govOffical = (RadioButton) view.findViewById(R.id.rb_govOffical);
		rb_newsCenter = (RadioButton) view.findViewById(R.id.rb_newsCenter);
		rb_home = (RadioButton) view.findViewById(R.id.rb_home);

		viewPager = (ViewPager) view.findViewById(R.id.viewPager);
		adapter = new HomeViewPagerAdapter(getActivity().getSupportFragmentManager(), pages);
		viewPager.setAdapter(adapter);
		viewPager.setOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageSelected(int position) {
				if (position == 0) {
					menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
				} else {
					menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_NONE);
				}

				if (position == 0) {
					rb_home.setChecked(true);
				} else if (position == 1) {
					rb_newsCenter.setChecked(true);
				} else if (position == 2) {
					rb_govOffical.setChecked(true);
				} else if (position == 3) {
					rb_smartService.setChecked(true);
				} else if (position == 4) {
					rb_settings.setChecked(true);
				}
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
			}

			@Override
			public void onPageScrollStateChanged(int arg0) {
			}
		});

		rb_home.setChecked(true);

		return view;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
	}

	@Override
	public void onCheckedChanged(RadioGroup group, int checkId) {
		switch (checkId) {
		case R.id.rb_home:
			viewPager.setCurrentItem(0, false);
			menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
			break;
		case R.id.rb_newsCenter:
			menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_NONE);
			viewPager.setCurrentItem(1, false);
			break;
		case R.id.rb_govOffical:
			viewPager.setCurrentItem(2, false);
			menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_NONE);
			break;
		case R.id.rb_smartService:
			viewPager.setCurrentItem(3, false);
			menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_NONE);
			break;
		case R.id.rb_settings:
			viewPager.setCurrentItem(4, false);
			menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_NONE);
			break;
		}
		rg_tab.check(checkId);
	}
}
