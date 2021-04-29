package com.dreamgyf.launcher.pm;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.LauncherActivityInfo;
import android.content.pm.LauncherApps;
import android.content.pm.PackageManager;
import android.os.UserHandle;
import android.os.UserManager;

import java.util.ArrayList;
import java.util.List;

public class LauncherAppLoader {

	private static LauncherAppLoader sInstance;

	public static LauncherAppLoader getInstance(Context context) {
		if (sInstance == null) {
			synchronized (LauncherAppLoader.class) {
				if (sInstance == null) {
					sInstance = new LauncherAppLoader(context);
				}
			}
		}
		return sInstance;
	}

	private final Context mAppContext;

	private final UserManager mUserManager;

	private final LauncherApps mLauncherApps;

	private final PackageManager mPackageManager;

	private LauncherAppLoader(Context context) {
		mAppContext = context.getApplicationContext();
		mUserManager = (UserManager) mAppContext.getSystemService(Context.USER_SERVICE);
		mLauncherApps = (LauncherApps) mAppContext.getSystemService(Context.LAUNCHER_APPS_SERVICE);
		mPackageManager = mAppContext.getPackageManager();
	}

	public List<App> obtainLauncherApps() {
		List<App> apps = new ArrayList<>();

		for (LauncherActivityInfo info : obtainLauncherActivityInfoList()) {
			App app = new App();
			app.info = new AppInfo();

			app.componentName = info.getComponentName().clone();
			app.info.packageName = app.componentName.getPackageName();
			app.info.icon = info.getIcon(0);
			try {
				app.info.name = getAppName(app.info.packageName);
			} catch (PackageManager.NameNotFoundException ignore) {
				app.info.name = app.info.packageName;
			}

			apps.add(app);
		}

		return apps;
	}

	public List<LauncherActivityInfo> obtainLauncherActivityInfoList() {
		List<LauncherActivityInfo> res = new ArrayList<>();

		List<UserHandle> userHandleList = mUserManager.getUserProfiles();
		for (UserHandle userHandle : userHandleList) {
			List<LauncherActivityInfo> apps = mLauncherApps.getActivityList(null, userHandle);
			res.addAll(apps);
		}

		return res;
	}

	private String getAppName(String packageName) throws PackageManager.NameNotFoundException {
		ApplicationInfo info = mPackageManager.getApplicationInfo(packageName, 0);
		return mPackageManager.getApplicationLabel(info).toString();
	}
}
