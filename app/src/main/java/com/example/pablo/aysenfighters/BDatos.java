package com.example.pablo.aysenfighters;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by pablo on 26/11/2017.
 */

public class BDatos extends SQLiteOpenHelper {

    String tabla="CREATE TABLE TORNEOS(ID INTERGER PRIMARY KEY, TORNEO TEXT, USUARIO TEXT)";
    public BDatos(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL(tabla);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("drop table TORNEOS");
        db.execSQL(tabla);
    }
}

























