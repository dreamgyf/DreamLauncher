package com.dreamgyf.launcher.touch;

import android.content.Context;
import android.view.View;

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

		DragShadowView dragShadowView = new DragShadowView(mContext, view, dragItemInfo.lastMoveX, dragItemInfo.lastMoveY);
		dragShadowView.bindDragLayout(dragLayout);
		dragShadowView.show();

		mLastView = view;
		mLastShadowView = dragShadowView;
	}

	public boolean handleMoveEvent(float x, float y) {
		if (mLastView != null && mLastShadowView != null) {
			mLastShadowView.move(x, y);
			return true;
		}
		return false;
	}

	public boolean handleEndEvent() {
		if (mLastView != null && mLastShadowView != null) {
			mLastView.setVisibility(View.VISIBLE);
			mLastShadowView.dismiss();
			mLastShadowView = null;
			mLastView = null;
			return true;
		}
		return false;
	}
}
