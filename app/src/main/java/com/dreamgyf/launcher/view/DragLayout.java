package com.dreamgyf.launcher.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

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
}
