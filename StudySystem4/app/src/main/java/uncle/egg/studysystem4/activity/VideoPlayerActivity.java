package uncle.egg.studysystem4.activity;

import android.graphics.PixelFormat;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

import java.io.File;

import uncle.egg.studysystem4.R;

public class VideoPlayerActivity extends AppCompatActivity {

    private VideoView videoView;
    private MediaController mediaController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_player);
        getWindow().setFormat(PixelFormat.TRANSLUCENT);
        videoView= (VideoView) findViewById(R.id.video);
        mediaController=new MediaController(this);
        File video=new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/test3.mp4");
        if (video.exists()){
            videoView.setVideoPath(video.getAbsolutePath());
            videoView.setMediaController(mediaController);
            mediaController.setMediaPlayer(videoView);
            videoView.requestFocus();
        }
    }
}
