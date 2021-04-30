package com.dreamgyf.launcher.view.main;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.GridLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

import com.dreamgyf.launcher.R;
import com.dreamgyf.launcher.view.cell.Cell;

import java.util.List;

public class MainViewPager extends ViewPager {

	private MainViewPagerAdapter mAdapter;

	private int mGridItemRowCount;

	private int mGridItemColCount;

	public MainViewPager(@NonNull Context context) {
		super(context);
		init(null);
	}

	public MainViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
		super(context, attrs);
		init(attrs);
	}

	private void init(AttributeSet attrs) {
		setOverScrollMode(OVER_SCROLL_NEVER);
		mAdapter = new MainViewPagerAdapter(getContext());
		setAdapter(mAdapter);
	}

	public void setupGrid(int rowCount, int colCount) {
		mGridItemRowCount = rowCount;
		mGridItemColCount = colCount;
	}

	public void render(List<Cell> cells) {
		for (Cell cell : cells) {
			int page = cell.getPage();
			while (page > mAdapter.getCount() - 1) {
				mAdapter.insertNewView(MainViewPagerAdapter.INSERT_POS_END, mGridItemRowCount, mGridItemColCount);
			}

			View view = mAdapter.getItemView(page);
			GridLayout gridLayout = view.findViewById(R.id.layout_grid);

			GridLayout.Spec rowSpec = GridLayout.spec(cell.getRow(), cell.getRowSpan());
			GridLayout.Spec colSpec = GridLayout.spec(cell.getCol(), cell.getColSpan());
			GridLayout.LayoutParams lp = new GridLayout.LayoutParams(rowSpec, colSpec);

			gridLayout.addView(cell.getView(), lp);
		}
	}
}
