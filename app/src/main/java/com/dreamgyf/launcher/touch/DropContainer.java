package com.dreamgyf.launcher.touch;

import android.graphics.Rect;

public interface DropContainer {
	Rect getRectRelativeToDragLayout();

	void onDragIn(DragEvent dragEvent);

	void onDragOut(DragEvent dragEvent);

	void onDragOver(DragEvent dragEvent);
}
