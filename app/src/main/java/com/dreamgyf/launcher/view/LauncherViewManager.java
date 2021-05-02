package com.dreamgyf.launcher.view;

import com.dreamgyf.launcher.Launcher;
import com.dreamgyf.launcher.R;
import com.dreamgyf.launcher.view.DragLayout;
import com.dreamgyf.launcher.view.main.MainViewManager;

public class LauncherViewManager {

	private DragLayout mDragLayout;

	private MainViewManager mMainViewManager;

	public LauncherViewManager(Launcher launcher) {
		init(launcher);
	}

	private void init(Launcher launcher) {
		initMe(launcher);
		initMain();
	}

	private void initMe(Launcher launcher) {
		mDragLayout = launcher.findViewById(R.id.layout_drag);
	}

	private void initMain() {
		mMainViewManager = new MainViewManager(mDragLayout.findViewById(R.id.main));
	}

	private void initDock() {

	}
}
