package com.dreamgyf.launcher.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.dreamgyf.launcher.touch.DragController;
import com.dreamgyf.launcher.touch.TouchController;

/**
 * 预留，未实现
 */
public class DragLayout extends FrameLayout {

	public DragLayout(@NonNull Context context) {
		super(context);
		init(null);
	}

	public DragLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
		super(context, attrs);
		init(attrs);
	}

	private void init(AttributeSet attrs) {
	}

	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
		boolean handled = false;

		DragController dc = TouchController.getInstance(getContext()).getDragController();
		switch (ev.getAction()) {
			case MotionEvent.ACTION_MOVE:
				handled = dc.handleMoveEvent(ev.getX(), ev.getY());
				break;
			case MotionEvent.ACTION_UP:
				handled = dc.handleMoveEvent(ev.getX(), ev.getY()) && dc.handleEndEvent();
				break;
		}
		return handled || super.dispatchTouchEvent(ev);
	}
}
