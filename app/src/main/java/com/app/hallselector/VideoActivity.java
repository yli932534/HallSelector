package com.app.hallselector;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

public class VideoActivity extends AppCompatActivity {

    private YouTubePlayerView youTubePlayerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        Intent intent = getIntent();

        youTubePlayerView = findViewById(R.id.activity_youtubePlayerView);
        getLifecycle().addObserver(youTubePlayerView);


     youTubePlayerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
        @Override
        public void onReady(@NonNull YouTubePlayer youTubePlayer) {
            String videoId = "1FJHYqE0RDg";
            youTubePlayer.loadVideo(videoId, 0);
        }
    });
        SharedPreferences store = getSharedPreferences("bk_color", Context.MODE_PRIVATE);
        int color = store.getInt("bk_color", Color.parseColor("#FFE4E9"));
        findViewById(R.id.video_layout).setBackgroundColor(color);
}
}