package com.dreamgyf.launcher.view.main;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.GridLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

import com.dreamgyf.launcher.R;
import com.dreamgyf.launcher.view.cell.Cell;

import java.util.List;

public class MainView extends FrameLayout {

	private MainViewPager mViewPager;

	public MainView(@NonNull Context context) {
		super(context);
		init(null);
	}

	public MainView(@NonNull Context context, @Nullable AttributeSet attrs) {
		super(context, attrs);
		init(attrs);
	}

	private void init(AttributeSet attrs) {
		LayoutInflater.from(getContext()).inflate(R.layout.view_main, this, true);
		mViewPager = findViewById(R.id.viewpager);
	}

	public void setupGrid(int rowCount, int colCount) {
		mViewPager.setupGrid(rowCount, colCount);
	}

	public void render(List<Cell> cells) {
		mViewPager.render(cells);
	}
}
