package com.tuoren.window;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.view.MotionEvent;
import android.view.View;

/**
 * Create by JDT on 2021-03-02.
 */
public class SlideView extends View {

    private Paint paint;
    private Path path;
    private int width;
    private int height;
    private float downX;
    private float moveX;
    private float offsetX = 0.3f;
    private PointF stabelPointF1;
    private PointF stabelPointF2;
    private PointF movePointF;
    private PointF moveMaxPointF;
    private PointF moveMinPointF;

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
        stabelPointF1 = new PointF(0, 0);
        stabelPointF2 = new PointF(0, height);
        movePointF = new PointF(0, height/2.0f);
        moveMinPointF = new PointF(0, height/2.0f);
        moveMaxPointF = new PointF(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        path.reset();
        path.moveTo(stabelPointF1.x, stabelPointF1.y);
        path.quadTo(movePointF.x, movePointF.y, stabelPointF2.x, stabelPointF2.y);
        path.lineTo(stabelPointF1.x, stabelPointF1.y);
        canvas.drawPath(path, paint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                downX = event.getX();
                break;
            case MotionEvent.ACTION_MOVE:
                moveX = event.getX();
                float distX = moveX - downX;
                float finalX = distX * offsetX;
                if (finalX > 0 && finalX <= moveMaxPointF.x) {
                    changeMovePointByPercent(finalX * 1.0f / moveMaxPointF.x);
                }
                break;
            case MotionEvent.ACTION_UP:
                //做一个值动画
                ValueAnimator valueAnimator = new ValueAnimator();
                valueAnimator.setDuration(500);
                valueAnimator.setFloatValues(movePointF.x, moveMinPointF.x);
                valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        float animatedValue = (float) animation.getAnimatedValue();
                        movePointF.x = animatedValue;
                        invalidate();
                    }
                });
                valueAnimator.start();
                break;
            default:

        }
        return true;
    }

    private void changeMovePointByPercent(float percent) {
        movePointF.x = moveMaxPointF.x * percent;
        invalidate();
    }

}
