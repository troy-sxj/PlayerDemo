package com.mika.player;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.SurfaceTexture;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.TextureView;

import java.lang.ref.WeakReference;

/**
 * @Author: mika
 * @Time: 2019/1/8 3:37 PM
 * @Description:
 */
public class DrawTextureView extends TextureView implements TextureView.SurfaceTextureListener {

    private static long DELAY = 1000;
    private DrawHandler mHandler = new DrawHandler(getContext());

    private Surface mSurface;
    private Paint mPaint;
    private int mCount;

    public DrawTextureView(Context context) {
        this(context, null);
    }

    public DrawTextureView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DrawTextureView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        mPaint = new Paint();
        mPaint.setTextAlign(Paint.Align.CENTER);
        mPaint.setTextSize(50);
//        mPaint.setStyle(Paint.Style.STROKE);
//        mPaint.setStrokeWidth(10);
        mPaint.setColor(Color.RED);
        this.setSurfaceTextureListener(this);
    }

    @Override
    public void onSurfaceTextureAvailable(SurfaceTexture surface, int width, int height) {
        mSurface = new Surface(surface);
        mHandler.sendEmptyMessageDelayed(0x1, DELAY);
    }

    @Override
    public void onSurfaceTextureSizeChanged(SurfaceTexture surface, int width, int height) {

    }

    @Override
    public boolean onSurfaceTextureDestroyed(SurfaceTexture surface) {
        mHandler.removeCallbacksAndMessages(null);
        mSurface = null;
        return true;
    }

    @Override
    public void onSurfaceTextureUpdated(SurfaceTexture surface) {

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
                Canvas canvas = mSurface.lockCanvas(null);
                canvas.drawColor(Color.WHITE);
                canvas.drawText("Hello world " + mCount, getWidth() >> 1, getHeight() >> 1, mPaint);
                mSurface.unlockCanvasAndPost(canvas);
                Log.e("aaa", "draw text --- " + mCount);
                sendEmptyMessageDelayed(0x1, DELAY);
            }
        }
    }
}
