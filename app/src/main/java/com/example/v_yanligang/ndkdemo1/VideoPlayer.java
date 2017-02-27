package com.example.v_yanligang.ndkdemo1;

/**
 * Created by Jonesx on 2016/3/12.
 */
public class VideoPlayer {

    static {
        System.loadLibrary("VideoPlayer");
    }

    public static native int play(Object surface, String path);
}
