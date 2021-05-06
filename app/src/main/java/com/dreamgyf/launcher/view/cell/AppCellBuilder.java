package com.dreamgyf.launcher.view.cell;

import android.annotation.SuppressLint;
import android.content.Context;

import com.dreamgyf.launcher.touch.TouchController;
import com.dreamgyf.launcher.pm.App;

public class AppCellBuilder {

	@SuppressLint("InflateParams")
	public static AppCell build(Context context, App app) {
		AppCellView view = new AppCellView(context);
		view.setIcon(app.info.icon);
		view.setName(app.info.name);
		view.setTag(app);
		TouchController.getInstance(context).bind(view);
		return new AppCell(view, app.layout.page, app.layout.row, app.layout.col);
	}
}
