package com.stephen.furniturerepair.gui.activity.guide;

import java.util.List;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * 自定义ViewPager适配器
 * 
 * @author Administrator
 * 
 */
public class MyViewPagerAdapter extends PagerAdapter {
	private List<ImageView> list_Views;

	public MyViewPagerAdapter(List<ImageView> list_Views) {
		this.list_Views = list_Views;
	}

	@Override
	public int getCount() {
		if (list_Views != null) {
			return list_Views.size();
		}
		return 0;
	}

	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		container.addView(list_Views.get(position));
		return list_Views.get(position);
	}

	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		container.removeView(list_Views.get(position));
	}

	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		return arg0 == arg1;
	}

}
