package com.ylj.biginsight.fragment;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.ylj.biginsight.activity.R;
import com.ylj.biginsight.adapter.MenuAdapter;

public class MenuFragment extends Fragment {

	private ListView lv_menu;
	private MenuAdapter adapter;
	private List<View> pages;
	private View view;
	public static int cur_pos = 0;// 当前显示的一行
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.fragment_menu, null);
		lv_menu = (ListView) view.findViewById(R.id.lv_menu);
		pages = new ArrayList<View>();
		pages.add(inflater.inflate(R.layout.layout_tab_news, null));
		pages.add(inflater.inflate(R.layout.layout_tab_focus, null));
		pages.add(inflater.inflate(R.layout.layout_tab_local, null));
		pages.add(inflater.inflate(R.layout.layout_tab_pics, null));
		pages.add(inflater.inflate(R.layout.layout_tab_read, null));
		pages.add(inflater.inflate(R.layout.layout_tab_ties, null));
		pages.add(inflater.inflate(R.layout.layout_tab_ugc, null));
		pages.add(inflater.inflate(R.layout.layout_tab_vote, null));
		adapter = new MenuAdapter(getActivity(), pages);
		lv_menu.setAdapter(adapter);
		lv_menu.setChoiceMode(ListView.CHOICE_MODE_SINGLE);// 一定要设置这个属性，否则ListView不会刷新
		lv_menu.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				adapter.setSelectItem(position); // 记录当前选中的item
				//adapter.notifyDataSetInvalidated();	//更新UI界面
			}
		});
		
		return view;
	}

	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
	}
}
