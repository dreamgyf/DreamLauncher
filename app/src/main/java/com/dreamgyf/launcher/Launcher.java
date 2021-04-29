package com.dreamgyf.launcher;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.dreamgyf.launcher.pm.LauncherAppManager;

public class Launcher extends AppCompatActivity {

	private LauncherAppManager mAppManager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_launcher);

		mAppManager = LauncherAppManager.getInstance(this);

		initView();
	}

	private void initView() {
		bindView();
	}

	private void bindView() {
	}
}