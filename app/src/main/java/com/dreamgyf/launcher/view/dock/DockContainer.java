package com.dreamgyf.launcher.view.dock;

import android.app.Activity;
import android.app.WallpaperManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class DockContainer extends FrameLayout {

	public DockContainer(@NonNull Context context) {
		super(context);
		init(null);
	}

	public DockContainer(@NonNull Context context, @Nullable AttributeSet attrs) {
		super(context, attrs);
		init(attrs);
	}

	private void init(AttributeSet attrs) {
		GradientDrawable gd = new GradientDrawable();
		gd.setColor(Color.WHITE);
		gd.setCornerRadius(75);
		setBackground(gd);
		setAlpha(0.6f);
		setPadding(10, 10, 10, 10);
	}

}
