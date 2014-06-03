package com.truongtvd.baohot.fragment;

import java.util.ArrayList;

import org.json.JSONObject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.AdapterView.OnItemClickListener;

import com.facebook.HttpMethod;
import com.facebook.Request;
import com.facebook.Response;
import com.facebook.Session;
import com.truongtvd.baohot.DetailActivity;
import com.truongtvd.baohot.R;
import com.truongtvd.baohot.adapter.ItemAdapter;
import com.truongtvd.baohot.adapter.ItemTechAdapter;
import com.truongtvd.baohot.model.ItemNewFeed;
import com.truongtvd.baohot.network.NetworkOperator;
import com.truongtvd.baohot.util.JsonUtils;
import com.truongtvd.baomoi.common.Constants;

public class TechFragment extends Fragment {
	private View mParent;
	private NetworkOperator operator;
	private Session session;
	private int limit = 300;
	private boolean check = false;
	private ListView lvListNew;
	private ItemTechAdapter adapter;
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
				intent.putExtra("POS", 1);
				startActivity(intent);
			}
		});
		return mParent;
	}

	public void init() {
		if (check) {
			return;
		}
		getNewFeed(limit);
		check = true;
	}

	private void getNewFeed(int limit) {
		String fqlQuery = "SELECT post_id, message, attachment,created_time,like_info FROM stream WHERE source_id = '"
				+ Constants.FANPAGE_KEY_TECH + "' LIMIT " + limit;
		Bundle params = new Bundle();
		params.putString("q", fqlQuery);

		// session = Session.getActiveSession();
		Request request = new Request(session, "/fql", params, HttpMethod.GET,
				new Request.Callback() {
					public void onCompleted(Response response) {
						try{
						JSONObject jso = JsonUtils.parseResponToJson(response);
						// Util.writetoFile(jso.toString(), "TUVI");
						loading.setVisibility(View.GONE);
						listnew = JsonUtils.getListItem(jso, listnew);
						adapter = new ItemTechAdapter(getActivity(),
								R.layout.item_layout, listnew);
						// lvListNew.addHeaderView(header);
						lvListNew.setAdapter(adapter);
						// // Log.e("LIST_SIZE", listItem.size() + "");

						// Log.e("NEW", jso.toString());
						} catch (Exception e) {

						}
					}
				});
		Request.executeBatchAsync(request);

	}
}
