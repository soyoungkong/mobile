package com.example.chris;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView lv_test;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // (ListView) 타입 캐스팅. 최신 버젼 부터는 안 적어도 됨.
        lv_test = (ListView) findViewById(R.id.lv_test);
        List<String> data = new ArrayList<>();

        // 어댑터로 리스트와 리스트 뷰를 연결해줌
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, data);
        lv_test.setAdapter(adapter);
        data.add("kim");
        data.add("kong");
        data.add("cha");
        data.add("choi");
        data.add("park");
        adapter.notifyDataSetChanged();
    }
}