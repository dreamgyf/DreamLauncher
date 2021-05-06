package com.dreamgyf.launcher.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.dreamgyf.launcher.touch.TouchController;
import com.dreamgyf.launcher.view.cell.AppCellView;

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

//	@Override
//	public boolean onTouchEvent(MotionEvent event) {
//		switch (event.getAction()) {
//			case MotionEvent.ACTION_MOVE:
//				TouchController.getInstance(getContext()).getDragController().handleMoveEvent(event.getX(), event.getY());
//				break;
//			case MotionEvent.ACTION_UP:
//				TouchController.getInstance(getContext()).getDragController().handleMoveEvent(event.getX(), event.getY());
//				TouchController.getInstance(getContext()).getDragController().handleEndEvent();
//				break;
//
//		}
//		return true;
//	}

	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
		switch (ev.getAction()) {
			case MotionEvent.ACTION_MOVE:
				TouchController.getInstance(getContext()).getDragController().handleMoveEvent(ev.getX(), ev.getY());
				break;
			case MotionEvent.ACTION_UP:
				TouchController.getInstance(getContext()).getDragController().handleMoveEvent(ev.getX(), ev.getY());
				TouchController.getInstance(getContext()).getDragController().handleEndEvent();
				break;

		}
		return super.dispatchTouchEvent(ev);
	}
}
