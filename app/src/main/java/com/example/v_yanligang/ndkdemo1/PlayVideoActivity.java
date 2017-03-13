package com.example.v_yanligang.ndkdemo1;

import android.app.Activity;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by v_yanligang on 2017/2/27.
 */

public class PlayVideoActivity extends Activity implements SurfaceHolder.Callback {
    private SurfaceHolder mSurfaceHolder;
    private Video mVideo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        mVideo = (Video) getIntent().getSerializableExtra("video");
        SurfaceView sv = (SurfaceView) findViewById(R.id.sv);
        mSurfaceHolder = sv.getHolder();
        mSurfaceHolder.addCallback(this);
        new Thread(new Runnable() {
            @Override
            public void run() {
                AudioPlayer1.playAudio(mVideo.path);
            }
        }).start();
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                VideoPlayer.play(mSurfaceHolder.getSurface(), mVideo.path);
            }
        }).start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        AudioPlayer1.stopAudio();
    }
}
