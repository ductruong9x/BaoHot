package com.truongtvd.baohot;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.text.Html;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.MenuItem;
import com.truongtvd.baohot.adapter.ViewPagerAdapter;
import com.truongtvd.baohot.adapter.ViewPagerDetailAdapter;
import com.viewpagerindicator.PagerSlidingTabStrip;

public class DetailActivity extends SherlockFragmentActivity {

	private ActionBar actionBar;
	private ViewPager vpMain;
	private PagerSlidingTabStrip mIndicator;
	private ViewPagerDetailAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		actionBar = getSupportActionBar();
		actionBar.setTitle(Html.fromHtml("<font color='#ffffff' size='25'>"
				+ getString(R.string.app_name) + "</font>"));
		actionBar.setBackgroundDrawable(new ColorDrawable(Color
				.parseColor("#7e3794")));
		actionBar.setIcon(android.R.color.transparent);
		actionBar.setHomeButtonEnabled(true);
		actionBar.setDisplayHomeAsUpEnabled(true);

		vpMain = (ViewPager) findViewById(R.id.vpMain);
		mIndicator = (PagerSlidingTabStrip) findViewById(R.id.indicatorTabHome);
		mIndicator.setBackgroundDrawable(new ColorDrawable(Color
				.parseColor("#7e3794")));
		mIndicator.setIndicatorColor(Color.parseColor("#ffffff"));
		mIndicator.setTextColor(Color.WHITE);
		adapter = new ViewPagerDetailAdapter(DetailActivity.this,
				getSupportFragmentManager());
		vpMain.setAdapter(adapter);
		vpMain.setCurrentItem(0);
		mIndicator.setAllCaps(false);
		mIndicator.setViewPager(vpMain);

	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {
		case android.R.id.home:
			finish();
			break;
		}
		return super.onOptionsItemSelected(item);
	}
}
