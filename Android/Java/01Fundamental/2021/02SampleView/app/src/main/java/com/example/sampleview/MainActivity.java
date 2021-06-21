package com.example.sampleview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView tv;
    MediaPlayer mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//      setContentView(new SampleView(this));   // view setting

        Intent intent = getIntent();
        String start = intent.getExtras().getString("start");
        String stop = intent.getExtras().getString("stop");

/**
 *  mp3 재생
 */
        setContentView(R.layout.activity_main);
        mp = MediaPlayer.create(this, R.raw.preview);
        mp.setLooping(false);
        tv = (TextView) this.findViewById(R.id.main);
        tv.setOnClickListener(new Button.OnClickListener(){

            @Override
            public void onClick(View v) {
                if(!mp.isPlaying()){
                    mp.start();
                    tv.setText(stop);
                }else{
                    mp.pause();
                    tv.setText(start);
                }
            }
        });
    }
}