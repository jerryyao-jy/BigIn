package com.ylj.biginsight.activity;

import java.text.SimpleDateFormat;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.sina.weibo.sdk.auth.AuthInfo;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.auth.WeiboAuthListener;
import com.sina.weibo.sdk.exception.WeiboException;
import com.sina.weibo.sdk.widget.LoginButton;
import com.ylj.biginsight.threepartlogin.AccessTokenKeeper;
import com.ylj.biginsight.threepartlogin.Constants;

public class LoginActivity extends ActionBarActivity {

	private LoginButton mLoginBtnDefault;
	private TextView mTokenView;
	private AuthInfo authInfo;
	/** 登陆认证对应的listener *//*
	private AuthListener mLoginListener = new AuthListener();
*/
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);

		authInfo = new AuthInfo(this, Constants.APP_KEY, Constants.REDIRECT_URL, Constants.SCOPE);
		mLoginBtnDefault = (LoginButton) findViewById(R.id.login_button_default);
		mLoginBtnDefault.setWeiboAuthInfo(authInfo, new AuthListener()); // 为按钮设置授权认证信息
		mLoginBtnDefault.setStyle(LoginButton.LOGIN_INCON_STYLE_3);
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
				String format = "Token：%1$s \n有效期：%2$s";
				mTokenView.setText(String.format(format, accessToken.getToken(), date));

				AccessTokenKeeper.writeAccessToken(getApplicationContext(), accessToken);
			}
		}

		@Override
		public void onWeiboException(WeiboException e) {
			Toast.makeText(LoginActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
		}

		@Override
		public void onCancel() {
			Toast.makeText(LoginActivity.this, "canceled", Toast.LENGTH_SHORT).show();
		}
	}
}
