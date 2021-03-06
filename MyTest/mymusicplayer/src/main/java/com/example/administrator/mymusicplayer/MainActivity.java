package com.example.administrator.mymusicplayer;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    ListView music_list;
    Button btn_previous;
    ImageButton btn_start_or_stop;
    ImageButton btn_next;
    ImageButton img_song_icon;
    SeekBar music_seek_bar;
    ProgressBar mProgressBar;
    TextView txt_time;
    // TextView txt_about_songs;
    AlwaysMarqueeTextView txt_about_songs;


    ArrayList<File> listFileData;
    List<Map<String, Object>> listData;
    MyListAdapter myListAdapter;

    static MediaPlayer mPlayer;
    static Boolean bol_Playing;
    static Boolean bol_pause;
    static int nowPlaying = 0;

    static List<Mp3Info> mp3InfoList;
    static List<HashMap<String, String>> hashMapList;
    final Intent intent = new Intent(SERVICE_ACTION);


    MusicReceiver musicReceiver;
    static final int START_OR_STOP = 0x11;
    static final int PLAY_LIST = 0x12;
    static final int PLAY_NEXT = 0x13;
    static final int PLAY_PROGRESS = 0X14;
    static final int PLAY_BACK = 0x15;
    //定义播放状态，0x11 没播放,0x12播放中,0x13暂停
    public static final String SERVICE_ACTION = "com.example.android.mymusicplayer.service";
    public static final String RECEIVER_ACTION = "com.example.android.mymusicplayer.receiver";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
//                != PackageManager.PERMISSION_GRANTED) {
//            //申请WRITE_EXTERNAL_STORAGE权限
//            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
//                    1);
//        }

        init();
        setMyClick();


        MyAyncTask myAyncTask = new MyAyncTask();
        myAyncTask.execute();


        musicReceiver = new MusicReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction(RECEIVER_ACTION);
        registerReceiver(musicReceiver, filter);
        Intent intent = new Intent(this, MusicService.class);
        startService(intent);


    }


    private void musicInit() {
        img_song_icon.setImageBitmap(MediaUtil.getAlbumImage(
                Integer.parseInt(hashMapList.get(0).get("albumId")), MainActivity.this));
        txt_about_songs.setText(hashMapList.get(0).get("title").toString()
                + "-" + hashMapList.get(0).get("Artist").toString());
        String time =hashMapList.get(0).get("duration");
        String str_songs_time = "00:00" + "/" + time;
        music_seek_bar.setMax(100);
        music_seek_bar.setProgress(0);
        txt_time.setText(str_songs_time);

    }

    private void init() {
        findView();
        bol_Playing = false;
        bol_pause = true;
        music_seek_bar.setOnSeekBarChangeListener(new SeekBarListener());
        listFileData = new ArrayList<>();
        listData = new ArrayList<>();
        myListAdapter = new MyListAdapter();
        mPlayer = new MediaPlayer();


    }

    private void findView() {
        txt_about_songs = (AlwaysMarqueeTextView) findViewById(R.id.txt_about_songs);
        img_song_icon = (ImageButton) findViewById(R.id.img_song_icon);
        txt_time = (TextView) findViewById(R.id.txt_time);
        mProgressBar = (ProgressBar) findViewById(R.id.progressBar);
        music_list = (ListView) findViewById(R.id.music_list);
        music_seek_bar = (SeekBar) findViewById(R.id.music_seek_bar);
        // btn_previous = (Button) findViewById(R.id.btn_previous);
        btn_start_or_stop = (ImageButton) findViewById(R.id.btn_start_or_stop);
        btn_next = (ImageButton) findViewById(R.id.btn_next);
    }

    private void setMyClick() {

        img_song_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MusicActivity.class);
                intent.putExtra("about_songs", txt_about_songs.getText().toString());
                //  Bundle bundle = new Bundle(img_song_icon.getId());
//        intent.putExtra("img_song_icon",MediaUtil.getAlbumImage(
//                Integer.parseInt(hashMapList.get(nowPlaying).get("albumId")), MainActivity.this));
                Log.v("MY_TAG", txt_about_songs.getText().toString());

                startActivity(intent);

            }
        });

        music_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @TargetApi(Build.VERSION_CODES.M)
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                btn_start_or_stop.setImageResource(R.drawable.iconfont_zanting);
                bol_Playing = true;
                bol_pause = false;
                nowPlaying = position;
                img_song_icon.setImageBitmap(MediaUtil.getAlbumImage(
                        Integer.parseInt(hashMapList.get(position).get("albumId")), MainActivity.this));
                txt_about_songs.setText(hashMapList.get(position).get("title").toString()
                        + "-" + hashMapList.get(position).get("Artist").toString());
                intent.putExtra("play_list", position);
                intent.putExtra("control", PLAY_LIST);
                sendBroadcast(intent);
            }
        });

        btn_start_or_stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v("MY_TAG", "onClick");
                if (bol_Playing) {
                    intent.putExtra("play_or_stop", bol_Playing);
                    bol_Playing = false;
                    bol_pause = true;
                    intent.putExtra("control", START_OR_STOP);
                    sendBroadcast(intent);
                    btn_start_or_stop.setImageResource(R.drawable.iconfont_bofangqibofang);
                } else {
                    intent.putExtra("play_or_stop", bol_Playing);
                    bol_Playing = true;
                    bol_pause = false;
                    intent.putExtra("control", START_OR_STOP);
                    sendBroadcast(intent);
                    btn_start_or_stop.setImageResource(R.drawable.iconfont_zanting);
                }

                txt_about_songs.setText(hashMapList.get(nowPlaying).get("title").toString()
                        + "-" + hashMapList.get(nowPlaying).get("Artist").toString());
            }
        });
        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //   nowPlaying = (nowPlaying + 1) % hashMapList.size();
                txt_about_songs.setText(hashMapList.get(nowPlaying).get("title").toString()
                        + "-" + hashMapList.get(nowPlaying).get("Artist").toString());
                //     intent.putExtra("play_next", nowPlaying);
                img_song_icon.setImageBitmap(MediaUtil.getAlbumImage(
                        Integer.parseInt(hashMapList.get(nowPlaying + 1).get("albumId")), MainActivity.this));
                intent.putExtra("control", PLAY_NEXT);
                sendBroadcast(intent);
            }
        });

    }


    class SeekBarListener implements SeekBar.OnSeekBarChangeListener {

        @Override
        public void onProgressChanged(SeekBar seekBar, int progress,
                                      boolean fromUser) {
            // TODO Auto-generated method stub
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {
            // TODO Auto-generated method stub
            intent.putExtra("control", 0x888);
            intent.putExtra("handler", "stop");
            sendBroadcast(intent);
        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
            // TODO Auto-generated method stub
            intent.putExtra("control", 0x888);
            intent.putExtra("handler", "start");
            sendBroadcast(intent);
            mPlayer.seekTo(seekBar.getProgress());
            int SeekPosition = seekBar.getProgress();
            Log.v("MY_TAG", "SeekPosition: " + SeekPosition + " " + "MAX" + "seekBar.getMax()" + seekBar.getMax());
            intent.putExtra("control", PLAY_PROGRESS);
            intent.putExtra("progress", SeekPosition);
            sendBroadcast(intent);
        }

    }

//    public String ShowTime(int time) {
//        time /= 1000;
//        int minute = time / 60;
//        int hour = minute / 60;
//        int second = time % 60;
//        minute %= 60;
//        return String.format("%02d:%02d", minute, second);
//    }

    private void setDate() {

        mp3InfoList = MediaUtil.getMp3Infos(this);
        hashMapList = MediaUtil.getMusicMaps(mp3InfoList);
        //  getAllFiles(new File("/sdcard"));
        //   listData = getMapData(listFileData);
        myListAdapter.setListdata(this, hashMapList);
        //  SimpleAdapter ladapter = new SimpleAdapter(this, getMapData(listFileData), R.layout.relative, new String[]{"ItemText", "ItemTitle"}, new int[]{R.id.ItemTitle, R.id.ItemText});
//        Message message = new Message();
//        message.what = 0x123;
//        handler.sendMessage(message);
        music_list.setAdapter(myListAdapter);
    }


//    @Override
//    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        doNext(requestCode,grantResults);
//    }
//
//    private void doNext(int requestCode, int[] grantResults) {
//        if (requestCode == 1) {
//            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                // Permission Granted
//                MyAyncTask myAyncTask = new MyAyncTask();
//                myAyncTask.execute();
//            } else {
//                // Permission Denied
//
//            }
//        }
//    }

    private class MyAyncTask extends AsyncTask<Void, Integer, ArrayList<String>> {

        //   ArrayList<String> list_name_data;

        public MyAyncTask() {
            super();
        }


        @Override
        protected void onPostExecute(ArrayList<String> strings) {
            //执行后返回值
            super.onPostExecute(strings);
            //     mPlayer = new MediaPlayer();
//            try {
//                mPlayer.setDataSource(hashMapList.get(MainActivity.nowPlaying).get("url").toString());
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            txt_about_songs.setText(hashMapList.get(nowPlaying).get("title").toString()
//                    + "-" + hashMapList.get(nowPlaying).get("Artist").toString());
//            txt_time.setText("00:00"+"/"+ShowTime(mPlayer.getDuration()));

            mProgressBar.setVisibility(View.GONE);
            music_list.setVisibility(View.VISIBLE);
            music_list.setAdapter(myListAdapter);

            musicInit();
        }

        @Override
        protected void onPreExecute() {
            //执行前的初始化操作
            super.onPreExecute();
            music_list.setVisibility(View.GONE);
            mProgressBar.setVisibility(View.VISIBLE);
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


    public class MusicReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {

            String str_songs_time = intent.getStringExtra("str_songs_time");
            int mMax = intent.getIntExtra("mMax", 1);
            int CurrentPosition = intent.getIntExtra("CurrentPosition", 1);
            music_seek_bar.setMax(mMax);
            music_seek_bar.setProgress(CurrentPosition);
            txt_time.setText(str_songs_time);
            if (intent.getStringExtra("about_songs") != null) {

                txt_about_songs.setText(intent.getStringExtra("about_songs"));
                //    nowPlaying++;

            }
            if (intent.getBooleanExtra("next_songs", false)) {
                img_song_icon.setImageBitmap(MediaUtil.getAlbumImage(
                        Integer.parseInt(hashMapList.get(nowPlaying).get("albumId")), MainActivity.this));
            }
            //  nowPlaying= intent.getIntExtra("nowPlaying",0);
//            String str_about_songs = intent.getStringExtra("str_about_songs");
//            txt_about_songs.setText(str_about_songs);
            //    int control = intent.getIntExtra("control", -1);
//            switch (control) {
//                case START_OR_STOP: {
////                    bol_Playing = intent.getBooleanExtra("play_or_stop", false);
////                    if (!bol_Playing) {
////                        btn_start_or_stop.setImageResource(R.drawable.iconfont_bofangqibofang);
////                    } else {
////                        btn_start_or_stop.setImageResource(R.drawable.iconfont_zanting);
////                    }
//
//                }
//                break;
//                case PLAY_LIST: {
//
//                }
//                break;
//                case PLAY_NEXT: {
//
//                }
//                break;
//            }

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


    public void gotoMusicActivity(View v) {
        Toast.makeText(MainActivity.this, "Test!", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(MainActivity.this, MusicActivity.class);
        intent.putExtra("about_songs", txt_about_songs.getText().toString());
        //  Bundle bundle = new Bundle(img_song_icon.getId());
//        intent.putExtra("img_song_icon",MediaUtil.getAlbumImage(
//                Integer.parseInt(hashMapList.get(nowPlaying).get("albumId")), MainActivity.this));
        Log.v("MY_TAG", txt_about_songs.getText().toString());
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.v("MY_TAG", "Sssss");
        unregisterReceiver(musicReceiver);
    }


}
