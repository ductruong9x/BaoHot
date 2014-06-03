package com.truongtvd.baohot;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.text.Html;
import android.widget.Toast;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.facebook.FacebookException;
import com.facebook.FacebookOperationCanceledException;
import com.facebook.Session;
import com.facebook.widget.WebDialog;
import com.facebook.widget.WebDialog.OnCompleteListener;
import com.google.ads.AdRequest;
import com.google.ads.AdView;
import com.truongtvd.baohot.adapter.ViewPagerAdapter;
import com.viewpagerindicator.PagerSlidingTabStrip;

public class MainActivity extends SherlockFragmentActivity {

	private ViewPager vpMain;
	private PagerSlidingTabStrip mIndicator;
	private ViewPagerAdapter adapter;
	private ActionBar actionBar;
	private AdView adView;

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
		adView = (AdView) findViewById(R.id.ad);
		adView.loadAd(new AdRequest());

		vpMain = (ViewPager) findViewById(R.id.vpMain);
		mIndicator = (PagerSlidingTabStrip) findViewById(R.id.indicatorTabHome);

		mIndicator.setBackgroundResource(R.color.app_color);
		mIndicator.setIndicatorColor(Color.parseColor("#ffffff"));
		mIndicator.setTextColor(Color.WHITE);
		adapter = new ViewPagerAdapter(MainActivity.this,
				getSupportFragmentManager());
		vpMain.setAdapter(adapter);
		vpMain.setOffscreenPageLimit(6);
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
				} else if (pos == 5) {
					actionBar.setBackgroundDrawable(new ColorDrawable(Color
							.parseColor("#4f42d4")));
					mIndicator.setBackgroundResource(R.color.color_bbc);
					actionBar.setTitle(Html
							.fromHtml("<font color='#ffffff' size='25'>"
									+ getString(R.string.bbc) + "</font>"));
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
		danhGia();
	}

	public void danhGia() {
		SharedPreferences getPre = getSharedPreferences("SAVE", MODE_PRIVATE);
		int i = getPre.getInt("VOTE", 0);
		SharedPreferences pre;
		SharedPreferences.Editor edit;
		switch (i) {
		case 0:
			pre = getSharedPreferences("SAVE", MODE_PRIVATE);
			edit = pre.edit();
			edit.putInt("VOTE", 1);
			edit.commit();
			break;
		case 1:
			pre = getSharedPreferences("SAVE", MODE_PRIVATE);
			edit = pre.edit();
			edit.putInt("VOTE", i + 1);
			edit.commit();
			break;
		case 2:
			pre = getSharedPreferences("SAVE", MODE_PRIVATE);
			edit = pre.edit();
			edit.putInt("VOTE", i + 1);
			edit.commit();
			break;
		case 3:
			pre = getSharedPreferences("SAVE", MODE_PRIVATE);
			edit = pre.edit();
			edit.putInt("VOTE", i + 1);
			edit.commit();
			break;
		case 4:
			pre = getSharedPreferences("SAVE", MODE_PRIVATE);
			edit = pre.edit();
			edit.putInt("VOTE", i + 1);
			edit.commit();
			break;
		case 5:
			DialogVote dialog = new DialogVote(MainActivity.this);
			dialog.show();
			pre = getSharedPreferences("SAVE", MODE_PRIVATE);
			edit = pre.edit();
			edit.putInt("VOTE", 5);
			edit.commit();
			break;
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		getSupportMenuInflater().inflate(R.menu.menu, menu);

		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {

		case R.id.invate:
			sendRequestDialog();
			Toast.makeText(MainActivity.this, "Invate friend from Facebook",
					Toast.LENGTH_SHORT).show();
			break;
		case R.id.rate:
			Toast.makeText(MainActivity.this, "Thank you", Toast.LENGTH_SHORT)
					.show();
			Intent goToMarket = new Intent(Intent.ACTION_VIEW).setData(Uri
					.parse("market://details?id=" + getPackageName()));
			startActivity(goToMarket);
			break;
		case R.id.devinfo:
			Intent goMoreApp = new Intent(Intent.ACTION_VIEW)
					.setData(Uri
							.parse("https://play.google.com/store/apps/developer?id=Appvn+Software"));
			startActivity(goMoreApp);
			break;

		default:
			break;
		}

		return super.onMenuItemSelected(featureId, item);
	}

	private void sendRequestDialog() {
		Bundle params = new Bundle();
		params.putString("message", "Đọc Báo mới hàng ngày");

		WebDialog requestsDialog = (new WebDialog.RequestsDialogBuilder(
				MainActivity.this, Session.getActiveSession(), params))
				.setOnCompleteListener(new OnCompleteListener() {

					@Override
					public void onComplete(Bundle values,
							FacebookException error) {
						if (error != null) {
							if (error instanceof FacebookOperationCanceledException) {
								Toast.makeText(
										MainActivity.this
												.getApplicationContext(),
										"Request cancelled", Toast.LENGTH_SHORT)
										.show();
							} else {
								Toast.makeText(
										MainActivity.this
												.getApplicationContext(),
										"Network Error", Toast.LENGTH_SHORT)
										.show();
							}
						} else {
							final String requestId = values
									.getString("request");
							if (requestId != null) {
								Toast.makeText(
										MainActivity.this
												.getApplicationContext(),
										"Request sent", Toast.LENGTH_SHORT)
										.show();
							} else {
								Toast.makeText(
										MainActivity.this
												.getApplicationContext(),
										"Request cancelled", Toast.LENGTH_SHORT)
										.show();
							}
						}
					}

				}).build();
		requestsDialog.show();
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		if (adView != null) {
			adView.destroy();
		}
		super.onDestroy();
	}
}
