package com.example.sergio.ticked;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by SERGIO on 11/12/2017.
 */

public class BaseHelper extends SQLiteOpenHelper {

    String tabla = "CREATE TABLE PERSONAS(CodigoAlumno text not null, NombreAlumno text not null, PRIMARY KEY('CodigoAlumno'))";
    String tabla1 = "CREATE TABLE PROGRAMAS(CodigoPrograma text not null, NombrePrograma text not null, PRIMARY KEY('CodigoPrograma'))";
    String tabla2 = "CREATE TABLE TICKETS(IDT INTEGER PRIMARY KEY AUTOINCREMENT, Alumno TEXT NOT NULL, Programa TEXT NOT NULL)";
    public BaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(tabla);
        sqLiteDatabase.execSQL(tabla1);
        sqLiteDatabase.execSQL(tabla2);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists personas");
        //sqLiteDatabase.execSQL(tabla);
        sqLiteDatabase.execSQL("CREATE TABLE PERSONAS(CodigoAlumno text not null, NombreAlumno text not null)");
        sqLiteDatabase.execSQL("drop table if exists programas");
        sqLiteDatabase.execSQL("CREATE TABLE PROGRAMAS(CodigoPrograma text not null, NombrePrograma text not null)");
        //sqLiteDatabase.execSQL(tabla1);

       sqLiteDatabase.execSQL("drop table if exists tickets");
       sqLiteDatabase.execSQL("CREATE TABLE TICKETS(IDT INTEGER PRIMARY KEY AUTOINCREMENT, Alumno TEXT NOT NULL, Programa TEXT NOT NULL)");
    }
}
