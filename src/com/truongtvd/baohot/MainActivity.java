package com.truongtvd.baohot;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.text.Html;

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
		mIndicator.setBackgroundDrawable(new ColorDrawable(Color
				.parseColor("#7e3794")));
		mIndicator.setIndicatorColor(Color.parseColor("#ffffff"));
		mIndicator.setTextColor(Color.WHITE);
		adapter = new ViewPagerAdapter(MainActivity.this,
				getSupportFragmentManager());
		vpMain.setAdapter(adapter);
		vpMain.setCurrentItem(0);
		mIndicator.setAllCaps(false);
		mIndicator.setViewPager(vpMain);

	}

}
