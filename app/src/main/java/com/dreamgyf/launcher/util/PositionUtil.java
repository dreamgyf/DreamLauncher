package com.dreamgyf.launcher.util;

import android.view.View;

import com.dreamgyf.launcher.view.DragLayout;

public class PositionUtil {

	public static int[] getChildLocationRelativeParent(View parent, View child) {
		int[] location = new int[2];
		View v = child;
		while (v != null && v != parent) {
			location[0] += v.getX();
			location[1] += v.getY();
			v = (View) v.getParent();
		}
		return location;
	}
}
