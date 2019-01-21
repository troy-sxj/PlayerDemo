package com.mika.player.camera;

/**
 * @Author: mika
 * @Time: 2019/1/18 10:57 AM
 * @Description:
 * encode video into a fixed-size circular buffer
 */
public class CircularEncoder {

    public static final int SAVE_STATUS_SUCCESS= 1;
    public static final int SAVE_STATUS_FAILED = -1;

    public interface Callback{
        //video save status
        void fileSaveComplete(int status);

        //video buffer duration: millisecond
        void bufferStatus(long totalTime);
    }

    /**
     * Configure Video Encoder
     * @param width
     * @param height
     * @param bitRate
     * @param frameRate
     * @param desiredSpanSec
     * @param callback
     */
    public CircularEncoder(int width, int height, int bitRate, int frameRate, int desiredSpanSec, Callback callback){
        CircularEncoderBuffer circularEncoderBuffer = new CircularEncoderBuffer(bitRate, frameRate);
    }


}
