package com.example.sergio.ticked;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class programa extends AppCompatActivity {

    EditText codAl,nomAl;
    Button regAl,mosAl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_programa);

        codAl = (EditText) findViewById(R.id.codAl);
        nomAl = (EditText) findViewById(R.id.nomAl);

        regAl = (Button) findViewById(R.id.modAl);
        mosAl = (Button) findViewById(R.id.mosAl);

    }

    public  void mostrarClick(View view) {
        startActivity(new Intent(programa.this, ListadoProg.class));
    }

    public void  registrarClick(View view) {
        guardar(codAl.getText().toString(), nomAl.getText().toString());
    }

    private void guardar(String nombre, String codigo){
        BaseHelper helper = new BaseHelper(this, "Demo", null, 1);
        SQLiteDatabase db = helper.getWritableDatabase();
        try {
            ContentValues c = new ContentValues();
            c.put("CodigoPrograma",nombre);
            c.put("NombrePrograma", codigo);
            db.insert("PROGRAMAS",null,c);
            db.close();
            Toast.makeText(this, "Registro insertado", Toast.LENGTH_SHORT).show();


        }catch (Exception e){
            Toast.makeText(this, "Error" + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}
