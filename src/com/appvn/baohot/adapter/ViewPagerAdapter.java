package com.appvn.baohot.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import com.appvn.baohot.R;
import com.appvn.baohot.fragment.BBCFragment;
import com.appvn.baohot.fragment.EntertainmentFragment;
import com.appvn.baohot.fragment.GameFragment;
import com.appvn.baohot.fragment.NewsFragment;
import com.appvn.baohot.fragment.SportFragment;
import com.appvn.baohot.fragment.TechFragment;

public class ViewPagerAdapter extends FragmentPagerAdapter {
	private Context context;
	private String[] CONTENT;

	public ViewPagerAdapter(Context context, FragmentManager fm) {

		super(fm);
		this.context = context;
		CONTENT = new String[] { context.getString(R.string.news),
				context.getString(R.string.tech),
				context.getString(R.string.sports),
				context.getString(R.string.entertaiment),
				context.getString(R.string.game),
				context.getString(R.string.bbc) };
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
		} else if (position == 2) {
			SportFragment sportFragment = (SportFragment) object;
			sportFragment.init();
		} else if (position == 3) {
			EntertainmentFragment entertainmentFragment = (EntertainmentFragment) object;
			entertainmentFragment.init();
		} else if (position == 4) {
			GameFragment gameFragment = (GameFragment) object;
			gameFragment.init();
		} else if (position == 5) {
			BBCFragment bbcFragment = (BBCFragment) object;
			bbcFragment.init();
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
		case 2:
			fragment = new SportFragment();
			break;
		case 3:
			fragment = new EntertainmentFragment();
			break;
		case 4:
			fragment = new GameFragment();
			break;
		case 5:
			fragment = new BBCFragment();
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