package com.example.user.a4lab;

import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

public class MusikActivity extends AppCompatActivity {
    AudioManager musikPlayer;
    private static String name;
    private static Uri musik;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        ((TextView) findViewById(R.id.musicName)).setText(name);
    }

    public void backClick(View v) {

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void EndActivity(){
        MusikActivity.this.finish();
    }

    public void play(View view){
       // musikPlayer
    }

    public void pause(View view){
        //musikPlayer.pause();
    }
    public void stop(View view){
        //musikPlayer.;
    }

    public void SetVideo(Uri v) {
        musik = v;
    }

    public void SetVideo(String v) {
        musik = Uri.parse("android.resource://" + getPackageName() + "/raw/" + v);
    }

}
