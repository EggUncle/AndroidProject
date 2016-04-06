package com.example.administrator.mymusicplayer;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Parcelable;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.view.View;
import android.widget.RemoteViews;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MusicService extends Service {

    //使用服务后台播放音乐
    final Intent intent = new Intent(MainActivity.RECEIVER_ACTION);
    int status = 0x11;
    static final int START_OR_STOP = 0x11;
    static final int PLAY_LIST = 0x12;
    static final int PLAY_NEXT = 0x13;
    static final int PLAY_PROGRESS = 0X14;
    int nowPlaying = 0;

    Boolean bol_Playing = false;
    List<Mp3Info> mp3InfoList;
    List<HashMap<String, String>> hashMapList;
    MyListAdapter myListAdapter;
    MediaPlayer mPlayer;

    MyReceiver serviceReceiver;


    public MusicService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        myListAdapter = new MyListAdapter();
        serviceReceiver = new MyReceiver();

        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(MainActivity.SERVICE_ACTION);
        registerReceiver(serviceReceiver, intentFilter);

        MyAyncTask myAyncTask = new MyAyncTask();
        myAyncTask.execute();
    }

    public class MyReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            int control = intent.getIntExtra("control", -1);
            StrartbarUpdate();
            //     Log.v("MY_TAG","onReceive");
            switch (control) {
                case START_OR_STOP: {
                    bol_Playing = intent.getBooleanExtra("play_or_stop", false);
                    if (mPlayer != null) {
                        if (bol_Playing) {
                            mPlayer.pause();
                            bol_Playing=false;
                        } else {
                            mPlayer.start();
                            bol_Playing=true;
                        }
                    }
                }
                break;
                case PLAY_LIST: {
                    int position = intent.getIntExtra("play_list", -1);
                    Play(position);
                    bol_Playing = true;
                }
                break;
                case PLAY_NEXT: {
                  //  int positon = intent.getIntExtra("play_next", -1);
                    nowPlaying = (nowPlaying + 1) % hashMapList.size();
                    Play(nowPlaying);
                    bol_Playing = true;
                }
                break;
                case PLAY_PROGRESS: {
                    int now_progress = intent.getIntExtra("progress", 0);
                    // Log.v("MY_TAG",now_progress+"");
                    mPlayer.seekTo(now_progress);
                }
                break;
                case 0x888: {
                    if (intent.getStringExtra("handler").equals("stop")) {
                        musicHandler.removeCallbacks(r);
                    } else {
                        musicHandler.post(r);
                    }
                }
                break;
            }
            //Intent sendIntent = new Intent(MainActivity.RECEIVER_ACTION);

        }
    }

    private void Play(int position) {
        try {
            if (!bol_Playing) {//未播放
                nowPlaying = position;
                mPlayer = new MediaPlayer();
                mPlayer.setDataSource(hashMapList.get(position).get("url").toString());
                Log.v("MY_TAG", "bofang");
                mPlayer.prepare();
                mPlayer.start();
            } else {//正在播放
                    nowPlaying = position;
                    if (mPlayer != null) {
                        mPlayer.stop();
                    }
                    mPlayer = new MediaPlayer();
                    mPlayer.setDataSource(hashMapList.get(nowPlaying).get("url").toString());

                    mPlayer.prepare();
                    mPlayer.start();

            }

            intent.putExtra("about_songs",hashMapList.get(nowPlaying).get("title").toString()
                    + "-" + hashMapList.get(nowPlaying).get("Artist").toString());
           // intent.putExtra("nowPlaying",nowPlaying);
            sendBroadcast(intent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    private void setDate() {
        mp3InfoList = MediaUtil.getMp3Infos(this);
        hashMapList = MediaUtil.getMusicMaps(mp3InfoList);
        myListAdapter.setListdata(this, hashMapList);
    }

    Handler musicHandler = new Handler();

    public void StrartbarUpdate() {
        musicHandler.post(r);
    }

    Runnable r = new Runnable() {

        @Override
        public void run() {
            // TODO Auto-generated method stub
            int CurrentPosition = mPlayer.getCurrentPosition();

            //    nowPlayTime.setText(ShowTime(CurrentPosition));
            int mMax = mPlayer.getDuration();
            String str_songs_time = ShowTime(CurrentPosition) + "/" + ShowTime(mMax);

            Intent sendIntent = new Intent(MainActivity.RECEIVER_ACTION);
            sendIntent.putExtra("str_songs_time", str_songs_time);
            sendIntent.putExtra("mMax", mMax);
            sendIntent.putExtra("CurrentPosition", CurrentPosition);
            sendBroadcast(sendIntent);
            if (!mPlayer.isPlaying() && bol_Playing) {
                //   mPlayer.stop();
                Log.v("MY_TAG", "next");
                nowPlaying = (nowPlaying + 1) % hashMapList.size();
                Play(nowPlaying);
            }
            musicHandler.postDelayed(r, 100);

        }
    };

    private class MyAyncTask extends AsyncTask<Void, Integer, ArrayList<String>> {

        //   ArrayList<String> list_name_data;

        public MyAyncTask() {
            super();
        }


        @Override
        protected void onPostExecute(ArrayList<String> strings) {
            //执行后返回值
            super.onPostExecute(strings);
//            mProgressBar.setVisibility(View.GONE);
//            music_list.setVisibility(View.VISIBLE);
//            music_list.setAdapter(myListAdapter);
//            Intent intent = new Intent(MainActivity.RECEIVER_ACTION);
//            intent.putExtra("list_data", (Parcelable) hashMapList);
            //  intent.putStringArrayListExtra("list_data",hashMapList);
            // sendBroadcast(intent);
            //   intent.putParcelableArrayListExtra("list_data",hashMapList);
        }

        @Override
        protected void onPreExecute() {
            //执行前的初始化操作
            super.onPreExecute();
            //music_list.setVisibility(View.GONE);
            //    mProgressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            //更新时调用的操作
            //  listFileData = getMapData(listFileData);
            super.onProgressUpdate(values);
        }

        @Override
        protected ArrayList<String> doInBackground(Void... params) {
            //后台加载时的操作
            //  getMapData(listFileData);
            setDate();
            return null;
        }
    }

    public String ShowTime(int time) {
        time /= 1000;
        int minute = time / 60;
        int hour = minute / 60;
        int second = time % 60;
        minute %= 60;
        return String.format("%02d:%02d", minute, second);
    }

}
