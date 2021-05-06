package com.dreamgyf.launcher.touch;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.view.View;

import com.dreamgyf.launcher.util.PositionUtil;
import com.dreamgyf.launcher.view.DragLayout;
import com.dreamgyf.launcher.view.DragShadowView;

public class DragController {

	private final Context mContext;

	private View mLastView;

	private DragShadowView mLastShadowView;

	public DragController(Context context) {
		mContext = context;
	}

	public void startDrag(DragItemInfo dragItemInfo) {
		View view = dragItemInfo.view;
		view.setVisibility(View.INVISIBLE);

		View v = (View) view.getParent();
		while (v != null && !(v instanceof DragLayout)) {
			v = (View) v.getParent();
		}
		DragLayout dragLayout = (DragLayout) v;

		DragShadowView dragShadowView = new DragShadowView(mContext, view);
		dragShadowView.bindDragLayout(dragLayout);
		dragShadowView.show();

		mLastView = view;
		mLastShadowView = dragShadowView;
	}

	public void handleMoveEvent(float x, float y) {

	}

	public void handleEndEvent() {
		if (mLastView != null && mLastShadowView != null) {
			mLastView.setVisibility(View.VISIBLE);
			mLastShadowView.dismiss();
			mLastShadowView = null;
			mLastView = null;
		}
	}
}
