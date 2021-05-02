package com.dreamgyf.launcher.view.dock;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.dreamgyf.launcher.R;

public class DockView extends FrameLayout {

	public DockView(@NonNull Context context) {
		super(context);
		init(null);
	}

	public DockView(@NonNull Context context, @Nullable AttributeSet attrs) {
		super(context, attrs);
		init(attrs);
	}

	private void init(AttributeSet attrs) {
		LayoutInflater.from(getContext()).inflate(R.layout.view_dock, this, true);
	}

}
