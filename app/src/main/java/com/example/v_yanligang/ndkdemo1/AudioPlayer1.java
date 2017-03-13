package com.example.v_yanligang.ndkdemo1;

/**
 * Created by v_yanligang on 2017/3/7.
 */

public class AudioPlayer1 {
    static {
        System.loadLibrary("Player");
    }
//    public static native void createEngine();
//    public static native boolean createBufferQueueAudioPlayer(int sampleRate, int samplesPerBuf);
    public static native void playAudio(String url);
    public static native void stopAudio();
}
