package com.example.sampleview;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

/**
 * 버튼 클릭 이벤트
 */
public class SubActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);
        
        // button이 View 보다 상위 개념
        Button btn2 = findViewById(R.id.Btn2);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("로그", "값");
                
            }
        });

        findViewById(R.id.Btn3).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                // Intent를 통해서 activity 및 데이터도 넘겨줄 수 있다.
                Intent intent = new Intent(SubActivity.this, MainActivity.class);
                intent.putExtra("start", "재생");
                intent.putExtra("stop", "정지");
                startActivity(intent);

            }
        });
    }

    public void Btn1Click(View v){
        // context : 띄울 화면
        Toast toast = Toast.makeText(this, "버튼 1 클릭", Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.END | Gravity.BOTTOM, 10, 10);
        toast.show();
    }
}