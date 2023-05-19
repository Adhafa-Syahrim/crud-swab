package com.example.crudswab;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class UpdateData extends AppCompatActivity {

    protected Cursor cursor;
    DataHelper dbHelper;
    Button ton1, ton2;
    EditText text1, text2, text3, text4, text5, text6, text7;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.updatedata);

        dbHelper = new DataHelper(this);
        text1 = (EditText) findViewById(R.id.nik);
        text2 = (EditText) findViewById(R.id.nama);
        text3 = (EditText) findViewById(R.id.tgllahir);
        text4 = (EditText) findViewById(R.id.kelamin);
        text5 = (EditText) findViewById(R.id.notelp);
        text6 = (EditText) findViewById(R.id.alamat);
        text7 = (EditText) findViewById(R.id.hasilswab);

        ton1 = (Button) findViewById(R.id.btn_kembali);
        ton2 = (Button) findViewById(R.id.btn_update);

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

        ton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                db.execSQL("UPDATE dataswab1 set nik='"+
                        text1.getText().toString() +"', nama='"+
                        text2.getText().toString() +"', tgl='"+
                        text3.getText().toString() +"', jk='"+
                        text4.getText().toString() +"', notelp='"+
                        text5.getText().toString() +"', alamat='"+
                        text6.getText().toString() +"', hasilswab='"+
                        text7.getText().toString() +"' WHERE nik='"+
                        text1.getText().toString() +"'");
                Toast.makeText(getApplicationContext(), "Berhasil", Toast.LENGTH_LONG).show();
                Home.ma.RefreshList();
                finish();
            }
        });

    }

}