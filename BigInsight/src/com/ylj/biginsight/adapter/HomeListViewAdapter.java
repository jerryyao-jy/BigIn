package com.ylj.biginsight.adapter;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.ylj.biginsight.activity.R;
import com.ylj.biginsight.model.NewsModel;

public class HomeListViewAdapter extends BaseAdapter {

	private Context context;
	private List<NewsModel> list;
	private View view;
	private TextView tv_title,tv_content;
	private ImageView iv_image;
	private ImageView iv_comment;
	
	public HomeListViewAdapter(Context context, List<NewsModel> list) {
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
		if(convertView != null){
			view = convertView;
		}else{
			view = View.inflate(context, R.layout.layout_listview_home, null);
		}
		
		tv_title = (TextView) view.findViewById(R.id.tv_title);
		tv_title.setText(list.get(position).getTitle());
		tv_content = (TextView) view.findViewById(R.id.tv_content);
		tv_content.setText(list.get(position).getContent());
		iv_image = (ImageView) view.findViewById(R.id.iv_image);
		iv_image.setImageResource(list.get(position).getShowImage());
		iv_comment = (ImageView) view.findViewById(R.id.iv_comment);
		iv_comment.setBackgroundResource(list.get(position).getComment());
		return view;
	}

}
