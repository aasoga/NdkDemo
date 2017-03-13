package com.example.v_yanligang.ndkdemo1;

/**
 * Created by v_yanligang on 2017/3/1.
 */

public class AudioPlayer {
    static {
        System.loadLibrary("Player");
    }
    public static native void createEngine();
    public static native void createBufferQueueAudioPlayer(int sampleRate, int samplesPerBuf);
    public static native boolean createUriAudioPlayer(String url);
    public static native void setPlayingUriAudioPlayer(boolean isPlaying);
    public static native int playAudio(String fileName);
}
