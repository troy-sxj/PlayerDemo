package com.mika.player;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * @Author: mika
 * @Time: 2019/1/4 5:21 PM
 * @Description:
 */
public class MediaPlayerActivity extends AppCompatActivity {

    private SelfMediaPlayer videoView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mediaplayer);

        videoView = findViewById(R.id.videoView);
//        videoView.setPlayerControl(new MediaController(this));
        findViewById(R.id.btnPlay).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                play();
            }
        });
    }

    private void play() {
        videoView.setDataSource("http://gslb.miaopai.com/stream/DL44MdyvivViKTQxzJZX3raspEO-0Sh4.mp4");
    }
}
