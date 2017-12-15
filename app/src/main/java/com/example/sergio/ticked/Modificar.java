package com.example.sergio.ticked;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Modificar extends AppCompatActivity {

    EditText codAl,nomAl;
    Button modAl,borAl;
    int id;
    String nombre,codigo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar);

        Bundle b = getIntent().getExtras();
        if(b!=null){
            //id = b.getInt("Id");
            codigo= b.getString("CodigoAlumno");
            nombre = b.getString("NombreAlumno");

        }

        codAl = (EditText) findViewById(R.id.codAl);
        nomAl = (EditText) findViewById(R.id.nomAl);

        codAl.setText(codigo);
        nomAl.setText(nombre);
        modAl = (Button) findViewById(R.id.modAl);
        borAl = (Button) findViewById(R.id.borAl);
        borAl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Eliminar(codigo);
                onBackPressed();
            }
        });

        modAl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                modificar(nomAl.getText().toString(),codAl.getText().toString());
                onBackPressed();
            }
        });
    }

    private void modificar( String nombre, String codigo){
        BaseHelper helper = new BaseHelper(this, "Demo", null, 1);
        SQLiteDatabase db = helper.getWritableDatabase();
        String sql = "update PERSONAS set  NombreAlumno='"+nombre+"' where CodigoAlumno="+codigo;
        db.execSQL(sql);
        //ContentValues cv = new ContentValues();
        //cv.put("CodigoAlumno",codigo);
        //cv.put("NombreAlumno",nombre);

       // db.update("PERSONAS",cv,"CodigoAlumno="+codigo,null);
        db.close();
    }

    private void Eliminar(String Id){
        BaseHelper helper = new BaseHelper(this, "Demo", null, 1);
        SQLiteDatabase db = helper.getWritableDatabase();
        String sql = "delete from Personas where CodigoAlumno="+Id;
        db.execSQL(sql);
        db.close();
    }
}
