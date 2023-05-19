package com.example.crudswab;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class InputData extends AppCompatActivity {

    protected Cursor cursor;
    DataHelper dbHelper;
    Button ton1, ton2;
    EditText text1, text2, text3, text4, text5, text6;
    RadioButton rblaki, rbperempuan, rbid;
    RadioGroup rg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inputdata);

        dbHelper = new DataHelper(this);
        text1 = (EditText) findViewById(R.id.nik);
        text2 = (EditText) findViewById(R.id.nama);
        text3 = (EditText) findViewById(R.id.tgllahir);

        text5 = (EditText) findViewById(R.id.notelp);
        text6 = (EditText) findViewById(R.id.alamat);

        rblaki = findViewById(R.id.radioButtonlaki);
        rbperempuan = findViewById(R.id.radioButtonperempuan);
        rg = findViewById(R.id.radiogrup);

        ton1 = (Button) findViewById(R.id.btn_tambah);

        String p = "Proses";

        ton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int id = rg.getCheckedRadioButtonId();
                rbid = findViewById(id);

                SQLiteDatabase db = dbHelper.getWritableDatabase();
                db.execSQL("insert into dataswab1(nik, nama, tgl, jk, notelp, alamat, hasilswab) values('" + text1.getText().toString() + "', '" + text2.getText().toString() + "', '" + text3.getText().toString() + "', '" + rbid.getText().toString() + "', '" + text5.getText().toString() + "', '" + text6.getText().toString() + "', '"+ p +"') ");
                Toast.makeText(getApplicationContext(), "Berhasil", Toast.LENGTH_LONG).show();
                Home.ma.RefreshList();
                finish();

            }
        });

    }


}
