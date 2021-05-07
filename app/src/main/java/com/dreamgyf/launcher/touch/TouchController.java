package com.dreamgyf.launcher.touch;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.view.MotionEvent;
import android.view.View;

import com.dreamgyf.launcher.Launcher;
import com.dreamgyf.launcher.R;
import com.dreamgyf.launcher.pm.App;
import com.dreamgyf.launcher.view.cell.AppCellView;

import java.util.WeakHashMap;

import static android.view.MotionEvent.*;

public class TouchController implements View.OnTouchListener {

	private static TouchController sInstance;

	public static TouchController getInstance(Context context) {
		if (sInstance == null) {
			synchronized (TouchController.class) {
				if (sInstance == null) {
					sInstance = new TouchController(context);
				}
			}
		}
		return sInstance;
	}

	/**
	 * 手指触摸点击或长按的误差距离
	 * 在这个距离内仍可以触发点击或长按
	 */
	private final static int TOUCH_EFFECTIVE_RANGE = 20;
	private final static int CLICK_WAIT_TIME = 250;
	private final static int LONG_CLICK_TRIGGER_TIME = 500;

	private final Context mContext;

	private final DragController mDragController;

	private final Handler mHandler = new Handler(Looper.getMainLooper());

	private final WeakHashMap<View, TouchRecord> mViewTouchRecordMap;

	private Launcher mLauncher;

	private TouchController(Context context) {
		mContext = context;
		mDragController = new DragController(context);
		mViewTouchRecordMap = new WeakHashMap<>();
	}

	public void attach(Launcher launcher) {
		mLauncher = launcher;
		mDragController.attach(launcher.findViewById(R.id.layout_drag));
	}

	public void bind(View view) {
		if (!mViewTouchRecordMap.containsKey(view)) {
			mViewTouchRecordMap.put(view, new TouchRecord());
			view.setOnTouchListener(this);
		}
	}

	public void unbind(View view) {
		if (mViewTouchRecordMap.remove(view) != null) {
			view.setOnTouchListener(null);
		}
	}

	public DragController getDragController() {
		return mDragController;
	}

	public void onClick(View v) {
		if (v instanceof AppCellView) {
			App app = (App) v.getTag();
			if (app != null) {
				Intent intent = new Intent();
				intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				intent.setComponent(app.componentName);
				mContext.startActivity(intent);
			}
		}
	}

	public void onLongClick(View v, float lastMoveX, float lastMoveY) {
		if (v instanceof AppCellView) {
			DragItemInfo dragItemInfo = new DragItemInfo();
			dragItemInfo.view = v;
			dragItemInfo.lastMoveX = lastMoveX;
			dragItemInfo.lastMoveY = lastMoveY;
			mDragController.startDrag(dragItemInfo);
		}
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		TouchRecord r = mViewTouchRecordMap.get(v);
		if (r == null) {
			return false;
		}

		float x = event.getX();
		float y = event.getY();

		switch (event.getAction()) {
			case ACTION_DOWN:
				r.lastDownX = x;
				r.lastDownY = y;
				r.lastMoveX = x;
				r.lastMoveY = y;
				r.lastDownTime = System.currentTimeMillis();
				r.isStillDown = true;
				mHandler.postDelayed(() -> {
					if (r.isStillDown) {
						onLongClick(v, r.lastMoveX, r.lastMoveY);
					}
				}, LONG_CLICK_TRIGGER_TIME);
				break;
			case ACTION_MOVE:
				if (r.isStillDown) {
					if (isInEffectiveRange(r.lastDownX, r.lastDownY, x, y)) {
						r.lastMoveX = x;
						r.lastMoveY = y;
					} else {
						r.reset();
					}
				}
				break;
			case ACTION_UP:
				if (r.isStillDown
						&& System.currentTimeMillis() - r.lastDownTime < CLICK_WAIT_TIME
						&& isInEffectiveRange(r.lastDownX, r.lastDownY, x, y)) {
					r.reset();
					onClick(v);
				}
				break;
			case ACTION_CANCEL:
				r.reset();
				break;
		}
		return true;
	}

	private boolean isInEffectiveRange(float lastX, float lastY, float nowX, float nowY) {
		return Math.abs(lastX - nowX) < TOUCH_EFFECTIVE_RANGE && Math.abs(lastY - nowY) < TOUCH_EFFECTIVE_RANGE;
	}
}
