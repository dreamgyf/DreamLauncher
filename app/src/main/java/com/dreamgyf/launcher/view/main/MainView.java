package com.dreamgyf.launcher.view.main;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.GridLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.dreamgyf.launcher.R;

import java.util.List;

public class MainView extends FrameLayout {

	private GridLayout mGridLayout;

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
		mGridLayout = findViewById(R.id.layout_grid);
	}

	public void setupGrid(int rowCount, int colCount) {
		mGridLayout.setRowCount(rowCount);
		mGridLayout.setColumnCount(colCount);
	}

	public void render(List<Cell> cells) {
		for (Cell cell : cells) {
			GridLayout.Spec rowSpec = GridLayout.spec(cell.getRow(), cell.getRowSpan());
			GridLayout.Spec colSpec = GridLayout.spec(cell.getCol(), cell.getColSpan());
			GridLayout.LayoutParams lp = new GridLayout.LayoutParams(rowSpec, colSpec);

			mGridLayout.addView(cell.getView(), lp);
		}
	}
}
