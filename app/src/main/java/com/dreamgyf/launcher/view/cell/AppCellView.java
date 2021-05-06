package com.dreamgyf.launcher.view.cell;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.dreamgyf.launcher.R;

public class AppCellView extends FrameLayout {

	private ImageView mIvIcon;

	private TextView mTvName;

	public AppCellView(@NonNull Context context) {
		super(context);
		init(null);
	}

	public AppCellView(@NonNull Context context, @Nullable AttributeSet attrs) {
		super(context, attrs);
		init(attrs);
	}

	private void init(AttributeSet attrs) {
		LayoutInflater.from(getContext()).inflate(R.layout.view_cell_app, this, true);

		mIvIcon = findViewById(R.id.iv_icon);
		mTvName = findViewById(R.id.tv_name);
	}

	public void setIcon(Drawable drawable) {
		mIvIcon.setImageDrawable(drawable);
	}

	public void setName(String name) {
		mTvName.setText(name);
	}
}
