package com.dreamgyf.launcher.view.main;

import android.graphics.Rect;

import com.dreamgyf.launcher.config.LayoutConfig;
import com.dreamgyf.launcher.touch.DropContainer;
import com.dreamgyf.launcher.touch.TouchController;
import com.dreamgyf.launcher.util.PositionUtil;
import com.dreamgyf.launcher.view.DragLayout;
import com.dreamgyf.launcher.view.cell.Cell;

import java.util.List;

public class MainViewManager implements DropContainer {

	private final DragLayout mDragLayout;

	private final MainView mMainView;

	private int mRowCount;

	private int mColCount;

	public MainViewManager(DragLayout dragLayout, MainView mainView) {
		mDragLayout = dragLayout;
		mMainView = mainView;
		setupLayout();

		TouchController.getInstance(mMainView.getContext()).getDragController().addDropContainer(this);
	}

	private void setupLayout() {
		mRowCount = LayoutConfig.getRows();
		mColCount = LayoutConfig.getCols();

		mMainView.setupGrid(mRowCount, mColCount);
	}

	public void render(List<Cell> cells) {
		mMainView.render(cells);
	}

	@Override
	public Rect getRectRelativeToDragLayout() {
		Rect rect = new Rect();
		int[] location = PositionUtil.getChildLocationRelativeParent(mDragLayout, mMainView);
		rect.left = location[0];
		rect.right = rect.left + mMainView.getWidth();
		rect.top = location[1];
		rect.bottom = rect.top + mMainView.getHeight();
		return rect;
	}
}
