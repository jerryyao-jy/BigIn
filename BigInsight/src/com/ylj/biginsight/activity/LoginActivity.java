package com.ylj.biginsight.activity;

import java.text.SimpleDateFormat;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.v7.app.ActionBarActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.sina.weibo.sdk.auth.AuthInfo;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.auth.WeiboAuthListener;
import com.sina.weibo.sdk.exception.WeiboException;
import com.sina.weibo.sdk.widget.LoginButton;
import com.sina.weibo.sdk.widget.LoginoutButton;
import com.tencent.connect.common.Constants;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;
import com.ylj.biginsight.threepartlogin.AccessTokenKeeper;
import com.ylj.biginsight.threepartlogin.XinLangConstants;
import com.ylj.biginsight.utils.Util;

public class LoginActivity extends ActionBarActivity {

	/** 新浪微博 start **/
	private LoginButton mLoginBtnDefault;
	private AuthInfo authInfo;
	private Button mCurrentClickedButton;
	/** 新浪微博 end */


	/** 腾讯微博 start **/
	public static String mAppid = "1103584654";
	private ImageButton mNewLoginButton;
	public static Tencent mTencent;
	/** 腾讯微博 end **/
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		
		/** 新浪微博 start **/
		authInfo = new AuthInfo(this, XinLangConstants.APP_KEY, XinLangConstants.REDIRECT_URL, XinLangConstants.SCOPE);
		mLoginBtnDefault = (LoginButton) findViewById(R.id.login_button_default);
		mLoginBtnDefault.setWeiboAuthInfo(authInfo, new AuthListener()); // 为登录按钮设置授权认证信息
		mLoginBtnDefault.setBackgroundResource(R.drawable.xinlang);
		/** 新浪微博 end */
		
		/** 腾讯微博 start **/
		initViews();
		
		if (mTencent == null) {
			mTencent = Tencent.createInstance(mAppid, this);
		}
		/** 腾讯微博 end **/
	}

	
	/**
	 * 新浪微博 ： 登入按钮的监听器，接收授权结果。
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
	 */
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		/** 新浪微博 start **/
		if (mCurrentClickedButton != null) {
			if (mCurrentClickedButton instanceof LoginButton) {
				((LoginButton) mCurrentClickedButton).onActivityResult(requestCode, resultCode, data);
			} else if (mCurrentClickedButton instanceof LoginoutButton) {
				((LoginoutButton) mCurrentClickedButton).onActivityResult(requestCode, resultCode, data);
			}
		}
		/** 新浪微博 end **/
		
		/** 腾讯微博 start **/
		if (requestCode == Constants.REQUEST_API) {
			if (resultCode == Constants.RESULT_LOGIN) {
				Tencent.handleResultData(data, loginListener);
				//Log.d(TAG, "-->onActivityResult handle logindata");
			}
		} else if (requestCode == Constants.REQUEST_APPBAR) { // app内应用吧登录
			if (resultCode == Constants.RESULT_LOGIN) {
				updateUserInfo();
				updateLoginButton();
				Util.showResultDialog(LoginActivity.this, data.getStringExtra(Constants.LOGIN_INFO), "登录成功");
			}
		}
		/** 腾讯微博 end **/
	}
	
	/** 腾讯微博 start **/
	private void initViews() {
		mNewLoginButton = (ImageButton) findViewById(R.id.new_login_btn);
		mNewLoginButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				onClickLogin();
			}
		});
		mNewLoginButton.setBackgroundResource(R.drawable.qq);
		//mUserInfo = (TextView) findViewById(R.id.user_nickname);
		//mUserLogo = (ImageView) findViewById(R.id.user_logo);
		updateLoginButton();
	}

	private void updateLoginButton() {
		if (mTencent != null && mTencent.isSessionValid()) {
			//mNewLoginButton.setTextColor(Color.RED);
			//mNewLoginButton.setText("退出帐号");
		} else {
			//mNewLoginButton.setTextColor(Color.BLUE);
			//mNewLoginButton.setText("登录");
		}
	}

	private void updateUserInfo() {
		if (mTencent != null && mTencent.isSessionValid()) {
			IUiListener listener = new IUiListener() {

				@Override
				public void onError(UiError e) {

				}

				@Override
				public void onComplete(final Object response) {
					Message msg = new Message();
					msg.obj = response;
					msg.what = 0;
					mHandler.sendMessage(msg);
					new Thread() {

						@Override
						public void run() {
							JSONObject json = (JSONObject) response;
							if (json.has("figureurl")) {
								Bitmap bitmap = null;
								try {
									bitmap = Util.getbitmap(json.getString("figureurl_qq_2"));
								} catch (JSONException e) {

								}
								Message msg = new Message();
								msg.obj = bitmap;
								msg.what = 1;
								mHandler.sendMessage(msg);
							}
						}

					}.start();
				}

				@Override
				public void onCancel() {

				}
			};
			//mInfo = new UserInfo(this, mTencent.getQQToken());
			//mInfo.getUserInfo(listener);

		} else {
			//mUserInfo.setText("");
			//mUserInfo.setVisibility(android.view.View.GONE);
			//mUserLogo.setVisibility(android.view.View.GONE);
		}
	}

	Handler mHandler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			if (msg.what == 0) {
				JSONObject response = (JSONObject) msg.obj;
				if (response.has("nickname")) {
				}
			} else if (msg.what == 1) {
				Bitmap bitmap = (Bitmap) msg.obj;
				//mUserLogo.setImageBitmap(bitmap);
				//mUserLogo.setVisibility(android.view.View.VISIBLE);
			}
		}

	};

	private void onClickLogin() {
		if (!mTencent.isSessionValid()) {
			mTencent.login(this, "all", loginListener);
			Log.d("SDKQQAgentPref", "FirstLaunch_SDK:" + SystemClock.elapsedRealtime());
		} else {
			mTencent.logout(this);
			updateUserInfo();
			updateLoginButton();
		}
	}

	public static void initOpenidAndToken(JSONObject jsonObject) {
		try {
			String token = jsonObject.getString(Constants.PARAM_ACCESS_TOKEN);
			String expires = jsonObject.getString(Constants.PARAM_EXPIRES_IN);
			String openId = jsonObject.getString(Constants.PARAM_OPEN_ID);
			if (!TextUtils.isEmpty(token) && !TextUtils.isEmpty(expires) && !TextUtils.isEmpty(openId)) {
				mTencent.setAccessToken(token, expires);
				mTencent.setOpenId(openId);
			}
		} catch (Exception e) {
		}
	}

	IUiListener loginListener = new BaseUiListener() {
		@Override
		protected void doComplete(JSONObject values) {
			Log.d("SDKQQAgentPref", "AuthorSwitch_SDK:" + SystemClock.elapsedRealtime());
			initOpenidAndToken(values);
			updateUserInfo();
			updateLoginButton();
		}
	};

	private class BaseUiListener implements IUiListener {

		@Override
		public void onComplete(Object response) {
			if (null == response) {
				Util.showResultDialog(LoginActivity.this, "1:返回为空", "登录失败");
				return;
			}
			JSONObject jsonResponse = (JSONObject) response;
			if (null != jsonResponse && jsonResponse.length() == 0) {
				Util.showResultDialog(LoginActivity.this, "2:返回为空", "登录失败");
				return;
			}
			//登录成功后的Token等信息
			//Util.showResultDialog(LoginActivity.this, response.toString(), "登录成功");
			
			Intent intent = new Intent(LoginActivity.this,LoginResultActivity.class);
			intent.putExtra("token", response.toString());
			startActivity(intent);
			
			doComplete((JSONObject) response);
		}

		protected void doComplete(JSONObject values) {

		}

		@Override
		public void onError(UiError e) {
			Util.toastMessage(LoginActivity.this, "onError: " + e.errorDetail);
			Util.dismissDialog();
		}

		@Override
		public void onCancel() {
			Util.toastMessage(LoginActivity.this, "onCancel: ");
			Util.dismissDialog();
		}
	}
	/** 腾讯微博 end **/
}
