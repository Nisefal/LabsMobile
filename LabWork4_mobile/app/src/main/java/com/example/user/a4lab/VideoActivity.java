package com.example.user.a4lab;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.VideoView;
import android.net.Uri;


import android.widget.MediaController;



public class VideoActivity extends AppCompatActivity {
    VideoView videoPlayer;
    public static String name;
    private static Uri video;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        ((Button) findViewById(R.id.play)).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        videoPlayer.start();
                    }
                }
        );

        ((Button) findViewById(R.id.pause)).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        videoPlayer.pause();
                    }
                }
        );

        ((Button) findViewById(R.id.stop)).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        videoPlayer.stopPlayback();
                        videoPlayer.resume();
                    }
                }
        );

        videoPlayer =  (VideoView)findViewById(R.id.MainVideoPlayer);
        if (video==null)
            video = Uri.parse( "android.resource://" + getPackageName() + "/" + R.raw.home);

        videoPlayer.setVideoURI(video);
        MediaController mediaController = new MediaController(this);
        videoPlayer.setMediaController(mediaController);
        mediaController.setMediaPlayer(videoPlayer);
    }

    public void backClick(View v) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        EndActivity();
    }

    public void EndActivity(){
        VideoActivity.this.finish();
    }

    public void play(View view){
        videoPlayer.start();
    }

    public void pause(View view){
        videoPlayer.pause();
    }
    public void stop(View view){
        videoPlayer.stopPlayback();
        videoPlayer.resume();
    }

    public void SetVideo(Uri v) {
        video = v;
    }

    public void SetVideo(String v) {
        video = Uri.parse("android.resource://" + getPackageName() + "/raw/" + v);
    }
}
