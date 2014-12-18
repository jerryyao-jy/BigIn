package com.ylj.biginsight.fragment;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.ylj.biginsight.activity.NewsDetailActivity;
import com.ylj.biginsight.activity.R;
import com.ylj.biginsight.adapter.HomeListViewAdapter;
import com.ylj.biginsight.model.NewsModel;
import com.ylj.biginsight.viewpager.ChildViewPager;

public class HomePageFragment extends Fragment {

	private ChildViewPager home_viewPager;
	private HomePageViewPagerAdapter adapter;
	private List<View> pages;
	private List<NewsModel> list;
	private SlidingMenu menu;
	private PullToRefreshListView refreshListView;
	private HomeListViewAdapter listViewAdapter;
	private ImageView dot_one,dot_two,dot_three,dot_four;
	private int[] images = { R.drawable.listview_1, R.drawable.listview_2, R.drawable.listview_3, R.drawable.listview_4, R.drawable.listview_5, R.drawable.listview_6, R.drawable.listview_7 };

	public HomePageFragment(SlidingMenu menu) {
		this.menu = menu;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_page_home, null);
		dot_one = (ImageView) view.findViewById(R.id.dot_one);
		dot_two = (ImageView) view.findViewById(R.id.dot_two);
		dot_three = (ImageView) view.findViewById(R.id.dot_three);
		dot_four = (ImageView) view.findViewById(R.id.dot_four);
		
		pages = new ArrayList<View>();
		pages.add(inflater.inflate(R.layout.layout_page_sliding_one, null));
		pages.add(inflater.inflate(R.layout.layout_page_sliding_two, null));
		pages.add(inflater.inflate(R.layout.layout_page_sliding_three, null));
		pages.add(inflater.inflate(R.layout.layout_page_sliding_four, null));

		adapter = new HomePageViewPagerAdapter(pages);
		home_viewPager = (ChildViewPager) view.findViewById(R.id.home_viewPager);
		home_viewPager.setAdapter(adapter);
		home_viewPager.setOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageSelected(int position) {
				if (position == 0) {
					menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
				} else {
					menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_NONE);
				}
				
				switch (position) {
				case 0:
					dot_one.setBackgroundResource(R.drawable.dot_focus);
					dot_two.setBackgroundResource(R.drawable.dot_normal);
					dot_three.setBackgroundResource(R.drawable.dot_normal);
					dot_four.setBackgroundResource(R.drawable.dot_normal);
					break;

				case 1:
					dot_one.setBackgroundResource(R.drawable.dot_normal);
					dot_two.setBackgroundResource(R.drawable.dot_focus);
					dot_three.setBackgroundResource(R.drawable.dot_normal);
					dot_four.setBackgroundResource(R.drawable.dot_normal);
					break;
					
				case 2:
					dot_one.setBackgroundResource(R.drawable.dot_normal);
					dot_two.setBackgroundResource(R.drawable.dot_normal);
					dot_three.setBackgroundResource(R.drawable.dot_focus);
					dot_four.setBackgroundResource(R.drawable.dot_normal);
					break;
					
				case 3:
					dot_one.setBackgroundResource(R.drawable.dot_normal);
					dot_two.setBackgroundResource(R.drawable.dot_normal);
					dot_three.setBackgroundResource(R.drawable.dot_normal);
					dot_four.setBackgroundResource(R.drawable.dot_focus);
					break;
				}
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
			}

			@Override
			public void onPageScrollStateChanged(int arg0) {
			}
		});

		list = new ArrayList<NewsModel>();
		for (int i = 0; i < images.length; i++) {
			NewsModel model = new NewsModel();
			model.setId(i);
			model.setTitle("this is title" + i);
			model.setContent("this is content" + i);
			model.setShowImage(images[i]);
			model.setComment(R.drawable.icon_comment_num);
			list.add(model);
		}
		refreshListView = (PullToRefreshListView) view.findViewById(R.id.refreshListView);
		refreshListView.setMode(Mode.BOTH);
		refreshListView.setClickable(true);
		listViewAdapter = new HomeListViewAdapter(getActivity(), list);
		refreshListView.setAdapter(listViewAdapter);
		refreshListView.setOnRefreshListener(new OnRefreshListener<ListView>() {

			@Override
			public void onRefresh(PullToRefreshBase<ListView> refreshView) {
				// Do work to refresh the list here.
				new GetDataTask().execute();

			}

		});

		refreshListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Intent intent = new Intent(getActivity(), NewsDetailActivity.class);
				intent.putExtra("title", list.get(position - 1).getTitle());
				intent.putExtra("content", list.get(position - 1).getContent());
				startActivity(intent);
			}
		});

		return view;
	}

	class GetDataTask extends AsyncTask<Void, Void, String[]> {

		@Override
		protected String[] doInBackground(Void... params) {
			// Simulates a background job.
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
			}
			return null;
		}

		@Override
		protected void onPostExecute(String[] result) {
			listViewAdapter.notifyDataSetChanged();

			// Call onRefreshComplete when the list has been refreshed.
			refreshListView.onRefreshComplete();

			super.onPostExecute(result);
		}
	}

}
