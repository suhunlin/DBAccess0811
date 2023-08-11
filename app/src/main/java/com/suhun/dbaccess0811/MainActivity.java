package com.suhun.dbaccess0811;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private String tag = MainActivity.class.getSimpleName();
    private TextView view_result;
    private EditText view_name, view_tel, view_birthday;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView(){
        view_result = findViewById(R.id.lid_queryResult);
        view_name = findViewById(R.id.lid_nameInput);
        view_tel = findViewById(R.id.lid_telInput);
        view_birthday = findViewById(R.id.lid_birthdayInput);
    }

    public void queryFun(View view){

    }
    public void insertFun(View view){

    }
}