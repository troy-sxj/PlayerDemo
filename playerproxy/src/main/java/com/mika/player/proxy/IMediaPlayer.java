package com.mika.player.proxy;

import android.annotation.TargetApi;
import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.view.Surface;
import android.view.SurfaceHolder;

import java.io.FileDescriptor;
import java.io.IOException;
import java.util.Map;

/**
 * @Author: mika
 * @Time: 2019/1/18 4:58 PM
 * @Description:
 */
public interface IMediaPlayer {

    int MEDIA_INFO_UNKNOWN = 1;
    int MEDIA_INFO_STARTED_AS_NEXT = 2;
    int MEDIA_INFO_VIDEO_RENDERING_START = 3;
    int MEDIA_INFO_VIDEO_TRACK_LAGGING = 700;
    int MEDIA_INFO_BUFFERING_START = 701;
    int MEDIA_INFO_BUFFERING_END = 702;
    int MEDIA_INFO_NETWORK_BANDWIDTH = 703;
    int MEDIA_INFO_BAD_INTERLEAVING = 800;
    int MEDIA_INFO_NOT_SEEKABLE = 801;
    int MEDIA_INFO_METADATA_UPDATE = 802;
    int MEDIA_INFO_TIMED_TEXT_ERROR = 900;
    int MEDIA_INFO_UNSUPPORTED_SUBTITLE = 901;
    int MEDIA_INFO_SUBTITLE_TIMED_OUT = 902;

    int MEDIA_INFO_VIDEO_ROTATION_CHANGED = 10001;
    int MEDIA_INFO_AUDIO_RENDERING_START  = 10002;
    int MEDIA_INFO_AUDIO_DECODED_START    = 10003;
    int MEDIA_INFO_VIDEO_DECODED_START    = 10004;
    int MEDIA_INFO_OPEN_INPUT             = 10005;
    int MEDIA_INFO_FIND_STREAM_INFO       = 10006;
    int MEDIA_INFO_COMPONENT_OPEN         = 10007;
    int MEDIA_INFO_VIDEO_SEEK_RENDERING_START = 10008;
    int MEDIA_INFO_AUDIO_SEEK_RENDERING_START = 10009;
    int MEDIA_INFO_MEDIA_ACCURATE_SEEK_COMPLETE = 10100;

    int MEDIA_ERROR_UNKNOWN = 1;
    int MEDIA_ERROR_SERVER_DIED = 100;
    int MEDIA_ERROR_NOT_VALID_FOR_PROGRESSIVE_PLAYBACK = 200;
    int MEDIA_ERROR_IO = -1004;
    int MEDIA_ERROR_MALFORMED = -1007;
    int MEDIA_ERROR_UNSUPPORTED = -1010;
    int MEDIA_ERROR_TIMED_OUT = -110;

    /*******************/

    void setDisplay(SurfaceHolder surfaceHolder);

    void setSurface(Surface surface);

    /*******************/

    void setDataSource(Context context, Uri uri)
            throws IOException, IllegalArgumentException, SecurityException, IllegalStateException;

    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    void setDataSource(Context context, Uri uri, Map<String, String> headers)
            throws IOException, IllegalArgumentException, SecurityException, IllegalStateException;

    void setDataSource(FileDescriptor fd)
            throws IOException, IllegalArgumentException, IllegalStateException;

    void setDataSource(String path)
            throws IOException, IllegalArgumentException, SecurityException, IllegalStateException;

    String getDataSource();

    /*******************/

    void prepareAsync() throws IllegalAccessException;

    void start();

    void stop();

    void pause();

    boolean isPlaying();

    void setScreenOnPlaying(boolean enable);

    void seekTo(long milliSecond);

    void setVolume(int leftVolume, int rightVolume);

    void release();

    void reset();

    int getVideoWidth();

    int getVideoHeight();

    long getCurrentPos();

    long getDuration();

    /*******************/

    void setOnPreparedListener(OnPreparedListener onPreparedListener);

    void setOnCompletedListener(OnCompletedListener onCompletedListener);

    void setOnBufferingUpdateListener(OnBufferingUpdateListener onBufferingUpdateListener);

    void setOnSeekCompletedListener(OnSeekCompletedListener onSeekCompletedListener);

    void setOnVideoSizeChangedListener(OnVideoSizeChangedListener onVideoSizeChangedListener);

    void setOnInfoListener(OnInfoListener onInfoListener);

    void setOnErrorListener(OnErrorListener onErrorListener);

    interface OnPreparedListener{
        void onPrepared(IMediaPlayer mediaPlayer);
    }

    interface OnCompletedListener{
        void onCompleted(IMediaPlayer mediaPlayer);
    }

    interface OnBufferingUpdateListener{
        void onBufferingUpdate(IMediaPlayer mediaPlayer, int percent);
    }

    interface OnSeekCompletedListener{
        void onSeekCompleted(IMediaPlayer mediaPlayer);
    }

    interface OnVideoSizeChangedListener{
        void onVideoSizeChanged(IMediaPlayer mediaPlayer, int width, int height, int sraNum, int sarDen);
    }

    interface OnInfoListener{
        boolean onInfo(IMediaPlayer mediaPlayer, int what, int extra);
    }

    interface OnErrorListener{
        boolean onError(IMediaPlayer mediaPlayer, int what, int extra);
    }
}
