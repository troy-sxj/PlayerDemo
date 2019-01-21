package com.mika.player.android;

import android.content.Context;
import android.media.AudioAttributes;
import android.media.MediaPlayer;
import android.net.Uri;
import android.text.TextUtils;
import android.view.Surface;
import android.view.SurfaceHolder;

import com.mika.player.proxy.AbstractMediaPlayer;

import java.io.FileDescriptor;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.Map;

/**
 * @Author: mika
 * @Time: 2019/1/18 5:31 PM
 * @Description:
 */
public class AndroidPlayer extends AbstractMediaPlayer {

    private final Object mInitLock = new Object();
    private MediaPlayer mInternalMediaPlayer;
    private AndroidMediaPlayerListenerHolder mAndroidMediaPlayerListenerHolder;

    private boolean mIsReleased;
    private String mDataSource;

    public AndroidPlayer() {
        synchronized (mInitLock) {
            mInternalMediaPlayer = new MediaPlayer();
        }
        AudioAttributes.Builder builder = new AudioAttributes.Builder();
        builder.setContentType(AudioAttributes.CONTENT_TYPE_MUSIC);
        mInternalMediaPlayer.setAudioAttributes(builder.build());
        mAndroidMediaPlayerListenerHolder = new AndroidMediaPlayerListenerHolder(this);
        attachInternalListener();
    }

    @Override
    public void setDisplay(SurfaceHolder surfaceHolder) {
        synchronized (mInitLock) {
            if (!mIsReleased) {
                mInternalMediaPlayer.setDisplay(surfaceHolder);
            }
        }
    }

    @Override
    public void setSurface(Surface surface) {
        mInternalMediaPlayer.setSurface(surface);
    }

    @Override
    public void setDataSource(Context context, Uri uri) throws IOException, IllegalArgumentException, SecurityException, IllegalStateException {
        mInternalMediaPlayer.setDataSource(context, uri);
    }

    @Override
    public void setDataSource(Context context, Uri uri, Map<String, String> headers) throws IOException, IllegalArgumentException, SecurityException, IllegalStateException {
        mInternalMediaPlayer.setDataSource(context, uri, headers);
    }

    @Override
    public void setDataSource(FileDescriptor fd) throws IOException, IllegalArgumentException, IllegalStateException {
        mInternalMediaPlayer.setDataSource(fd);
    }

    @Override
    public void setDataSource(String path) throws IOException, IllegalArgumentException, SecurityException, IllegalStateException {
        mDataSource = path;
        Uri uri = Uri.parse(path);
        String scheme = uri.getScheme();
        if (!TextUtils.isEmpty(scheme) && scheme.equalsIgnoreCase("file")) {
            mInternalMediaPlayer.setDataSource(uri.getPath());
        } else {
            mInternalMediaPlayer.setDataSource(path);
        }
    }

    @Override
    public String getDataSource() {
        return mDataSource;
    }

    @Override
    public void prepareAsync() throws IllegalAccessException {
        mInternalMediaPlayer.prepareAsync();
    }

    @Override
    public void start() {
        mInternalMediaPlayer.start();
    }

    @Override
    public void stop() {
        mInternalMediaPlayer.stop();
    }

    @Override
    public void pause() {
        mInternalMediaPlayer.pause();
    }

    @Override
    public boolean isPlaying() {
        return mInternalMediaPlayer.isPlaying();
    }

    @Override
    public void setScreenOnPlaying(boolean enable) {

    }

    @Override
    public void seekTo(long milliSecond) {
        mInternalMediaPlayer.seekTo((int) milliSecond);
    }

    @Override
    public void setVolume(int leftVolume, int rightVolume) {
        mInternalMediaPlayer.setVolume(leftVolume, rightVolume);
    }

    @Override
    public void release() {
        mIsReleased = true;
        mInternalMediaPlayer.release();
        resetListener();
    }

    @Override
    public void reset() {
        mInternalMediaPlayer.reset();
        resetListener();
        attachInternalListener();
    }

    @Override
    public int getVideoWidth() {
        return mInternalMediaPlayer.getVideoWidth();
    }

    @Override
    public int getVideoHeight() {
        return mInternalMediaPlayer.getVideoHeight();
    }

    @Override
    public long getCurrentPos() {
        return mInternalMediaPlayer.getCurrentPosition();
    }

    @Override
    public long getDuration() {
        return mInternalMediaPlayer.getDuration();
    }

    private void attachInternalListener() {
        if (mAndroidMediaPlayerListenerHolder == null || mInternalMediaPlayer == null) return;
        mInternalMediaPlayer.setOnBufferingUpdateListener(mAndroidMediaPlayerListenerHolder);
        mInternalMediaPlayer.setOnCompletionListener(mAndroidMediaPlayerListenerHolder);
        mInternalMediaPlayer.setOnErrorListener(mAndroidMediaPlayerListenerHolder);
        mInternalMediaPlayer.setOnInfoListener(mAndroidMediaPlayerListenerHolder);
        mInternalMediaPlayer.setOnPreparedListener(mAndroidMediaPlayerListenerHolder);
        mInternalMediaPlayer.setOnVideoSizeChangedListener(mAndroidMediaPlayerListenerHolder);
        mInternalMediaPlayer.setOnSeekCompleteListener(mAndroidMediaPlayerListenerHolder);
    }

    private class AndroidMediaPlayerListenerHolder implements
            MediaPlayer.OnPreparedListener,
            MediaPlayer.OnCompletionListener,
            MediaPlayer.OnErrorListener,
            MediaPlayer.OnInfoListener,
            MediaPlayer.OnSeekCompleteListener,
            MediaPlayer.OnVideoSizeChangedListener,
            MediaPlayer.OnBufferingUpdateListener {

        public final WeakReference<AndroidPlayer> androidPlayerWeakRef;

        public AndroidMediaPlayerListenerHolder(AndroidPlayer androidPlayer) {
            androidPlayerWeakRef = new WeakReference<>(androidPlayer);
        }

        @Override
        public void onPrepared(MediaPlayer mp) {
            AndroidPlayer self = androidPlayerWeakRef.get();
            if (self != null) {
                notifyOnPrepared();
            }
        }

        @Override
        public void onBufferingUpdate(MediaPlayer mp, int percent) {
            AndroidPlayer self = androidPlayerWeakRef.get();
            if (self != null) {
                notifyOnBufferingUpdate(percent);
            }
        }

        @Override
        public void onCompletion(MediaPlayer mp) {
            AndroidPlayer self = androidPlayerWeakRef.get();
            if (self != null) {
                notifyOnCompleted();
            }
        }

        @Override
        public boolean onError(MediaPlayer mp, int what, int extra) {
            AndroidPlayer self = androidPlayerWeakRef.get();
            return (self != null) && notifyOnError(what, extra);
        }

        @Override
        public boolean onInfo(MediaPlayer mp, int what, int extra) {
            AndroidPlayer self = androidPlayerWeakRef.get();
            return (self != null) && notifyOnInfoListener(what, extra);
        }

        @Override
        public void onSeekComplete(MediaPlayer mp) {
            AndroidPlayer self = androidPlayerWeakRef.get();
            if (self != null) {
                notifySeekCompletedListener();
            }
        }

        @Override
        public void onVideoSizeChanged(MediaPlayer mp, int width, int height) {
            AndroidPlayer self = androidPlayerWeakRef.get();
            if (self != null) {
                notifyOnVideoSizeChanged(width, height, 1, 1);
            }
        }
    }
}
