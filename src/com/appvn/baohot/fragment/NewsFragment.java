package com.appvn.baohot.fragment;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.appvn.baohot.DetailActivity;
import com.appvn.baohot.MyApplication;
import com.appvn.baohot.R;
import com.appvn.baohot.adapter.ItemAdapter;
import com.appvn.baohot.common.Constants;
import com.appvn.baohot.model.ItemNewFeed;
import com.appvn.baohot.network.NetworkOperator;
import com.appvn.baohot.util.JsonUtils;
import com.facebook.HttpMethod;
import com.facebook.Request;
import com.facebook.Request.GraphUserCallback;
import com.facebook.Response;
import com.facebook.Session;
import com.facebook.model.GraphUser;

public class NewsFragment extends Fragment {
	private View mParent;
	private NetworkOperator operator;
	private Session session;
	private int limit = 30;
	private boolean check = false;
	private ListView lvListNew;
	private ItemAdapter adapter;
	private ArrayList<ItemNewFeed> listnew = new ArrayList<ItemNewFeed>();
	private ProgressBar loading;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		session = Session.getActiveSession();
		operator = NetworkOperator.getInstance().init(getActivity());
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		mParent = inflater.inflate(R.layout.layout_main, null);
		loading = (ProgressBar) mParent.findViewById(R.id.loading);
		lvListNew = (ListView) mParent.findViewById(R.id.lvListNews);
		lvListNew.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				ItemNewFeed item = (ItemNewFeed) parent
						.getItemAtPosition(position);
				Intent intent = new Intent(getActivity(), DetailActivity.class);
				intent.putExtra("LINK", item.getLink());
				intent.putExtra("TITLE", item.getName());
				intent.putExtra("IMAGE", item.getImage());
				intent.putExtra("DES", item.getMessage());
				intent.putExtra("TITME", item.getTime());
				intent.putExtra("POST_ID", item.getPost_id());
				intent.putExtra("POS", 0);
				startActivity(intent);
			}
		});
		return mParent;
	}

	public void init() {
		if (check) {
			return;
		}

		getIDUser();
		getNewFeedDanTri(limit);
		check = true;
	}

	private void getIDUser() {
		Request request = Request.newMeRequest(session,
				new GraphUserCallback() {

					@Override
					public void onCompleted(GraphUser user, Response response) {
						// TODO Auto-generated method stub
						try {
							getUserInfo(user.getId());
						} catch (Exception e) {

						}
					}
				});
		Request.executeBatchAsync(request);
	}

	private void getUserInfo(String id) {
		String fqlQuery = "SELECT name,pic FROM user WHERE uid='" + id + "'";
		Bundle params = new Bundle();
		params.putString("q", fqlQuery);

		// session = Session.getActiveSession();
		Request request = new Request(session, "/fql", params, HttpMethod.GET,
				new Request.Callback() {
					public void onCompleted(Response response) {
						JSONObject jso = JsonUtils.parseResponToJson(response);
						try {
							JSONArray data = jso.getJSONArray("data");
							if (data.length() > 0) {
								JSONObject info = data.getJSONObject(0);
								MyApplication.setAvater(info.getString("pic"));
								MyApplication.setName(info.getString("name"));
								// getNewFeed(limit);
							}

						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

						// Log.e("USER_INFO", jso.toString());

					}
				});
		Request.executeBatchAsync(request);

	}

	private void getNewFeed(int limit) {
		String fqlQuery = "SELECT post_id, message, attachment,created_time,like_info FROM stream WHERE source_id = '"
				+ Constants.FANPAGE_KEY_HOT + "' LIMIT " + limit;
		Bundle params = new Bundle();
		params.putString("q", fqlQuery);

		// session = Session.getActiveSession();
		Request request = new Request(session, "/fql", params, HttpMethod.GET,
				new Request.Callback() {
					public void onCompleted(Response response) {
						try {
							JSONObject jso = JsonUtils
									.parseResponToJson(response);
							// Util.writetoFile(jso.toString(), "TUVI");
							loading.setVisibility(View.GONE);
							listnew = JsonUtils.getListItem(jso, listnew);
							Collections.shuffle(listnew);
							Collections.sort(listnew,
									new Comparator<ItemNewFeed>() {

										@Override
										public int compare(ItemNewFeed item1,
												ItemNewFeed item2) {
											if (item1.getTime() < item2
													.getTime()) {
												return 1;
											} else {
												if (item1.getTime() == item2
														.getTime()) {
													return 0;
												} else {
													return -1;
												}

											}
										}
									});

							adapter = new ItemAdapter(getActivity(),
									R.layout.item_layout, listnew);
							lvListNew.setAdapter(adapter);
							 Log.e("NEW", jso.toString());
						} catch (Exception e) {

						}
					}
				});
		Request.executeBatchAsync(request);

	}

	private void getNewFeedVNexpress(int limit) {
		String fqlQuery = "SELECT post_id, message, attachment,created_time,like_info FROM stream WHERE source_id = '"
				+ "262700667105773" + "' LIMIT " + limit;
		Bundle params = new Bundle();
		params.putString("q", fqlQuery);

		// session = Session.getActiveSession();
		Request request = new Request(session, "/fql", params, HttpMethod.GET,
				new Request.Callback() {
					public void onCompleted(Response response) {
						try {
							JSONObject jso = JsonUtils
									.parseResponToJson(response);
							listnew = JsonUtils.getListItem(jso, listnew);
							getNewFeedTin247(30);
							Log.e("NEW", jso.toString());
						} catch (Exception e) {

						}
					}
				});
		Request.executeBatchAsync(request);

	}
	private void getNewFeedTin247(int limit) {
		String fqlQuery = "SELECT post_id, message, attachment,created_time,like_info FROM stream WHERE source_id = '"
				+ "772006936162917" + "' LIMIT " + limit;
		Bundle params = new Bundle();
		params.putString("q", fqlQuery);

		// session = Session.getActiveSession();
		Request request = new Request(session, "/fql", params, HttpMethod.GET,
				new Request.Callback() {
					public void onCompleted(Response response) {
						try {
							JSONObject jso = JsonUtils
									.parseResponToJson(response);
							listnew = JsonUtils.getListItem(jso, listnew);
							getNewFeed(30);
							 Log.e("NEW", jso.toString());
						} catch (Exception e) {

						}
					}
				});
		Request.executeBatchAsync(request);

	}

	

	private void getNewFeedDanTri(int limit) {
		String fqlQuery = "SELECT post_id, message, attachment,created_time,like_info FROM stream WHERE source_id = '"
				+ "604923616218501" + "' LIMIT " + limit;
		Bundle params = new Bundle();
		params.putString("q", fqlQuery);

		// session = Session.getActiveSession();
		Request request = new Request(session, "/fql", params, HttpMethod.GET,
				new Request.Callback() {
					public void onCompleted(Response response) {
						try {
							JSONObject jso = JsonUtils
									.parseResponToJson(response);

							listnew = JsonUtils.getListItem(jso, listnew);
							 Log.e("NEW", jso.toString());
							getNewFeedVNexpress(30);
						} catch (Exception e) {

						}
					}
				});
		Request.executeBatchAsync(request);

	}
}
