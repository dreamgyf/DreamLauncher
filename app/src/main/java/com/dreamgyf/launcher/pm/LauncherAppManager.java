package com.dreamgyf.launcher.pm;

import android.content.Context;
import android.util.Log;

import java.util.List;

public class LauncherAppManager {

	private static LauncherAppManager sInstance;

	public static LauncherAppManager getInstance(Context context) {
		if (sInstance == null) {
			synchronized (LauncherAppManager.class) {
				if (sInstance == null) {
					sInstance = new LauncherAppManager(context);
				}
			}
		}
		return sInstance;
	}

	private final Context mAppContext;

	private final LauncherAppLoader mLauncherAppLoader;

	private LauncherAppManager(Context context) {
		mAppContext = context.getApplicationContext();
		mLauncherAppLoader = LauncherAppLoader.getInstance(mAppContext);
	}

}
