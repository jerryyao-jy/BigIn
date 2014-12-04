package com.ylj.biginsight.activity;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.PopupMenu.OnMenuItemClickListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;
import com.ylj.biginsight.fragment.HomeFragment;
import com.ylj.biginsight.fragment.MenuFragment;

public class MainActivity extends SlidingFragmentActivity implements OnClickListener {

	private ImageButton img_menu;
	private SlidingMenu menu;
	private MenuFragment mMenuFragment;
	private HomeFragment mHomeFragment;
	private ImageButton more;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);

		setBehindContentView(R.layout.activity_menu);
		setContentView(R.layout.activity_main);
		ShareSDK.initSDK(this);
		getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.layout_titlebar);
		// init sliding menu
		initMenu();
		initImgBtnToggle();
		initMore();

		if (savedInstanceState == null) {
			mMenuFragment = new MenuFragment();
			mHomeFragment = new HomeFragment(menu);
			getSupportFragmentManager().beginTransaction().replace(R.id.activity_menu, mMenuFragment, "Menu").commit();
			getSupportFragmentManager().beginTransaction().replace(R.id.activity_main, mHomeFragment, "Home").commit();
		}

	}

	private void initMore() {
		more = (ImageButton) findViewById(R.id.more);
		more.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				final PopupMenu popupMenu = new PopupMenu(MainActivity.this, v);
				MenuInflater menuInflater = new MenuInflater(MainActivity.this);
				menuInflater.inflate(R.menu.main, popupMenu.getMenu());
				popupMenu.show();
				popupMenu.setOnMenuItemClickListener(new OnMenuItemClickListener() {
					@Override
					public boolean onMenuItemClick(MenuItem item) {
						switch (item.getItemId()) {
						case R.id.menu_share:
							OnekeyShare oks = new OnekeyShare();
							// 分享时 Notification 的图标和文字
							oks.setNotification(R.drawable.biginsight, MainActivity.this.getString(R.string.app_name));
							// address 是接收人地址，仅在信息和邮件使用
							// oks.setAddress("12345678901");
							// title 标题，印象笔记、邮箱、信息、微信、人人网和 QQ 空间使用
							oks.setTitle(MainActivity.this.getString(R.string.share));
							// titleUrl 是标题的网络链接，仅在人人网和 QQ 空间使用
							oks.setTitleUrl("http://sharesdk.cn");
							// text 是分享文本，所有平台都需要这个字段
							oks.setText(MainActivity.this.getString(R.string.share_content));
							// imagePath 是图片的本地路径， Linked-In 以外的平台都支持此参数
							// oks.setImagePath(MainActivity.TEST_IMAGE);
							// imageUrl 是图片的网络路径，新浪微博、人人网、 QQ 空间、
							// 微信、易信、 Linked-In 支持此字段
							oks.setImageUrl("http://sharesdk.cn/ rest.png");
							// 微信、易信中使用，表示视屏地址或网页地址
							oks.setUrl("http://sharesdk.cn");
							// appPath 是待分享应用程序的本地路劲，仅在微信中使用
							// oks.setAppPath(MainActivity.TEST_IMAGE);
							// comment 是我对这条分享的评论，仅在人人网和 QQ 空间使用
							oks.setComment(MainActivity.this.getString(R.string.share));
							// site 是分享此内容的网站名称，仅在 QQ 空间使用
							oks.setSite(MainActivity.this.getString(R.string.app_name));
							// siteUrl 是分享此内容的网站地址，仅在 QQ 空间使用
							oks.setSiteUrl("http://sharesdk.cn");
							// venueName 是分享社区名称，仅在 Foursquare 使用
							// oks.setVenueName("Southeast in China");
							// venueDescription 是分享社区描述，仅在 Foursquare 使用
							// oks.setVenueDescription("This is a beautiful place!");
							// latitude 是纬度数据，仅在新浪微博、腾讯微博和 Foursquare 使用
							oks.setLatitude(23.122619f);
							// longitude 是经度数据，仅在新浪微博、腾讯微博和 Foursquare 使用
							oks.setLongitude(113.372338f);
							// 是否直接分享（ true 则直接分享）
							oks.setSilent(true);
							// 指定分享平台，和 slient 一起使用可以直接分享到指定的平台
							/*
							 * if (platform != null) {
							 * oks.setPlatform(platform); }
							 */
							// 去除注释可通过 OneKeyShareCallback 来捕获快捷分享的处理结果
							// oks.setCallback(new OneKeyShareCallback());
							// 通过 OneKeyShareCallback 来修改不同平台分享的内容
							// oks.setShareContentCustomizeCallback(new
							// ShareContentCustomizeDemo());
							oks.show(MainActivity.this);
							break;

						case R.id.menu_login:
							Intent intent = new Intent(MainActivity.this, LoginActivity.class);
							startActivity(intent);
						default:
							break;
						}
						popupMenu.dismiss();
						return false;
					}
				});
			}
		});
	}

	private void initImgBtnToggle() {
		img_menu = (ImageButton) findViewById(R.id.ib_menu);
		img_menu.setOnClickListener(this);
	}

	private void initMenu() {
		menu = getSlidingMenu();
		menu.setMode(SlidingMenu.LEFT);
		menu.setBehindOffsetRes(R.dimen.slidingMenu_offSet);
		// menu.setShadowDrawable(R.drawable.shadow);
		menu.setShadowWidth(R.dimen.shadow_width);
		menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
		menu.setFadeDegree(0.35f);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.menu_save) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onClick(View v) {

		menu.toggle();
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
			backDialog();
			return true;
		}
		return true;
	}

	protected void backDialog() {
		AlertDialog.Builder dialog = new Builder(MainActivity.this);
		dialog.setMessage("Do you really want to sign out ?");
		dialog.setTitle("Sign out");
		dialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
				finish();
				// android.os.Process.killProcess(android.os.Process.myPid());
			}
		});
		dialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
			}
		});
		dialog.show();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		ShareSDK.stopSDK(this);
	}
}
