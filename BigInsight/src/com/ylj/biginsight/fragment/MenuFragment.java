package com.ylj.biginsight.fragment;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.ylj.biginsight.activity.R;
import com.ylj.biginsight.adapter.MenuAdapter;

public class MenuFragment extends Fragment {

	private ListView lv_menu;
	private MenuAdapter adapter;
	private List<View> pages;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_menu, null);
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
		return view;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
	}
}
