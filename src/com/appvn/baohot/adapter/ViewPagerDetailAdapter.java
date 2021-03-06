package com.appvn.baohot.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import com.appvn.baohot.R;
import com.appvn.baohot.fragment.CommentFragment;
import com.appvn.baohot.fragment.DetailFragment;

public class ViewPagerDetailAdapter extends FragmentPagerAdapter {
	private Context context;
	private String[] CONTENT;

	public ViewPagerDetailAdapter(Context context, FragmentManager fm) {

		super(fm);
		this.context = context;
		CONTENT = new String[] { context.getString(R.string.detail),
				context.getString(R.string.comment) };
	}

	Fragment fragment;

	@Override
	public void setPrimaryItem(ViewGroup container, int position, Object object) {
		// TODO Auto-generated method stub
		super.setPrimaryItem(container, position, object);
		if (position == 0) {
			DetailFragment detailFragment = (DetailFragment) object;
			detailFragment.init();
		} else if (position == 1) {
			CommentFragment commentFragment = (CommentFragment) object;
			commentFragment.init();
		}

	}

	@Override
	public Fragment getItem(int index) {

		switch (index) {
		case 0:
			fragment = new DetailFragment();

			break;
		case 1:
			fragment = new CommentFragment();
			break;

		default:
			break;
		}
		return fragment;

	}

	@Override
	public CharSequence getPageTitle(int position) {
		return CONTENT[position];
	}

	@Override
	public void notifyDataSetChanged() {
		super.notifyDataSetChanged();
	}

	@Override
	public int getCount() {
		return CONTENT.length;
	}

}