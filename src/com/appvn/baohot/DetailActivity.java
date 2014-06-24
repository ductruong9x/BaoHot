package com.appvn.baohot;

import java.util.Arrays;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.text.Html;
import android.util.Log;
import android.widget.Toast;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.appvn.baohot.adapter.ViewPagerDetailAdapter;
import com.facebook.FacebookException;
import com.facebook.FacebookOperationCanceledException;
import com.facebook.HttpMethod;
import com.facebook.Request;
import com.facebook.RequestAsyncTask;
import com.facebook.Response;
import com.facebook.Session;
import com.facebook.Session.NewPermissionsRequest;
import com.facebook.Session.StatusCallback;
import com.facebook.SessionState;
import com.facebook.widget.WebDialog;
import com.facebook.widget.WebDialog.OnCompleteListener;
import com.viewpagerindicator.PagerSlidingTabStrip;

public class DetailActivity extends SherlockFragmentActivity {

	private ActionBar actionBar;
	private ViewPager vpMain;
	private PagerSlidingTabStrip mIndicator;
	private ViewPagerDetailAdapter adapter;
	private ProgressDialog dialog, dialoglike;
	private String link, title, des, image, post_id;
	private int pos;

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
		dialoglike = new ProgressDialog(this);
		dialoglike.setMessage("Like...");
		Intent intent = getIntent();
		link = intent.getStringExtra("LINK");
		title = intent.getStringExtra("TITLE");
		des = intent.getStringExtra("DES");
		image = intent.getStringExtra("IMAGE");
		pos = intent.getIntExtra("POS", 0);
		post_id = intent.getStringExtra("POST_ID");
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

		if (pos == 0) {
			actionBar.setBackgroundDrawable(new ColorDrawable(Color
					.parseColor("#7e3794")));
			mIndicator.setBackgroundResource(R.color.app_color);
			actionBar.setTitle(Html.fromHtml("<font color='#ffffff' size='25'>"
					+ getString(R.string.news) + "</font>"));
		} else if (pos == 1) {
			actionBar.setBackgroundDrawable(new ColorDrawable(Color
					.parseColor("#008aa7")));
			mIndicator.setBackgroundResource(R.color.color_tech);
			actionBar.setTitle(Html.fromHtml("<font color='#ffffff' size='25'>"
					+ getString(R.string.tech) + "</font>"));
		} else if (pos == 2) {
			actionBar.setBackgroundDrawable(new ColorDrawable(Color
					.parseColor("#508416")));
			mIndicator.setBackgroundResource(R.color.color_sport);
			actionBar.setTitle(Html.fromHtml("<font color='#ffffff' size='25'>"
					+ getString(R.string.sports) + "</font>"));
		} else if (pos == 3) {
			actionBar.setBackgroundDrawable(new ColorDrawable(Color
					.parseColor("#ef6c00")));
			mIndicator.setBackgroundResource(R.color.color_entertaiment);
			actionBar.setTitle(Html.fromHtml("<font color='#ffffff' size='25'>"
					+ getString(R.string.entertaiment) + "</font>"));
		} else if (pos == 4) {
			actionBar.setBackgroundDrawable(new ColorDrawable(Color
					.parseColor("#e04b28")));
			mIndicator.setBackgroundResource(R.color.color_game);
			actionBar.setTitle(Html.fromHtml("<font color='#ffffff' size='25'>"
					+ getString(R.string.game) + "</font>"));
		} else if (pos == 5) {
			actionBar.setBackgroundDrawable(new ColorDrawable(Color
					.parseColor("#4f42d4")));
			mIndicator.setBackgroundResource(R.color.color_bbc);
			actionBar.setTitle(Html.fromHtml("<font color='#ffffff' size='25'>"
					+ getString(R.string.bbc) + "</font>"));
		}

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
//
//			try {
//				if (!Session.getActiveSession().getPermissions()
//						.contains("publish_actions")) {
//					NewPermissionsRequest request = new NewPermissionsRequest(
//							DetailActivity.this,
//							Arrays.asList("publish_actions"));
//					request.setCallback(new StatusCallback() {
//
//						@Override
//						public void call(Session session, SessionState state,
//								Exception exception) {
//							// TODO Auto-generated method stub
//
//						}
//					});
//
//					Session.getActiveSession().requestNewPublishPermissions(
//							request);
//					return true;
//				}
//			} catch (Exception e) {
//
//			}
//			DialogShare dialog=new DialogShare(DetailActivity.this, des, image, link, title);
//			dialog.show();
			shareWeb();
			break;

		case R.id.menu_like:

			try {
				if (!Session.getActiveSession().getPermissions()
						.contains("publish_actions")) {
					NewPermissionsRequest requestlike = new NewPermissionsRequest(
							DetailActivity.this,
							Arrays.asList("publish_actions"));
					requestlike.setCallback(new StatusCallback() {

						@Override
						public void call(Session session, SessionState state,
								Exception exception) {
							// TODO Auto-generated method stub

						}
					});

					Session.getActiveSession().requestNewPublishPermissions(
							requestlike);
					return true;
				}
			} catch (Exception e) {

			}
			dialoglike.show();

			Request.Callback callbacklike = new Request.Callback() {
				public void onCompleted(Response response) {
					Log.e("LIKE", response.toString());
					dialoglike.dismiss();
					Toast.makeText(DetailActivity.this, "Like successfuly	",
							Toast.LENGTH_SHORT).show();
				}
			};

			Request requestlike = new Request(Session.getActiveSession(),
					post_id + "/likes", null, HttpMethod.POST, callbacklike);

			RequestAsyncTask tasklike = new RequestAsyncTask(requestlike);
			tasklike.execute();

			break;
		}

		return super.onOptionsItemSelected(item);
	}
	
	private void shareWeb() {
		Bundle params = new Bundle();
		params.putString("name", title);
		params.putString("caption", "Xem thêm");
		params.putString("description", des);
		params.putString("link",link);
//		params.putString("picture", image);

		WebDialog feedDialog = (new WebDialog.FeedDialogBuilder(DetailActivity.this,
				Session.getActiveSession(), params)).setOnCompleteListener(
				new OnCompleteListener() {

					@Override
					public void onComplete(Bundle values,
							FacebookException error) {
						if (error == null) {
							// When the story is posted, echo the
							// success
							// and the post Id.
							final String postId = values.getString("post_id");
							if (postId != null) {
//								Toast.makeText(DetailActivity.this,
//										"Posted story, id: " + postId,
//										Toast.LENGTH_SHORT).show();
							} else {
								// User clicked the Cancel button
								Toast.makeText(
										DetailActivity.this.getApplicationContext(),
										"Publish cancelled", Toast.LENGTH_SHORT)
										.show();
							}
						} else if (error instanceof FacebookOperationCanceledException) {
							// User clicked the "x" button
							Toast.makeText(
									DetailActivity.this.getApplicationContext(),
									"Publish cancelled", Toast.LENGTH_SHORT)
									.show();
						} else {
							// Generic, ex: network error
							Toast.makeText(
									DetailActivity.this.getApplicationContext(),
									"Error posting story", Toast.LENGTH_SHORT)
									.show();
						}
					}

				}).build();
		feedDialog.show();
	}
}
