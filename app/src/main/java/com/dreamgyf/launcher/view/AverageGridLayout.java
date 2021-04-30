package com.dreamgyf.launcher.view;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;
import android.widget.GridLayout;

public class AverageGridLayout extends GridLayout {

	private int mWidthSpacing = 0;

	private int mHeightSpacing = 20;

	public AverageGridLayout(Context context) {
		super(context);
	}

	public AverageGridLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public AverageGridLayout(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
	}

	@Override
	protected void dispatchDraw(Canvas canvas) {
		super.dispatchDraw(canvas);
		if (getLayoutParams().width != LayoutParams.WRAP_CONTENT) {
			int rowCount = getRowCount();
			int colCount = getColumnCount();

			for (int i = 0; i < getChildCount(); i++) {
				View view = getChildAt(i);
				MarginLayoutParams lp = (MarginLayoutParams) view.getLayoutParams();

				if (getLayoutParams().width != LayoutParams.WRAP_CONTENT) {
					if (i % colCount == 0) {
						lp.leftMargin = 0;
						lp.rightMargin = mWidthSpacing / 2;
					} else if (i % colCount == colCount - 1) {
						lp.leftMargin = mWidthSpacing / 2;
						lp.rightMargin = 0;
					} else {
						lp.leftMargin = mWidthSpacing / 2;
						lp.rightMargin = mWidthSpacing / 2;
					}
					if (colCount == 1) {
						lp.leftMargin = 0;
						lp.rightMargin = 0;
					}
					lp.width = getWidth() / colCount - mWidthSpacing + (mWidthSpacing / colCount - 2);
				}

				if (getLayoutParams().height != LayoutParams.WRAP_CONTENT) {
					if (i / colCount % rowCount == 0) {
						lp.topMargin = 0;
						lp.bottomMargin = mHeightSpacing / 2;
					} else if (i / colCount % rowCount == rowCount - 1) {
						lp.topMargin = mHeightSpacing / 2;
						lp.bottomMargin = 0;
					} else {
						lp.topMargin = mHeightSpacing / 2;
						lp.bottomMargin = mHeightSpacing / 2;
					}
					if (rowCount == 1) {
						lp.topMargin = 0;
						lp.bottomMargin = 0;
					}
					lp.height = getHeight() / rowCount - mHeightSpacing + (mHeightSpacing / rowCount - 2);
				}
				view.requestLayout();
			}
		}
	}
}
