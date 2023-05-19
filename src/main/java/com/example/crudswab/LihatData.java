package com.example.crudswab;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class LihatData extends AppCompatActivity {

    protected Cursor cursor;
    DataHelper dbHelper;
    Button ton1;
    EditText text1, text2, text3, text4, text5, text6, text7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lihatdata);

        dbHelper = new DataHelper(this);
        text1 = (EditText) findViewById(R.id.nik);
        text2 = (EditText) findViewById(R.id.nama);
        text3 = (EditText) findViewById(R.id.tgllahir);
        text4 = (EditText) findViewById(R.id.kelamin);
        text5 = (EditText) findViewById(R.id.notelp);
        text6 = (EditText) findViewById(R.id.alamat);
        text7 = (EditText) findViewById(R.id.hasilswab);


        ton1 = (Button) findViewById(R.id.btn_kembali);

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String A = getIntent().getStringExtra("nama");
        cursor = db.rawQuery("SELECT * FROM dataswab1 WHERE nama = '" + A + "'",null);
        cursor.moveToFirst();

        if(cursor.getCount()>0){
            cursor.moveToPosition(0);
            text1.setText(cursor.getString(0).toString());
            text2.setText(cursor.getString(1).toString());
            text3.setText(cursor.getString(2).toString());
            text4.setText(cursor.getString(3).toString());
            text5.setText(cursor.getString(4).toString());
            text6.setText(cursor.getString(5).toString());
            text7.setText(cursor.getString(6).toString());

        }

        ton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


    }
}