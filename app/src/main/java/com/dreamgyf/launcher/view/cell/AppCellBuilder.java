package com.dreamgyf.launcher.view.cell;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.dreamgyf.launcher.R;
import com.dreamgyf.launcher.pm.App;

public class AppCellBuilder {

	@SuppressLint("InflateParams")
	public static AppCell build(Context context, App app) {
		View view = LayoutInflater.from(context).inflate(R.layout.view_cell_app, null, false);
		ImageView iv = view.findViewById(R.id.iv_icon);
		TextView tv = view.findViewById(R.id.tv_name);
		iv.setImageDrawable(app.info.icon);
		tv.setText(app.info.name);
		view.setOnClickListener((v) -> {
			Intent intent = new Intent();
			intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			intent.setComponent(app.componentName);
			context.startActivity(intent);
		});
		return new AppCell(view, app.layout.page, app.layout.row, app.layout.col);
	}
}
