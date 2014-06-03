package com.truongtvd.baohot.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.truongtvd.baohot.R;
import com.truongtvd.baohot.model.ItemNewFeed;
import com.truongtvd.baohot.network.MyVolley;
import com.truongtvd.baohot.util.Util;
import com.truongtvd.baohot.view.FadeInNetworkImageView;

public class ItemTechAdapter extends ArrayAdapter<ItemNewFeed> {

	private Context context;
	private ArrayList<ItemNewFeed> listNew = null;
	private LayoutInflater inflater;

	public ItemTechAdapter(Context context, int resource,
			ArrayList<ItemNewFeed> listNew) {
		super(context, resource, listNew);
		// TODO Auto-generated constructor stub
		this.context = context;
		this.listNew = listNew;
		inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder = null;
		if (convertView == null) {
			convertView = inflater.inflate(R.layout.item_layout, null);
			viewHolder = new ViewHolder();
			viewHolder.tvTitle = (TextView) convertView
					.findViewById(R.id.tvTitle);
			viewHolder.tvDes = (TextView) convertView.findViewById(R.id.tvDes);
			viewHolder.tvTime = (TextView) convertView
					.findViewById(R.id.tvTime);
			viewHolder.imgItem = (FadeInNetworkImageView) convertView
					.findViewById(R.id.imgItem);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();

		}
		ItemNewFeed item = getItem(position);

		viewHolder.tvTitle.setText(item.getName() + "");
		viewHolder.tvDes.setText(item.getMessage() + "");
		viewHolder.tvTime.setText(Util.convertDate(item.getTime()) + "");
		viewHolder.imgItem.setImageUrl(item.getImage(),
				MyVolley.getImageLoader());

		return convertView;
	}

	private class ViewHolder {
		public TextView tvTitle, tvDes, tvTime;
		public FadeInNetworkImageView imgItem;
	}
}
