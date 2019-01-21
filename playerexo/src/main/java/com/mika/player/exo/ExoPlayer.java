package com.mika.player.exo;

import android.content.Context;
import android.net.Uri;
import android.view.Surface;
import android.view.SurfaceHolder;

import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.mika.player.proxy.AbstractMediaPlayer;

import java.io.FileDescriptor;
import java.io.IOException;
import java.util.Map;

/**
 * @Author: mika
 * @Time: 2019/1/21 9:45 AM
 * @Description:
 */
public class ExoPlayer extends AbstractMediaPlayer {

    private Context mApplicationContext;

    public ExoPlayer(Context context) {
        mApplicationContext = context.getApplicationContext();
        SimpleExoPlayer simpleExoPlayer = ExoPlayerFactory.newSimpleInstance(mApplicationContext);
    }

    @Override
    public void setDisplay(SurfaceHolder surfaceHolder) {

    }

    @Override
    public void setSurface(Surface surface) {

    }

    @Override
    public void setDataSource(Context context, Uri uri) throws IOException, IllegalArgumentException, SecurityException, IllegalStateException {

    }

    @Override
    public void setDataSource(Context context, Uri uri, Map<String, String> headers) throws IOException, IllegalArgumentException, SecurityException, IllegalStateException {

    }

    @Override
    public void setDataSource(FileDescriptor fd) throws IOException, IllegalArgumentException, IllegalStateException {

    }

    @Override
    public void setDataSource(String path) throws IOException, IllegalArgumentException, SecurityException, IllegalStateException {

    }

    @Override
    public String getDataSource() {
        return null;
    }

    @Override
    public void prepareAsync() throws IllegalAccessException {

    }

    @Override
    public void start() {

    }

    @Override
    public void stop() {

    }

    @Override
    public void pause() {

    }

    @Override
    public boolean isPlaying() {
        return false;
    }

    @Override
    public void setScreenOnPlaying(boolean enable) {

    }

    @Override
    public void seekTo(long milliSecond) {

    }

    @Override
    public void setVolume(int leftVolume, int rightVolume) {

    }

    @Override
    public void release() {

    }

    @Override
    public void reset() {

    }

    @Override
    public int getVideoWidth() {
        return 0;
    }

    @Override
    public int getVideoHeight() {
        return 0;
    }

    @Override
    public long getCurrentPos() {
        return 0;
    }

    @Override
    public long getDuration() {
        return 0;
    }
}
