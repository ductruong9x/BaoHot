package com.truongtvd.baohot.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import com.truongtvd.baohot.R;
import com.truongtvd.baohot.fragment.NewsFragment;
import com.truongtvd.baohot.fragment.TechFragment;

public class ViewPagerAdapter extends FragmentPagerAdapter {
	private Context context;
	private String[] CONTENT;

	public ViewPagerAdapter(Context context, FragmentManager fm) {

		super(fm);
		this.context = context;
		CONTENT = new String[] { context.getString(R.string.news),
				context.getString(R.string.tech) };
	}

	Fragment fragment;

	@Override
	public void setPrimaryItem(ViewGroup container, int position, Object object) {
		// TODO Auto-generated method stub
		super.setPrimaryItem(container, position, object);
		if (position == 0) {
			NewsFragment newfFeedFragment = (NewsFragment) object;
			newfFeedFragment.init();
		} else if (position == 1) {
			TechFragment codeFragment = (TechFragment) object;
			codeFragment.init();
		}

	}

	@Override
	public Fragment getItem(int index) {

		switch (index) {
		case 0:
			fragment = new NewsFragment();

			break;
		case 1:
			fragment = new TechFragment();
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