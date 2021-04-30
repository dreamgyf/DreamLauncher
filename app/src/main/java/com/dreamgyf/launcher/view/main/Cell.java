package com.dreamgyf.launcher.view.main;

import android.view.View;

public class Cell {

	private final int mRow;
	private final int mCol;
	private final int mRowSpan;
	private final int mColSpan;

	private final View mView;

	public Cell(View view, int row, int rowSpan, int col, int colSpan) {
		mView = view;
		mRow = row;
		mRowSpan = rowSpan;
		mCol = col;
		mColSpan = colSpan;
	}

	public int getRow() {
		return mRow;
	}

	public int getCol() {
		return mCol;
	}

	public int getRowSpan() {
		return mRowSpan;
	}

	public int getColSpan() {
		return mColSpan;
	}

	public View getView() {
		return mView;
	}
}
