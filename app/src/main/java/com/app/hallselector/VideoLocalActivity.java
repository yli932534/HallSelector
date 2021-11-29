package com.app.hallselector;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.MediaController;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

//https://technobyte.org/play-video-file-android-studio-using-videoview-tutorial//
public class VideoLocalActivity extends AppCompatActivity {

    VideoView vid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_local);



    }

        public void playVideo(View v) {
            MediaController m = new MediaController(this);
            vid = (VideoView) findViewById(R.id.videoView);
            vid.setVideoPath("android.resource://" + getPackageName() + "/" + R.raw.a);
            MediaController mediaController = new MediaController(this);
            vid.setMediaController(mediaController);
            vid.start();
            vid.setMediaController(m);

            vid.start();
            MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.audio);
            mediaPlayer.start();
        }


}