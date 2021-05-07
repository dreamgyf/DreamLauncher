package com.dreamgyf.launcher.view.main;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;

import androidx.annotation.IntDef;
import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.dreamgyf.launcher.R;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.List;

public class MainViewPagerAdapter extends PagerAdapter {

	@Retention(RetentionPolicy.SOURCE)
	@Target(ElementType.PARAMETER)
	@IntDef({INSERT_POS_START, INSERT_POS_END})
	public @interface InsertPlace {
	}

	public final static int INSERT_POS_START = 0;
	public final static int INSERT_POS_END = 1;

	private final Context mContext;

	private final List<View> mViewList = new ArrayList<>();

	public MainViewPagerAdapter(Context context) {
		mContext = context;
	}

	@Override
	public int getCount() {
		return mViewList.size();
	}

	@Override
	public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
		return view == object;
	}

	@NonNull
	@Override
	public Object instantiateItem(@NonNull ViewGroup container, int position) {
		View view = mViewList.get(position);
		container.addView(view);
		return view;
	}

	@Override
	public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
		container.removeView((View) object);
	}

	@Override
	public int getItemPosition(@NonNull Object object) {
		return POSITION_NONE;
	}

	@SuppressLint("InflateParams")
	public void insertNewView(@InsertPlace int place, int rowCount, int colCount) {
		View view = LayoutInflater.from(mContext).inflate(R.layout.viewpager_item_main, null, false);
		GridLayout gridLayout = view.findViewById(R.id.layout_grid);
		gridLayout.setRowCount(rowCount);
		gridLayout.setColumnCount(colCount);

		if (place == INSERT_POS_START) {
			mViewList.add(0, view);
		} else if (place == INSERT_POS_END) {
			mViewList.add(view);
		}
		notifyDataSetChanged();
	}

	public View getItemView(int page) {
		return mViewList.get(page);
	}
}
