package com.ylj.biginsight.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.widget.TextView;

public class NewsDetailActivity extends Activity {

	private TextView tv_title,tv_content;
	protected void onCreate(android.os.Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_newsdetail);
		
		Intent intent = getIntent();
		String title = intent.getStringExtra("title");
		String content = intent.getStringExtra("content");
		
		tv_title = (TextView) findViewById(R.id.tv_title);
		tv_title.setText(title);
		tv_content = (TextView) findViewById(R.id.tv_content);
		tv_content.setText(content);
	};
}
