package com.ylj.biginsight.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.TextUtils;
import android.widget.TextView;

public class LoginResultActivity extends ActionBarActivity {

	private TextView mTokenView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login_success);

		Intent intent = getIntent();
		String token = intent.getStringExtra("token");
		mTokenView = (TextView) findViewById(R.id.result);
		if (!TextUtils.isEmpty(token)) {
			mTokenView.setText(token);
		}

	}
}
