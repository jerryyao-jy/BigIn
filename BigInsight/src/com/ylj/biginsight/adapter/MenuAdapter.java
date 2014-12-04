package com.ylj.biginsight.adapter;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class MenuAdapter extends BaseAdapter {

	private Context context;
	private List<View> list;
	private View view;
	public MenuAdapter(Context context, List<View> list) {
		this.context = context;
		this.list = list;
	}

	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if(convertView == null){
			view = list.get(position);
		}else{
			view = convertView;
		}
		return view;
	}






}
