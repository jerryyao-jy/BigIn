package com.ylj.biginsight.adapter;

import java.util.List;

import com.ylj.biginsight.activity.R;
import com.ylj.biginsight.fragment.MenuFragment;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class MenuAdapter extends BaseAdapter {
	private static int selectItem = -1;
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
		if (convertView == null) {
			view = list.get(position);
		} else {
			view = convertView;
		}

		TextView tv = (TextView) view.findViewById(R.id.description);

		if (selectItem == position) {
			view.setSelected(true);
			view.setPressed(true);
			view.setBackgroundResource(R.drawable.menu_item_bg_select);
			tv.setTextColor(context.getResources().getColor(R.color.menu_item_text_color));// 更改字体颜色
		} else {
			view.setSelected(false);
			view.setPressed(false);
			view.setBackgroundColor(Color.TRANSPARENT);
			tv.setTextColor(context.getResources().getColor(R.color.gray));
		}

		return view;
	}

	public void setSelectItem(int selectItem) {
		this.selectItem = selectItem;
		notifyDataSetInvalidated();
	}

}
