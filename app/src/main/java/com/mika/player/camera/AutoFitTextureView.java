package com.mika.player.camera;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.TextureView;

/**
 * @Author: mika
 * @Time: 2019/1/16 9:56 AM
 * @Description:
 */
public class AutoFitTextureView extends TextureView {

    private static final String Tag = AutoFitTextureView.class.getSimpleName();
    private int mRatioWidth;
    private int mRatioHeight;

    public AutoFitTextureView(Context context) {
        this(context, null);
    }

    public AutoFitTextureView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AutoFitTextureView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setAspectRatio(int width, int height) {
        this.mRatioWidth = width;
        this.mRatioHeight = height;
        requestLayout();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        Log.i(Tag, "auto fit view width: " + width + " ,height: " + height);
        if (mRatioWidth == 0 || mRatioHeight == 0) {
            setMeasuredDimension(width, height);
        } else {
            if (height > width * mRatioWidth / mRatioHeight) {
                //view高度大于预览高度
                setMeasuredDimension(height * mRatioWidth / mRatioHeight, height);
            } else {
                //view高度小于预览高度
                setMeasuredDimension(width, width * mRatioHeight / mRatioWidth);
            }
//            if (width < height * mRatioWidth / mRatioHeight) {
//                Log.i(Tag, "onMeasure, width: " + width + " ,height: " + width * mRatioHeight / mRatioWidth);
//                setMeasuredDimension(width, width * mRatioHeight / mRatioWidth);
//            } else {
//                Log.i(Tag, "onMeasure, width: " + height * mRatioWidth / mRatioHeight + " ,height: " + height);
//                setMeasuredDimension(height * mRatioWidth / mRatioHeight, height);
//            }
        }
    }
}
