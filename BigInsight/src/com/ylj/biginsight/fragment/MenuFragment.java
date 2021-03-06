package com.ylj.biginsight.fragment;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageButton;
import android.widget.ListView;

import com.ylj.biginsight.activity.LoginActivity;
import com.ylj.biginsight.activity.MainActivity;
import com.ylj.biginsight.activity.R;
import com.ylj.biginsight.adapter.MenuAdapter;

public class MenuFragment extends Fragment {

	private Activity mActivity;
	private ListView lv_menu;
	private MenuAdapter adapter;
	private List<View> pages;
	private View view;
	public static int cur_pos = 0;// 当前显示的一行
	private ImageButton login;

	public MenuFragment(Activity mActivity) {
		this.mActivity = mActivity;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.fragment_menu, null);

		pages = new ArrayList<View>();
		pages.add(inflater.inflate(R.layout.layout_tab_personcenter, null));
		pages.add(inflater.inflate(R.layout.layout_tab_mycollects, null));
		pages.add(inflater.inflate(R.layout.layout_tab_pics, null));
		pages.add(inflater.inflate(R.layout.layout_tab_focus, null));
		pages.add(inflater.inflate(R.layout.layout_tab_local, null));
		pages.add(inflater.inflate(R.layout.layout_tab_settings, null));
		pages.add(inflater.inflate(R.layout.layout_tab_updatecheck, null));
		pages.add(inflater.inflate(R.layout.layout_tab_about, null));
		adapter = new MenuAdapter(getActivity(), pages);

		lv_menu = (ListView) view.findViewById(R.id.lv_menu);
		lv_menu.setAdapter(adapter);
		lv_menu.setChoiceMode(ListView.CHOICE_MODE_SINGLE);// 一定要设置这个属性，否则ListView不会刷新
		lv_menu.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				adapter.setSelectItem(position); // 记录当前选中的item

				Fragment mFragment = null;
				if(mActivity instanceof MainActivity){
					switch (position) {
					case 0:
						mFragment = new NewsFragment();
						break;

					case 1:
						mFragment = new FocusFragment();
						break;
						
					case 2:
						mFragment = new LocalFragment();
						break;
					case 3:
						mFragment = new PicsFragment();
						break;
					case 4:
						mFragment = new ReadFragment();
						break;
					case 5:
						mFragment = new TiesFragment();
						break;
					case 6:
						mFragment = new UgcFragment();
						break;
					case 7:
						mFragment = new VoteFragment();
						break;
					}
					((MainActivity) mActivity).switchContent(mFragment);
				}
			}
		});

		login = (ImageButton) view.findViewById(R.id.login);
		login.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(getActivity(), LoginActivity.class);
				startActivity(intent);
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
