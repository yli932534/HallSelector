package com.app.hallselector;

import android.net.Uri;
import android.os.Bundle;
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
        vid = (VideoView) findViewById(R.id.videoView);

    }
        public void playVideo(View v) {
            MediaController m = new MediaController(this);
            vid.setMediaController(m);
           // vid.setVideoPath("android.resource://" + getPackageName() + "/" + R.raw.a);
            String path = "android.resource://" + getPackageName() + "/" + R.raw.a;
            Uri u = Uri.parse(path);

            vid.setVideoURI(u);
            vid.start();

        }


}