package com.dreamgyf.launcher;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.dreamgyf.launcher.pm.LauncherAppManager;
import com.dreamgyf.launcher.view.main.MainViewManager;

public class Launcher extends AppCompatActivity {

	private LauncherAppManager mAppManager;

	private MainViewManager mMainViewManager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_launcher);

		mAppManager = LauncherAppManager.getInstance(this);
		mMainViewManager = new MainViewManager(findViewById(R.id.main));

		mMainViewManager.render(mAppManager.getAppCells());
	}

}