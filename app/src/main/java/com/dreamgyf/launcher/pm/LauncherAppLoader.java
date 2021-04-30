package com.dreamgyf.launcher.pm;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.LauncherActivityInfo;
import android.content.pm.LauncherApps;
import android.content.pm.PackageManager;
import android.os.UserHandle;
import android.os.UserManager;

import com.dreamgyf.launcher.config.LayoutConfig;

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

		int page = 0;
		int row = 0;
		int col = 0;
		int rowCount = LayoutConfig.getRows();
		int colCount = LayoutConfig.getCols();

		for (LauncherActivityInfo info : obtainLauncherActivityInfoList()) {
			App app = new App();
			app.info = new AppInfo();
			app.layout = new AppLayout();

			app.componentName = info.getComponentName().clone();
			app.info.packageName = app.componentName.getPackageName();
			app.info.icon = info.getIcon(0);
			try {
				app.info.name = getAppName(app.info.packageName);
			} catch (PackageManager.NameNotFoundException ignore) {
				app.info.name = app.info.packageName;
			}

			//指定位置
			app.layout.page = page;
			app.layout.row = row;
			app.layout.col = col++;

			//计算下一个app的位置
			if (col > colCount - 1) {
				++row;
				col = 0;
			}
			if (row > rowCount - 1) {
				++page;
				row = 0;
				col = 0;
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
