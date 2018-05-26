package com.example.user.a4lab;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getMediaFiles();
    }

    public void videoCick(View v) {

        Intent intent = new Intent(this, VideoActivity.class);
        startActivity(intent);
    }

    public void getMediaFiles() {
        File mediadirectory = new File("android.resource://" + getPackageName() + "/raw");
        final File[] media = mediadirectory.listFiles();
        ArrayList<TextView> list = new ArrayList<TextView>();
        for (int i = 0; i < media.length; i++) {
            TextView tv = new TextView(this);
            tv.setText(media[i].getName());
            tv.setHeight(100);
            tv.setPadding(3, 3, 3, 3);
            tv.setTextSize(20);

            if (media[i].getName().contains(".mp4")) {
                try {
                    tv.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View v) {
                            String name = ((TextView) v).getText().toString();
                            ChangeToVideo(name);
                        }
                    });

                    ((LinearLayout) findViewById(R.id.video_holder)).addView(tv);
                } catch (Exception e) {
                    String exc = e.toString();
                }
            } else {
                try {
                    tv.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View v) {
                            String name = ((TextView) v).getText().toString();
                            ChangeToMusik(name);
                        }
                    });

                    ((LinearLayout) findViewById(R.id.musik_holder)).addView(tv);
                } catch (Exception e) {
                    String exc = e.toString();
                }
            }
        }
    }

    public void ChangeToVideo(String name) {
        Intent intentApp = new Intent(MainActivity.this,
                VideoActivity.class);

        MainActivity.this.startActivity(intentApp);
        MainActivity.this.finish();
    }

    public void ChangeToMusik(String name) {
        Intent intentApp = new Intent(MainActivity.this,
                MusikActivity.class);

        MainActivity.this.startActivity(intentApp);
        MainActivity.this.finish();
    }
}