package com.example.v_yanligang.ndkdemo1;

/**
 * Created by v_yanligang on 2017/2/21.
 */

public class JniUtils {
    static {
        System.loadLibrary("native-lib");
    }
    private static JniUtils mInstance;
    public static JniUtils getInstance() {
        if (mInstance == null) {
            mInstance = new JniUtils();
        }
        return mInstance;
    }

    public native String stringFromJNI();
}
