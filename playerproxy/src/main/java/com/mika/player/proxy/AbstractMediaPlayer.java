package com.mika.player.proxy;

/**
 * @Author: mika
 * @Time: 2019/1/18 5:14 PM
 * @Description:
 */
public abstract class AbstractMediaPlayer implements IMediaPlayer {

    private OnPreparedListener onPreparedListener;
    private OnCompletedListener onCompletedListener;
    private OnBufferingUpdateListener onBufferingUpdateListener;
    private OnSeekCompletedListener onSeekCompletedListener;
    private OnInfoListener onInfoListener;
    private OnVideoSizeChangedListener onVideoSizeChangedListener;
    private OnErrorListener onErrorListener;

    @Override
    public final void setOnPreparedListener(OnPreparedListener onPreparedListener) {
        this.onPreparedListener = onPreparedListener;
    }

    @Override
    public final void setOnCompletedListener(OnCompletedListener onCompletedListener) {
        this.onCompletedListener = onCompletedListener;
    }

    @Override
    public final void setOnBufferingUpdateListener(OnBufferingUpdateListener onBufferingUpdateListener) {
        this.onBufferingUpdateListener = onBufferingUpdateListener;
    }

    @Override
    public final void setOnErrorListener(OnErrorListener onErrorListener) {
        this.onErrorListener = onErrorListener;
    }

    @Override
    public final void setOnInfoListener(OnInfoListener onInfoListener) {
        this.onInfoListener = onInfoListener;
    }

    @Override
    public void setOnSeekCompletedListener(OnSeekCompletedListener onSeekCompletedListener) {
        this.onSeekCompletedListener = onSeekCompletedListener;
    }

    @Override
    public final void setOnVideoSizeChangedListener(OnVideoSizeChangedListener onVideoSizeChangedListener) {
        this.onVideoSizeChangedListener = onVideoSizeChangedListener;
    }

    public final void resetListener() {
        this.onBufferingUpdateListener = null;
        this.onCompletedListener = null;
        this.onErrorListener = null;
        this.onInfoListener = null;
        this.onPreparedListener = null;
        this.onSeekCompletedListener = null;
        this.onVideoSizeChangedListener = null;
    }

    protected final void notifyOnPrepared() {
        if (onPreparedListener != null) {
            onPreparedListener.onPrepared(this);
        }
    }

    protected final void notifyOnCompleted() {
        if (onCompletedListener != null) {
            onCompletedListener.onCompleted(this);
        }
    }

    protected final void notifySeekCompletedListener() {
        if (onSeekCompletedListener != null) {
            onSeekCompletedListener.onSeekCompleted(this);
        }
    }

    protected final boolean notifyOnInfoListener(int what, int extra) {
        if (onInfoListener != null) {
            return onInfoListener.onInfo(this, what, extra);
        }
        return false;
    }

    protected final boolean notifyOnError(int what, int extra) {
        if (onErrorListener != null) {
            return onErrorListener.onError(this, what, extra);
        }
        return false;
    }

    protected final void notifyOnVideoSizeChanged(int width, int height, int sraNum, int sarDen) {
        if (onVideoSizeChangedListener != null) {
            onVideoSizeChangedListener.onVideoSizeChanged(this, width, height, sraNum, sarDen);
        }
    }

    protected final void notifyOnBufferingUpdate(int percent) {
        if (onBufferingUpdateListener != null) {
            onBufferingUpdateListener.onBufferingUpdate(this, percent);
        }
    }
}
