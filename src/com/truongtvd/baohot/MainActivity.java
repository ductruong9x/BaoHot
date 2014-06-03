package com.truongtvd.baohot;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.text.Html;
import android.util.Log;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.truongtvd.baohot.adapter.ViewPagerAdapter;
import com.viewpagerindicator.PagerSlidingTabStrip;

public class MainActivity extends SherlockFragmentActivity {

	private ViewPager vpMain;
	private PagerSlidingTabStrip mIndicator;
	private ViewPagerAdapter adapter;
	private ActionBar actionBar;

	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		setContentView(R.layout.main);
		actionBar = getSupportActionBar();
		actionBar.setTitle(Html.fromHtml("<font color='#ffffff' size='25'>"
				+ getString(R.string.app_name) + "</font>"));
		actionBar.setBackgroundDrawable(new ColorDrawable(Color
				.parseColor("#7e3794")));
		actionBar.setIcon(R.drawable.ic_actionbar);
		vpMain = (ViewPager) findViewById(R.id.vpMain);
		mIndicator = (PagerSlidingTabStrip) findViewById(R.id.indicatorTabHome);

		mIndicator.setBackgroundResource(R.color.app_color);
		mIndicator.setIndicatorColor(Color.parseColor("#ffffff"));
		mIndicator.setTextColor(Color.WHITE);
		adapter = new ViewPagerAdapter(MainActivity.this,
				getSupportFragmentManager());
		vpMain.setAdapter(adapter);
		vpMain.setCurrentItem(0);

		mIndicator.setAllCaps(false);
		mIndicator.setViewPager(vpMain);

		mIndicator.setOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageSelected(int pos) {
				// TODO Auto-generated method stub
				// Log.e("onPageSelected", "POS: " + pos);
				if (pos == 0) {
					actionBar.setBackgroundDrawable(new ColorDrawable(Color
							.parseColor("#7e3794")));
					mIndicator.setBackgroundResource(R.color.app_color);
					actionBar.setTitle(Html
							.fromHtml("<font color='#ffffff' size='25'>"
									+ getString(R.string.news) + "</font>"));
				} else if (pos == 1) {
					actionBar.setBackgroundDrawable(new ColorDrawable(Color
							.parseColor("#008aa7")));
					mIndicator.setBackgroundResource(R.color.color_tech);
					actionBar.setTitle(Html
							.fromHtml("<font color='#ffffff' size='25'>"
									+ getString(R.string.tech) + "</font>"));
				} else if (pos == 2) {
					actionBar.setBackgroundDrawable(new ColorDrawable(Color
							.parseColor("#508416")));
					mIndicator.setBackgroundResource(R.color.color_sport);
					actionBar.setTitle(Html
							.fromHtml("<font color='#ffffff' size='25'>"
									+ getString(R.string.sports) + "</font>"));
				} else if (pos == 3) {
					actionBar.setBackgroundDrawable(new ColorDrawable(Color
							.parseColor("#ef6c00")));
					mIndicator
							.setBackgroundResource(R.color.color_entertaiment);
					actionBar.setTitle(Html
							.fromHtml("<font color='#ffffff' size='25'>"
									+ getString(R.string.entertaiment)
									+ "</font>"));
				} else if (pos == 4) {
					actionBar.setBackgroundDrawable(new ColorDrawable(Color
							.parseColor("#e04b28")));
					mIndicator.setBackgroundResource(R.color.color_game);
					actionBar.setTitle(Html
							.fromHtml("<font color='#ffffff' size='25'>"
									+ getString(R.string.game) + "</font>"));
				}
			}

			@Override
			public void onPageScrolled(int pos, float arg1, int arg2) {
				// TODO Auto-generated method stub
				// Log.e("onPageScrolled", "POS: " + pos);
			}

			@Override
			public void onPageScrollStateChanged(int pos) {
				// TODO Auto-generated method stub
				// Log.e("onPageScrollStateChanged", "POS: " + pos);
			}
		});
	}

}
