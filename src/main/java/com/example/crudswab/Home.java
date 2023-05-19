package com.example.crudswab;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class Home extends AppCompatActivity {
    String[] daftar;
    ListView ListView01;
    Menu menu;
    protected Cursor cursor;
    DataHelper dbcenter;
    public static Home ma;


    FloatingActionButton addfab;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);

        addfab = findViewById(R.id.fab);

        addfab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent inten = new Intent(Home.this, InputData.class);
                startActivity(inten);
            }
        });

        ma = this;
        dbcenter = new DataHelper(this);
        RefreshList(); //sebuah fungsi


    }

    public void RefreshList() {
        SQLiteDatabase db = dbcenter.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM dataswab1", null);
        daftar = new String[cursor.getCount()];
        cursor.moveToFirst();
        for (int cc =0; cc<cursor.getCount(); cc++){
            cursor.moveToPosition(cc);
            daftar[cc] = cursor.getString(1).toString();

        }
        ListView01 = (ListView) findViewById(R.id.listswab);
        ListView01.setAdapter(new ArrayAdapter(this, android.R.layout.simple_list_item_1, daftar));
        ListView01.setSelected(true);
        ListView01.setBackgroundColor(Color.LTGRAY);

        ListView01.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                final String selection = daftar[i];
                final CharSequence[] dialogitem = {"Lihat Biodata", "Update Biodata", "Kirim Data via WA", "Hapus Biodata"};
                AlertDialog.Builder builder = new AlertDialog.Builder(Home.this);
                builder.setTitle("Pilihan");
                builder.setItems(dialogitem, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int item) {
                        switch (item){
                            case 0:
                                Intent inten1 = new Intent(Home.this, LihatData.class);
                                inten1.putExtra("nama", selection); //koding membawa data yang diklik
                                startActivity(inten1);
                                break;
                            case 1:
                                Intent inten2 = new Intent(Home.this, UpdateData.class);
                                inten2.putExtra("nama", selection);
                                startActivity(inten2);
                                break;
                            case 2:
                                Intent inten3 = new Intent(Home.this, KirimData.class);
                                inten3.putExtra("nama", selection);
                                startActivity(inten3);
                                break;
                            case 3:
                                SQLiteDatabase db = dbcenter.getWritableDatabase();
                                db.execSQL("DELETE from dataswab1 where nama = '" + selection + "'");
                                RefreshList();
                                break;

                        }
                    }
                });
                builder.create().show();

            }
        });
        ((ArrayAdapter) ListView01.getAdapter()).notifyDataSetInvalidated();

    }
}

