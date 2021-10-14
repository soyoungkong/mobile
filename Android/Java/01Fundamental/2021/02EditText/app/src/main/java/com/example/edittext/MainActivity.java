package com.example.edittext;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    // 기능(동적 컨트롤)은 java Activity에서, 요소와 디자인은 xml에서 잡는다.
    // 가능하면 변수명은 id값과 같게 한다 (헷갈리지 않게)
    EditText et_id;
    Button btn_test;

    // 어플을 실행 했을 떄 첫번째 생명 주기
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et_id = findViewById(R.id.et_id);
        btn_test = findViewById(R.id.btn_test);

        // 버튼 클릭 시의 이벤트 
        btn_test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et_id.setText("클릭됨");
            }
        });
    }
}