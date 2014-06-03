package com.truongtvd.baohot;

import java.util.Arrays;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.text.Html;
import android.widget.Toast;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.facebook.HttpMethod;
import com.facebook.Request;
import com.facebook.RequestAsyncTask;
import com.facebook.Response;
import com.facebook.Session;
import com.facebook.Session.NewPermissionsRequest;
import com.facebook.Session.StatusCallback;
import com.facebook.SessionState;
import com.truongtvd.baohot.adapter.ViewPagerDetailAdapter;
import com.viewpagerindicator.PagerSlidingTabStrip;

public class DetailActivity extends SherlockFragmentActivity {

	private ActionBar actionBar;
	private ViewPager vpMain;
	private PagerSlidingTabStrip mIndicator;
	private ViewPagerDetailAdapter adapter;
	private ProgressDialog dialog;
	private String link, title, des, image;

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
		dialog = new ProgressDialog(this);
		dialog.setMessage("Sharing...");
		Intent intent = getIntent();
		link = intent.getStringExtra("LINK");
		title = intent.getStringExtra("TITLE");
		des = intent.getStringExtra("DES");
		image = intent.getStringExtra("IMAGE");
		vpMain = (ViewPager) findViewById(R.id.vpMain);
		mIndicator = (PagerSlidingTabStrip) findViewById(R.id.indicatorTabHome);
		mIndicator.setBackgroundResource(R.color.app_color);
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
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		getSupportMenuInflater().inflate(R.menu.detail, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {
		case android.R.id.home:
			finish();
			break;
		case R.id.menu_share:

			try {
				if (!Session.getActiveSession().getPermissions()
						.contains("publish_actions")) {
					NewPermissionsRequest request = new NewPermissionsRequest(
							DetailActivity.this,
							Arrays.asList("publish_actions"));
					request.setCallback(new StatusCallback() {

						@Override
						public void call(Session session, SessionState state,
								Exception exception) {
							// TODO Auto-generated method stub

						}
					});

					Session.getActiveSession().requestNewPublishPermissions(
							request);
					return true;
				}
			} catch (Exception e) {

			}
			dialog.show();
			Bundle postParams = new Bundle();
			postParams.putString("name", title);
			postParams
					.putString(
							"message",
							"Link tải ứng dụng Báo Hót cho Android: "
									+ "https://play.google.com/store/apps/details?id=com.truongtvd.baohot");
			postParams.putString("description", des);
			postParams.putString("picture", image);
			postParams.putString("link", link);

			Request.Callback callback = new Request.Callback() {
				public void onCompleted(Response response) {
					dialog.dismiss();
					Toast.makeText(DetailActivity.this, "Share successfuly	",
							Toast.LENGTH_SHORT).show();
				}
			};

			Request request = new Request(Session.getActiveSession(),
					"me/feed", postParams, HttpMethod.POST, callback);

			RequestAsyncTask task = new RequestAsyncTask(request);
			task.execute();

			break;
		}

		return super.onOptionsItemSelected(item);
	}
}
