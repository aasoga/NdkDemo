package com.example.v_yanligang.ndkdemo1;

import android.graphics.Bitmap;

import java.io.Serializable;

/**
 * Created by v_yanligang on 2017/2/10.
 */

public class Video implements Serializable{
    public int id;
    public String title;
    public String album;
    public String artist;
    public String displayName;
    public String mimeType;
    public String path;
    public long size;
    public long duration;
    public LoadedImage image;


    public Video() {
        super();
    }

    public Video(int id, String title, String album, String artist,
                 String displayName, String mimeType, String path, long size,
                 long duration) {
        super();
        this.id = id;
        this.title = title;
        this.album = album;
        this.artist = artist;
        this.displayName = displayName;
        this.mimeType = mimeType;
        this.path = path;
        this.size = size;
        this.duration = duration;
    }

    public class LoadedImage{
        Bitmap mBitmap;
        public LoadedImage(Bitmap mBitmap) {
            this.mBitmap = mBitmap;
        }
        public Bitmap getBitmap() {
            return mBitmap;
        }
    }
}
