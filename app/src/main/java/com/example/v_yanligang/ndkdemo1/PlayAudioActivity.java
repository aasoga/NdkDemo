package com.example.v_yanligang.ndkdemo1;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

/**
 * Created by v_yanligang on 2017/3/2.
 */

public class PlayAudioActivity extends Activity implements View.OnClickListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio);
        findViewById(R.id.bt_asset).setOnClickListener(this);
        findViewById(R.id.bt_online).setOnClickListener(this);
//        AudioPlayer.createEngine();
        AudioPlayer.createUriAudioPlayer("http://www.freesound.org/data/previews/18/18765_18799-lq.mp3");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_asset:
                break;
            case R.id.bt_online:
                AudioPlayer.setPlayingUriAudioPlayer(true);
                break;
        }
    }

}
