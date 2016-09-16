package com.stephen.furniturerepair.gui.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

public class FillHeightGridView extends GridView {

	public FillHeightGridView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	public FillHeightGridView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public FillHeightGridView(Context context) {
		super(context);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
				MeasureSpec.AT_MOST);
		super.onMeasure(widthMeasureSpec, expandSpec);
	}

}
