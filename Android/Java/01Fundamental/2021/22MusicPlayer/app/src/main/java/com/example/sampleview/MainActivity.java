package com.example.sampleview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView tv;
    private Button btn_play;
    private Button btn_stop;
    private MediaPlayer mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = findViewById(R.id.tv);
        btn_play = findViewById(R.id.btn_play);
        btn_stop = findViewById(R.id.btn_stop);

        // 재생버튼 클릭 시
        btn_play.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {
                mp = MediaPlayer.create(MainActivity.this, R.raw.music);
                mp.start();
                tv.setText("Playing");
            }
        });

        // 정지 버튼 클릭 시
        btn_stop.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(mp.isPlaying()) {
                    mp.stop();
                    mp.reset();
                    tv.setText("Stopped");
                }
            }
        });


    }

    // activity가 종료될 때 시점
    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 미디어 플레이어 자원 해제
        if(mp != null){
            mp.release();
            mp = null;
        }
    }
}