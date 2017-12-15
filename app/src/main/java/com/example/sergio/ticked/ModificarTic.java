package com.example.sergio.ticked;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;

public class ModificarTic extends AppCompatActivity {

    EditText codAl,nomAl;
    Button modificar,borrar;
    public Spinner spAlumno,spPrograma;
    int id;
    String alumno,programa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar_tic);


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


        Bundle b = getIntent().getExtras();
        if(b!=null){
            id = b.getInt("Idt");
            alumno= b.getString("Alumno");
            programa = b.getString("Programa");

        }

        spAlumno = (Spinner) findViewById(R.id.spinnerAlumno);
        spPrograma = (Spinner) findViewById(R.id.spinnerPrograma);

        /*---Enviar al Spinner
        codAl.setText(codigo);
        nomAl.setText(nombre);*/

        modificar = (Button) findViewById(R.id.Modificar);
        borrar = (Button) findViewById(R.id.Borrar);
        borrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Eliminar(id);
                onBackPressed();
            }
        });

        modificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                modificar(spAlumno.getSelectedItem().toString(),spPrograma.getSelectedItem().toString());
                onBackPressed();
            }
        });
    }

    private void modificar(String alumno, String programa){
        int key = consultar("TICKETS",alumno);
        BaseHelper helper = new BaseHelper(this, "Demo", null, 1);
        SQLiteDatabase db = helper.getWritableDatabase();
        String sql = "update TICKETS set  Alumno= '"+alumno+"', Programa='"+programa+"' where IDT="+key;
        db.execSQL(sql);
        //ContentValues cv = new ContentValues();
        //cv.put("CodigoAlumno",codigo);
        //cv.put("NombreAlumno",nombre);

        // db.update("PERSONAS",cv,"CodigoAlumno="+codigo,null);
        db.close();
        System.out.print(key);
    }

    private void Eliminar(int Id){

        BaseHelper helper = new BaseHelper(this, "Demo", null, 1);
        SQLiteDatabase db = helper.getWritableDatabase();
        String sql = "delete from Tickets where IDT="+Id;
        db.execSQL(sql);
        db.close();
    }
    private int consultar(String table, String column1){
        ArrayList<String> datos  = new ArrayList<String>();
        int linea=-1;
        BaseHelper helper = new BaseHelper(this, "Demo", null, 1);
        SQLiteDatabase db = helper.getReadableDatabase();
        String sql = "select IDT from "+table+" where Alumno="+alumno;
        Cursor c = db.rawQuery(sql,null);

        if(c.moveToFirst()){
            do{
                linea = c.getInt(0);

            }while (c.moveToNext());
        }
        db.close();
        return linea;
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
