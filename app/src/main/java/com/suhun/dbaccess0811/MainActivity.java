package com.suhun.dbaccess0811;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private String tag = MainActivity.class.getSimpleName();
    private TextView view_result;
    private EditText view_name, view_tel, view_birthday, view_delete;
    private MyDBOpenHelper myDBOpenHelper;
    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initDB();
    }

    private void initView(){
        view_result = findViewById(R.id.lid_queryResult);
        view_name = findViewById(R.id.lid_nameInput);
        view_tel = findViewById(R.id.lid_telInput);
        view_birthday = findViewById(R.id.lid_birthdayInput);
        view_delete = findViewById(R.id.lid_deleteInput);
    }
    private void initDB(){
        myDBOpenHelper = new MyDBOpenHelper(this, "suhunDB", null, 1);
        db = myDBOpenHelper.getWritableDatabase();
    }

    public void queryFun(View view){
        Cursor cursor = db.query("cust", null, null, null, null, null, null);
        StringBuffer stringBuffer = new StringBuffer();
        while(cursor.moveToNext()){
            String id = cursor.getString(cursor.getColumnIndexOrThrow("cid"));
            String name = cursor.getString(cursor.getColumnIndexOrThrow("cname"));
            String tel = cursor.getString(cursor.getColumnIndexOrThrow("ctel"));
            String birthday = cursor.getString(cursor.getColumnIndexOrThrow("cbirthday"));
            String resultString = String.format("%s:%s:%s:%s\n", id, name, tel, birthday);
            stringBuffer.append(resultString);
        }
        view_result.setText(stringBuffer);
    }
    public void insertFun(View view){
        ContentValues views = new ContentValues();
        views.put("cname", view_name.getText().toString());
        views.put("ctel", view_tel.getText().toString());
        views.put("cbirthday", view_birthday.getText().toString());
        db.insert("cust", null, views);
        view_name.setText("");
        view_tel.setText("");
        view_birthday.setText("");
    }

    public void dateSelectFun(View view){
        DatePickerDialog dialog = new DatePickerDialog(this, DatePickerDialog.THEME_DEVICE_DEFAULT_DARK,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        String pickerResult = String.format("%s-%s-%s", year, month, dayOfMonth);
                        view_birthday.setText(pickerResult);
                    }
                }, 2021, 00, 01);
        dialog.show();
    }

    public void deleteFun(View view){
        db.delete("cust", "cid=?", new String[]{view_delete.getText().toString()});
    }
}