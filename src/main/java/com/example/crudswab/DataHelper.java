package com.example.crudswab;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class DataHelper extends SQLiteOpenHelper {
    // membuat database

    private static final String DATABASE_NAME = "swab1.db";
    private static final int DATABASE_VERSION = 1;
    public DataHelper(Context context){

        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    //membuat tabel

    @Override
    public void onCreate(SQLiteDatabase db){
        String sql = "CREATE TABLE dataswab1(nik text unique, nama text null, tgl text null, jk text null, notelp text null, alamat text null, hasilswab text null);";
        Log.d("Data","onCreate: "+sql);
        db.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2){

    }

}
