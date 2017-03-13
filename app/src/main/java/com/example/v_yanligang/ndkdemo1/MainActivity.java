package com.example.v_yanligang.ndkdemo1;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {

    private static final int READ_PERMISSION_CODE = 1;
    private List<Video> mList = new ArrayList<>();
    private Myadapter mAdapter;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
//            LogUtils.e("video", "art" + mList.get(0).displayName);
            mAdapter.notifyDataSetChanged();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_nativevideo);
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE}, READ_PERMISSION_CODE);
        } else {
            initData();
        }
        initView();
    }

    public void initData() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                VideoProvider videoProvider = new VideoProvider();
                mList = videoProvider.getVideoList(getApplicationContext());
                mHandler.sendEmptyMessage(0);
            }
        }).start();
    }

    private void initView() {
        RecyclerView rv = (RecyclerView) findViewById(R.id.lv);
        mAdapter = new Myadapter();
        rv.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        rv.setAdapter(mAdapter);
        TextView textView = new TextView(getApplicationContext());
        textView.setText("音频播放");
        textView.setTextSize(30);
        mAdapter.setmHeadView(textView);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == READ_PERMISSION_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                initData();
            } else {
                Toast.makeText(MainActivity.this, "Permission Denied", Toast.LENGTH_SHORT).show();
            }
            return;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    public class Myadapter extends RecyclerView.Adapter<Myadapter.ViewHolder>{
        private View mHeadView;
        private static final int TYPE_HEADER = 0;
        private static final int TYPE_NORMAL = 1;

        public void setmHeadView(View headView) {
            this.mHeadView = headView;
            notifyItemChanged(0);
        }

        @Override
        public int getItemViewType(int position) {
            if (mHeadView != null && position == 0) {
                return TYPE_HEADER;
            }
            return TYPE_NORMAL;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            if (mHeadView != null && viewType == TYPE_HEADER) {
                return new ViewHolder(mHeadView);
            }
            return new ViewHolder(LayoutInflater.from(getApplicationContext()).inflate(R.layout.item_play_nativevideo,null));
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            if (mHeadView != null && getItemViewType(position) == TYPE_HEADER) {
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
//                        Toast.makeText(getApplication(), "音频播放" , Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(MainActivity.this, PlayAudioActivity.class);
                        startActivity(intent);
                    }
                });
                return;
            }

            if (mHeadView != null) {
                position = position -1;
            }
            if (mList.size() != 0) {
                Log.e("playvideo", "size" + mList.size());
                Log.e("playvideo", "size" + mList.size());
                Video video = mList.get(position);
                holder.mTitle.setText(video.title);
                long min = video.duration/1000/60;
                long sec = video.duration/1000%60;
                holder.mTime.setText("时长" + min + ":" + sec);
                int side = dip2px(getApplicationContext(), 100f);
                ImageLoader.loadwithDefault(holder.mImage, video.path, side, side);
            }
        }

        @Override
        public int getItemCount() {
            if (mHeadView != null) {
                return mList.size() + 1;
            }
            return mList.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
            public ImageView mImage;
            public TextView mTitle;
            public TextView mTime;
            public ViewHolder(View itemView) {
                super(itemView);
                if (itemView == mHeadView) {
                    return;
                }
                mImage = (ImageView) itemView.findViewById(R.id.iv);
                mTime = (TextView) itemView.findViewById(R.id.tv_time);
                mTitle = (TextView) itemView.findViewById(R.id.tv_title);
                itemView.setOnClickListener(this);
            }

            @Override
            public void onClick(View v) {
                Log.e("video", "position" + getLayoutPosition());
                Intent intent = new Intent(MainActivity.this, PlayVideoActivity.class);
                intent.putExtra("video", mList.get(getLayoutPosition()-1));
                startActivity(intent);
            }
        }
    }


    public int dip2px(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }
}
