package com.example.sergio.ticked;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class Ticket extends AppCompatActivity {

    //EditText codAl,nomAl;
    public static int count = 1;
    Button regAl,mosAl;
    Spinner spAlumno,spPrograma;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket);
        ArrayList<String> array;
        ArrayList<String> array2;

        array = listar("Personas","NombreAlumno");
        array2 = listar("Programas","NombrePrograma");

        Spinner s = (Spinner) findViewById(R.id.spinnerAlumno);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, array);
        s.setAdapter(adapter);

        Spinner s2 = (Spinner) findViewById(R.id.spinnerPrograma);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, array2);
        s2.setAdapter(adapter2);


        spAlumno = (Spinner) findViewById(R.id.spinnerAlumno);
        spPrograma = (Spinner) findViewById(R.id.spinnerPrograma);

        regAl = (Button) findViewById(R.id.Modificar);
        mosAl = (Button) findViewById(R.id.Borrar);

    }
    public  void mostrarClick(View view) {
        startActivity(new Intent(Ticket.this, ListadoTic.class));
    }

    public void  registrarClick(View view) {
        guardar(spAlumno.getSelectedItem().toString(), spPrograma.getSelectedItem().toString());
    }

    private void guardar(String alumno, String programa){
        BaseHelper helper = new BaseHelper(this, "Demo", null, 1);
        SQLiteDatabase db = helper.getWritableDatabase();


        try {
            ContentValues c = new ContentValues();
            c.put("IDT",count);
            c.put("Alumno", alumno);
            c.put("Programa",programa);

            db.insert("TICKETS",null,c);
            db.close();
            Toast.makeText(this, "Registro insertado", Toast.LENGTH_SHORT).show();
            count++;

        }catch (Exception e){
            Toast.makeText(this, "Error" + e.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }

    private ArrayList<String> listar(String table, String column1){
        ArrayList<String> datos  = new ArrayList<String>();

        BaseHelper helper = new BaseHelper(this, "Demo", null, 1);
        SQLiteDatabase db = helper.getReadableDatabase();
        String sql = "select "+column1+" from "+table;
        Cursor c = db.rawQuery(sql,null);

        if(c.moveToFirst()){
            do{
                String linea = c.getString(0)+" ";
                datos.add(linea);

            }while (c.moveToNext());
        }
        db.close();
        return datos;
    }
}