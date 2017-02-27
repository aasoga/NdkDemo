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
        // Example of a call to a native method
//    TextView tv = (TextView) findViewById(R.id.sample_text);
//    tv.setText(stringFromJNI());
        SurfaceView sv = (SurfaceView) findViewById(R.id.sv);
        mSurfaceHolder = sv.getHolder();
        mSurfaceHolder.addCallback(this);
    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */

    // Used to load the 'native-lib' library on application startup.
//    static {
//        System.loadLibrary("native-lib");
//    }

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
}
