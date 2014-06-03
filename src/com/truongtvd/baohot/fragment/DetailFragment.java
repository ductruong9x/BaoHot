package com.truongtvd.baohot.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.webkit.WebView.FindListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.actionbarsherlock.app.ActionBar;
import com.google.ads.AdRequest;
import com.google.ads.AdView;
import com.truongtvd.baohot.R;
import com.truongtvd.baohot.network.MyVolley;
import com.truongtvd.baohot.util.Util;
import com.truongtvd.baohot.view.FadeInNetworkImageView;

public class DetailFragment extends Fragment {

	private View mParent;
	private String link, title, des, image;
	private int time;
	private String timeformat;
	private ActionBar actionBar;
	private TextView tvTitle, tvDes, tvTime;
	private FadeInNetworkImageView imgDetail;
	private Button btnView;
	private WebView webDetail;
	private ProgressBar loading;
	private LinearLayout layout_web;
	private AdView adView;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		mParent = inflater.inflate(R.layout.detail_activity, null);
		adView = (AdView) mParent.findViewById(R.id.adDetail);
		adView.loadAd(new AdRequest());
		tvTitle = (TextView) mParent.findViewById(R.id.tvTitle);
		tvTime = (TextView) mParent.findViewById(R.id.tvTime);
		tvDes = (TextView) mParent.findViewById(R.id.tvDes);
		imgDetail = (FadeInNetworkImageView) mParent
				.findViewById(R.id.imgDetail);
		webDetail = (WebView) mParent.findViewById(R.id.webDetail);
		btnView = (Button) mParent.findViewById(R.id.btnView);
		loading = (ProgressBar) mParent.findViewById(R.id.load);
		layout_web = (LinearLayout) mParent.findViewById(R.id.layout_web);
		Intent intent = getActivity().getIntent();
		link = intent.getStringExtra("LINK");
		title = intent.getStringExtra("TITLE");
		des = intent.getStringExtra("DES");
		image = intent.getStringExtra("IMAGE");
		time = intent.getIntExtra("TIME", 0);
		timeformat = Util.convertDate(time);
		tvTitle.setText(title);
		tvTime.setText(timeformat);
		tvDes.setText(des);
		imgDetail.setImageUrl(image, MyVolley.getImageLoader());
		loading.setMax(100);
		webDetail.setWebChromeClient(new MyWebChrome());
		webDetail.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
		webDetail.getSettings().setDomStorageEnabled(true);
		webDetail.getSettings().setDatabaseEnabled(true);
		webDetail.getSettings().setAppCacheEnabled(true);
		webDetail.setWebViewClient(new WebViewClient());
		btnView.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				layout_web.setVisibility(View.VISIBLE);
				webDetail.getSettings().setJavaScriptEnabled(true);
				webDetail.loadUrl(link);
//				if (adView != null) {
//					adView.destroy();
//				}
			}
		});
		return mParent;
	}

	public void init() {

	}

	private class MyWebChrome extends WebChromeClient {

		@Override
		public void onProgressChanged(WebView view, int newProgress) {
			loading.setProgress(newProgress);
			super.onProgressChanged(view, newProgress);
		}

	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		if (adView != null) {
			adView.destroy();
		}
		super.onDestroy();
	}
}
