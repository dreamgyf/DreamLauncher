package com.dreamgyf.launcher;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.dreamgyf.launcher.pm.LauncherAppManager;
import com.dreamgyf.launcher.touch.TouchController;
import com.dreamgyf.launcher.view.DragLayout;
import com.dreamgyf.launcher.view.main.MainViewManager;

public class Launcher extends AppCompatActivity {

	private LauncherAppManager mAppManager;

	private MainViewManager mMainViewManager;

	private DragLayout mDragLayout;

	private TouchController mTouchController;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_launcher);
		setupTheme();
		setupViews();

		mTouchController = TouchController.getInstance(this);
		mTouchController.attach(this);

		mAppManager = LauncherAppManager.getInstance(this);
		mMainViewManager = new MainViewManager(findViewById(R.id.main));

		mMainViewManager.render(mAppManager.getAppCells());

	}

	private void setupTheme() {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
			getWindow().setNavigationBarContrastEnforced(false);
		}
	}

	private void setupViews() {
		bindViews();
	}

	private void bindViews() {
		mDragLayout = findViewById(R.id.layout_drag);
	}

}