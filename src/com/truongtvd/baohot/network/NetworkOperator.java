package com.truongtvd.baohot.network;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.json.JSONObject;

import android.content.Context;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.truongtvd.baomoi.common.Constants;

public class NetworkOperator {
	private static final String TAG = NetworkOperator.class.getSimpleName();
	private static NetworkOperator defaultInstance;
	private Context context;
	private RequestQueue requestQueue;
	String url = "https://graph.facebook.com/fql?q=";

	public static NetworkOperator getInstance() {
		if (defaultInstance == null) {
			synchronized (NetworkOperator.class) {
				if (defaultInstance == null) {
					defaultInstance = new NetworkOperator();
				}
			}
		}
		return defaultInstance;
	}

	public NetworkOperator init(Context context) {
		this.context = context;
		requestQueue = MyVolley.getRequestQueue();
		return this;
	}

	public void getFanpageInfoHot(
			Response.Listener<JSONObject> responseSuccessListener,
			Response.ErrorListener responseErrorListener) {

		MyGetRequest request = new MyGetRequest(context,
				Constants.FANPAGE_URL_HOT, null, responseSuccessListener,
				responseErrorListener);
		requestQueue.add(request);
	}

	public void getFanpageInfoTech(
			Response.Listener<JSONObject> responseSuccessListener,
			Response.ErrorListener responseErrorListener) {

		MyGetRequest request = new MyGetRequest(context,
				Constants.FANPAGE_URL_TECH, null, responseSuccessListener,
				responseErrorListener);
		requestQueue.add(request);
	}

	public void getNewFeedHot(int limit,
			Response.Listener<JSONObject> responseSuccessListener,
			Response.ErrorListener responseErrorListener) {

		String params = "select object_id,caption,src_big,like_info,comment_info,created,link FROM photo WHERE owner = '"
				+ Constants.FANPAGE_KEY_HOT + "'  LIMIT " + limit;

		String endpoint = "";

		try {
			endpoint = url + URLEncoder.encode(params, "UTF-8");
			Log.e("ENDPOINT", endpoint);
			MyGetRequest request = new MyGetRequest(context, endpoint, null,
					responseSuccessListener, responseErrorListener);
			requestQueue.add(request);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void getNewFeedTech(int limit,
			Response.Listener<JSONObject> responseSuccessListener,
			Response.ErrorListener responseErrorListener) {

		String params = "select object_id,caption,src_big,like_info,comment_info,created,link FROM photo WHERE owner = '"
				+ Constants.FANPAGE_KEY_TECH + "'  LIMIT " + limit;

		String endpoint = "";

		try {
			endpoint = url + URLEncoder.encode(params, "UTF-8");
			Log.e("ENDPOINT", endpoint);
			MyGetRequest request = new MyGetRequest(context, endpoint, null,
					responseSuccessListener, responseErrorListener);
			requestQueue.add(request);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// Get newsfeed comments
	public void getNewsFeedComments(String id, int limit,
			Response.Listener<JSONObject> responseSuccessListener,
			Response.ErrorListener responseErrorListener) {
		String url = "https://graph.facebook.com/fql?q=";
		String params = "{'comment_data':'select id,text,likes,fromid,user_likes,time from comment WHERE post_id = "
				+ id
				+ " LIMIT "
				+ limit
				+ "', 'user_data':'select uid,name,pic from user WHERE uid IN (SELECT fromid from #comment_data)'}";
		String endpoint = "";
		try {
			endpoint = url + URLEncoder.encode(params, "UTF-8").toString();
			System.err.println(endpoint);
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}

		MyGetRequest jr = new MyGetRequest(context, endpoint, null,
				responseSuccessListener, responseErrorListener);
		requestQueue.add(jr);
	}
}
