package com.dreamgyf.launcher.util;

import android.view.View;

import androidx.viewpager.widget.ViewPager;

public class PositionUtil {

	public static int[] getChildLocationRelativeParent(View parent, View child) {
		int[] location = new int[2];
		View v = child;
		while (v != null && v != parent) {
			location[0] += v.getX();
			location[1] += v.getY();
			View p = (View) v.getParent();
			if (p instanceof ViewPager) {
				location[0] -= ((ViewPager) p).indexOfChild(v) * p.getWidth();
			}
			v = p;
		}
		return location;
	}
}
