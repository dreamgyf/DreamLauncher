package com.dreamgyf.launcher.touch;

import android.content.Context;
import android.graphics.Rect;
import android.util.Log;
import android.view.View;

import com.dreamgyf.launcher.view.DragLayout;
import com.dreamgyf.launcher.view.DragShadowView;

import java.util.ArrayList;
import java.util.List;

public class DragController {

	private final Context mContext;

	private DragLayout mDragLayout;

	private final List<DropContainer> mDropContainers = new ArrayList<>();

	private DragEvent mDragEvent;

	public DragController(Context context) {
		mContext = context;
	}

	public void attach(DragLayout dragLayout) {
		mDragLayout = dragLayout;
	}

	public void addDropContainer(DropContainer dropContainer) {
		mDropContainers.add(dropContainer);
	}

	public void startDrag(DragItemInfo dragItemInfo) {
		mDragEvent = new DragEvent();
		mDragEvent.originView = dragItemInfo.view;
		mDragEvent.shadowView = new DragShadowView(mContext, dragItemInfo.view, dragItemInfo.lastMoveX, dragItemInfo.lastMoveY);
		mDragEvent.shadowView.bindDragLayout(mDragLayout);

		mDragEvent.originView.setVisibility(View.INVISIBLE);
		mDragEvent.shadowView.show();
	}

	public boolean handleMoveEvent(float x, float y) {
		if (mDragEvent != null) {
			mDragEvent.shadowView.move(x, y);

			DropContainer dropContainer = findDropContainer(x, y);
			return true;
		}
		return false;
	}

	private DropContainer findDropContainer(float x, float y) {
		for (DropContainer container : mDropContainers) {
			Rect r = container.getRectRelativeToDragLayout();
			if (r.contains((int) x, (int) y)) {
				return container;
			}
		}
		return null;
	}

	public boolean handleEndEvent() {
		if (mDragEvent != null) {
			mDragEvent.originView.setVisibility(View.VISIBLE);
			mDragEvent.shadowView.dismiss();
			mDragEvent = null;
			return true;
		}
		return false;
	}
}
