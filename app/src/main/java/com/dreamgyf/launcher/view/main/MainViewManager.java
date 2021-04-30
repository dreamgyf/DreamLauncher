package com.dreamgyf.launcher.view.main;

import com.dreamgyf.launcher.config.LayoutConfig;
import com.dreamgyf.launcher.view.cell.Cell;

import java.util.List;

public class MainViewManager {

	private final MainView mMainView;

	private int mRowCount;

	private int mColCount;

	public MainViewManager(MainView mainView) {
		mMainView = mainView;
		setupLayout();
	}

	private void setupLayout() {
		mRowCount = LayoutConfig.getRows();
		mColCount = LayoutConfig.getCols();

		mMainView.setupGrid(mRowCount, mColCount);
	}

	public void render(List<Cell> cells) {
		mMainView.render(cells);
	}
}