package com.tuoren.window;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.View;

/**
 * Create by JDT on 2021-03-02.
 */
public class SlideView extends View {

    private Paint paint;
    private Path path;
    private int width;
    private int height;

    public SlideView(Context context) {
        super(context);
        init();
    }

    private void init() {
        paint = new Paint();
        paint.setColor(getResources().getColor(R.color.design_default_color_error));
        path = new Path();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        width = getWidth();
        height = getHeight();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        path.moveTo(0, 0);
        path.quadTo(width, height/2, 0, height);
        path.lineTo(0, 0);
        canvas.drawPath(path, paint);
    }
}
