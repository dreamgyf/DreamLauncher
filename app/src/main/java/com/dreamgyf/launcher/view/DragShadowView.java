package com.dreamgyf.launcher.view;

import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;

import com.dreamgyf.launcher.util.PositionUtil;

@SuppressLint("ViewConstructor")
public class DragShadowView extends View {

	private final static float SCALE = 1.1f;
	private final static int DURATION = 400;

	private final Bitmap mBitmap;

	private final View mRefView;

	private final float mLastTouchX;

	private final float mLastTouchY;

	private final Paint mPaint;

	private DragLayout mDragLayout;

	public DragShadowView(Context context, View referenceView, float lastTouchX, float lastTouchY) {
		super(context);
		mRefView = referenceView;
		mLastTouchX = lastTouchX;
		mLastTouchY = lastTouchY;
		mPaint = new Paint(Paint.FILTER_BITMAP_FLAG);

		Bitmap bitmap = Bitmap.createBitmap(mRefView.getWidth(), mRefView.getHeight(), Bitmap.Config.ARGB_8888);
		Canvas canvas = new Canvas(bitmap);
		mRefView.draw(canvas);
		mBitmap = bitmap;
	}

	public void bindDragLayout(DragLayout dragLayout) {
		mDragLayout = dragLayout;
	}

	public void show() {
		mDragLayout.addView(this);
		int[] location = PositionUtil.getChildLocationRelativeParent(mDragLayout, mRefView);
		setX(location[0]);
		setY(location[1]);
		setPivotX((float) mRefView.getWidth() / 2);
		setPivotY((float) mRefView.getHeight() / 2);
		ValueAnimator anim = ValueAnimator.ofFloat(1, SCALE);
		anim.setDuration(DURATION);
		anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
			@Override
			public void onAnimationUpdate(ValueAnimator animation) {
				float scale = (float) animation.getAnimatedValue();
				setScaleX(scale);
				setScaleY(scale);
				if (!isAttachedToWindow()) {
					anim.cancel();
				}
			}
		});
		anim.start();
	}

	@Override
	protected void onDraw(Canvas canvas) {
		canvas.drawBitmap(mBitmap, 0.0f, 0.0f, mPaint);
	}

	public void dismiss() {
		mDragLayout.removeView(this);
	}

	public void move(float x, float y) {
		setTranslationX(x - mLastTouchX);
		setTranslationY(y - mLastTouchY);
	}
}
