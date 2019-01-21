package com.mika.player;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.MediaController;
import android.widget.VideoView;

/**
 * @Author: mika
 * @Time: 2019/1/4 5:20 PM
 * @Description:
 */
public class VideoViewActivity extends AppCompatActivity {

    private VideoView videoView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_videoview);

        videoView = findViewById(R.id.videoView);
        findViewById(R.id.btnPlay).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                play();
            }
        });

        initPlayer();
    }

    private void initPlayer() {
        videoView.setMediaController(new MediaController(this));
    }

    private void play() {
        videoView.setVideoURI(Uri.parse("http://gslb.miaopai.com/stream/DL44MdyvivViKTQxzJZX3raspEO-0Sh4.mp4"));
//        videoView.setVideoURI(Uri.parse("http://1251526118.vod2.myqcloud.com/9643e0dfvodgzp1251526118/a2f369927447398156461367534/H2xR2tIC6PcA.mp4"));
        videoView.requestFocus();
//        videoView.start();
    }

    @Override
    protected void onPause() {
        videoView.pause();
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        videoView.resume();
    }
}
