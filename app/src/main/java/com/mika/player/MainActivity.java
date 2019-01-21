package com.mika.player;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    String[] paths = new String[]{
            "http://1251526118.vod2.myqcloud.com/9643e0dfvodgzp1251526118/a2f369927447398156461367534/H2xR2tIC6PcA.mp4"
            , "http://gslb.miaopai.com/stream/DL44MdyvivViKTQxzJZX3raspEO-0Sh4.mp4"
            , "rtmp://live.hkstv.hk.lxdns.com/live/hks"
            , "http://hdl0901.plures.net/onlive/166912cb16b544b9bd1fcea43e38dc0f.flv?wsSecret=c97f096444055448f898544318b62f90&wsTime=5ba231c8"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btnVideoView).setOnClickListener(this);
        findViewById(R.id.btnMediaPlayer).setOnClickListener(this);
        findViewById(R.id.btnMediaApi).setOnClickListener(this);
        findViewById(R.id.btnSurface).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnVideoView:
                testVideoView();
                break;
            case R.id.btnMediaPlayer:
                testMediaPlayer();
                break;
            case R.id.btnMediaApi:
                testMediaApi();
                break;
            case R.id.btnSurface:
                testSurface();
                break;
        }
    }

    private void testVideoView() {
        startActivity(new Intent(this, VideoViewActivity.class));
    }

    private void testMediaPlayer() {
        startActivity(new Intent(this, PlayerTestActivity.class));
    }

    private void testMediaApi() {

    }

    private void testSurface(){
        startActivity(new Intent(this, TestSurfaceActivity.class));
    }
}
