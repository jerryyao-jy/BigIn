package com.ylj.biginsight.activity;

import java.text.SimpleDateFormat;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.sina.weibo.sdk.auth.AuthInfo;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.auth.WeiboAuthListener;
import com.sina.weibo.sdk.exception.WeiboException;
import com.sina.weibo.sdk.widget.LoginButton;
import com.sina.weibo.sdk.widget.LoginoutButton;
import com.ylj.biginsight.threepartlogin.AccessTokenKeeper;
import com.ylj.biginsight.threepartlogin.Constants;

public class LoginActivity extends ActionBarActivity {

	private LoginButton mLoginBtnDefault;
	//private TextView mTokenView;
	private AuthInfo authInfo;
	private Button mCurrentClickedButton;

	/** 登陆认证对应的listener */

	// private AuthListener mLoginListener = new AuthListener();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);

		//mTokenView = (TextView) findViewById(R.id.result);
		
		authInfo = new AuthInfo(this, Constants.APP_KEY, Constants.REDIRECT_URL, Constants.SCOPE);
		mLoginBtnDefault = (LoginButton) findViewById(R.id.login_button_default);
		mLoginBtnDefault.setWeiboAuthInfo(authInfo, new AuthListener()); // 为按钮设置授权认证信息
		mLoginBtnDefault.setBackgroundResource(R.drawable.xinlang);
		//mLoginBtnDefault.setStyle(LoginButton.LOGIN_INCON_STYLE_3);
	}

	/**
	 * 登入按钮的监听器，接收授权结果。
	 */
	private class AuthListener implements WeiboAuthListener {
		@Override
		public void onComplete(Bundle values) {
			Oauth2AccessToken accessToken = Oauth2AccessToken.parseAccessToken(values);
			if (accessToken != null && accessToken.isSessionValid()) {
				String date = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new java.util.Date(accessToken.getExpiresTime()));
				String format = getString(R.string.weibosdk_demo_token_to_string_format_1);
				AccessTokenKeeper.writeAccessToken(getApplicationContext(), accessToken);
				
				Intent intent = new Intent(LoginActivity.this,LoginResultActivity.class);
				intent.putExtra("token", String.format(format, accessToken.getToken(), date));
				startActivity(intent);
			}
		}

		@Override
		public void onWeiboException(WeiboException e) {
			Toast.makeText(LoginActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
		}

		@Override
		public void onCancel() {
			Toast.makeText(LoginActivity.this, R.string.weibosdk_demo_toast_auth_canceled, Toast.LENGTH_SHORT).show();
		}
	}

	/**
	 * 当 SSO 授权 Activity 退出时，该函数被调用。
	 * 
	 * @see {@link Activity#onActivityResult}
	 */
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		if (mCurrentClickedButton != null) {
			if (mCurrentClickedButton instanceof LoginButton) {
				((LoginButton) mCurrentClickedButton).onActivityResult(requestCode, resultCode, data);
			} else if (mCurrentClickedButton instanceof LoginoutButton) {
				((LoginoutButton) mCurrentClickedButton).onActivityResult(requestCode, resultCode, data);
			}
		}
	}

	private OnClickListener mButtonClickListener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			if (v instanceof Button) {
				mCurrentClickedButton = (Button) v;
			}
		}
	};
}
