package com.dreamgyf.launcher.touch;

public class TouchRecord {
	public float lastDownX;
	public float lastDownY;
	public float lastMoveX;
	public float lastMoveY;
	public long lastDownTime;
	public boolean isStillDown;

	public void reset() {
		lastDownX = lastDownY = lastMoveX = lastMoveY = 0;
		lastDownTime = 0;
		isStillDown = false;
	}
}
