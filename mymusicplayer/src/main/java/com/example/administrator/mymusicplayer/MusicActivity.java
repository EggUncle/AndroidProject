package com.example.administrator.mymusicplayer;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ButtonBarLayout;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MusicActivity extends AppCompatActivity {

    private ImageButton btn_preview;
    private ImageButton btn_start_or_stop;
    private ImageButton btn_next;
    private ImageButton btn_back;
    private SeekBar music_seek_bar;
    private AlwaysMarqueeTextView txt_about_songs;

    final Intent intent = new Intent(MainActivity.SERVICE_ACTION);
    MusicReceiver musicReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music);
        getSupportActionBar().hide();
        init();
        //  Intent intent = new Intent();
        txt_about_songs.setText(getIntent().getStringExtra("about_songs").toString());
        if (MainActivity.bol_Playing) {
            btn_start_or_stop.setImageResource(R.drawable.stop_play);
        } else {
            btn_start_or_stop.setImageResource(R.drawable.start_play);
        }
    }

    private void init() {
        musicReceiver = new MusicReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction(MainActivity.RECEIVER_ACTION);
        registerReceiver(musicReceiver, filter);
        Intent intent = new Intent(this, MusicService.class);
        startService(intent);

        btn_preview = (ImageButton) findViewById(R.id.btn_preview_2);
        btn_start_or_stop = (ImageButton) findViewById(R.id.btn_start_or_stop_2);
        btn_next = (ImageButton) findViewById(R.id.btn_next_2);
        btn_back = (ImageButton) findViewById(R.id.btn_back_2);
        music_seek_bar = (SeekBar) findViewById(R.id.music_seek_bar_2);
        music_seek_bar.setOnSeekBarChangeListener(new SeekBarListener());
        txt_about_songs = (AlwaysMarqueeTextView) findViewById(R.id.txt_about_songs2);
        MyListener listener = new MyListener();
        btn_start_or_stop.setOnClickListener(listener);
        btn_next.setOnClickListener(listener);
        btn_preview.setOnClickListener(listener);
    }

    private class MyListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btn_preview_2:
                    break;
                case R.id.btn_start_or_stop_2:
                    if (MainActivity.bol_Playing) {
                        MainActivity.bol_Playing = false;
                        btn_start_or_stop.setImageResource(R.drawable.start_play);
                    } else {
                        MainActivity.bol_Playing = true;
                        btn_start_or_stop.setImageResource(R.drawable.stop_play);
                    }
                    break;
                case R.id.btn_next_2:
                    //   MainActivity.nowPlaying++;
                    txt_about_songs.setText(MainActivity.hashMapList.get((MainActivity.nowPlaying+1)%MainActivity.hashMapList.size()).get("title").toString()
                            + "-" + MainActivity.hashMapList.get((MainActivity.nowPlaying+1)%MainActivity.hashMapList.size()).get("Artist").toString());
                    //     intent.putExtra("play_next", nowPlaying);
                    intent.putExtra("control", MainActivity.PLAY_NEXT);
                    Toast.makeText(MusicActivity.this,"123",Toast.LENGTH_SHORT).show();
                    sendBroadcast(intent);

                    break;
                case R.id.btn_back_2:
                    txt_about_songs.setText(MainActivity.hashMapList.get((MainActivity.nowPlaying-1)%MainActivity.hashMapList.size()).get("title").toString()
                            + "-" + MainActivity.hashMapList.get((MainActivity.nowPlaying-1)%MainActivity.hashMapList.size()).get("Artist").toString());
                    //     intent.putExtra("play_next", nowPlaying);
                    intent.putExtra("control", MainActivity.PLAY_BACK);
                    Toast.makeText(MusicActivity.this,"123",Toast.LENGTH_SHORT).show();
                    sendBroadcast(intent);
                    break;
            }
        }
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
            MainActivity.mPlayer.seekTo(seekBar.getProgress());
            int SeekPosition = seekBar.getProgress();
            Log.v("MY_TAG", "SeekPosition: " + SeekPosition + " " + "MAX" + "seekBar.getMax()" + seekBar.getMax());
            intent.putExtra("control", MainActivity.PLAY_PROGRESS);
            intent.putExtra("progress", SeekPosition);
            sendBroadcast(intent);
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
            //   txt_time.setText(str_songs_time);
            if (intent.getStringExtra("about_songs") != null) {

                  txt_about_songs.setText(intent.getStringExtra("about_songs"));
                //    nowPlaying++;

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
}
