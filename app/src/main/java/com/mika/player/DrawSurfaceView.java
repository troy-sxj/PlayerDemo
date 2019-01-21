package com.mika.player;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.lang.ref.WeakReference;


/**
 * @Author: mika
 * @Time: 2019/1/8 3:07 PM
 * @Description:
 */
public class DrawSurfaceView extends SurfaceView implements SurfaceHolder.Callback {

    private static long DELAY = 1000;
    private DrawHandler mHandler = new DrawHandler(getContext());

    private SurfaceHolder mHolder;
    private Paint mPaint;
    private int mCount;

    public DrawSurfaceView(Context context) {
        this(context, null);
    }

    public DrawSurfaceView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DrawSurfaceView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        mHolder = getHolder();
        mHolder.addCallback(this);

        mPaint = new Paint();
        mPaint.setTextAlign(Paint.Align.CENTER);
        mPaint.setTextSize(50);
//        mPaint.setStyle(Paint.Style.STROKE);
//        mPaint.setStrokeWidth(10);

        mPaint.setColor(Color.RED);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        if(mHandler != null) {
            mHandler.sendEmptyMessageDelayed(0x1, DELAY);
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        mHandler = null;
    }

    class DrawHandler extends Handler {

        private WeakReference<Context> weakReference;

        public DrawHandler(Context context) {
            weakReference = new WeakReference<>(context);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(mHandler != null) {
                mCount++;
                Canvas canvas = mHolder.lockCanvas();
                canvas.drawColor(Color.WHITE);
                canvas.drawText("Hello world " + mCount, getWidth() >> 1, getHeight() >> 1, mPaint);
                mHolder.unlockCanvasAndPost(canvas);
                Log.e("aaa", "draw text --- " + mCount);
                sendEmptyMessageDelayed(0x1, DELAY);
            }
        }
    }
}
