package com.ylj.biginsight.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.widget.RelativeLayout;

public class SplashActivity extends Activity {

	private AlphaAnimation animation;
    private RelativeLayout rl_splash;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_splash);
		initAnimation();
		
		rl_splash = (RelativeLayout) findViewById(R.id.rl_splash);
		rl_splash.setAnimation(animation);
	}
	
	private void initAnimation() {
		animation = new AlphaAnimation(0.1f, 1.0f);
		animation.setDuration(1000);
		
		animation.setAnimationListener(new AnimationListener() {
			
			@Override
			public void onAnimationStart(Animation arg0) {
			}
			
			@Override
			public void onAnimationRepeat(Animation arg0) {
			}
			
			@Override
			public void onAnimationEnd(Animation arg0) {
				goHome();
			}
		});
		animation.start();
	}

	private void goHome() {
		Intent intent = new Intent(SplashActivity.this, MainActivity.class);
		startActivity(intent);
		finish();
		
	}
}
