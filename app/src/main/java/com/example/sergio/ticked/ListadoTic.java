package com.example.sergio.ticked;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ListadoTic extends AppCompatActivity {

    ListView listView;
    ArrayList<String> listado;

    @Override
    protected void onPostResume() {
        super.onPostResume();
        CargarListado();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado_tic);

        listView = (ListView) findViewById(R.id.listView);
        CargarListado();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long id) {
                Toast.makeText(ListadoTic.this, listado.get(i),Toast.LENGTH_SHORT).show();
                String alumno = (listado.get(i).split(" ")[0]);
                String programa = listado.get(i).split(" ")[1];
                //String nombre = listado.get(i).split(" ")[2];
                Intent intent = new Intent(ListadoTic.this, ModificarTic.class);
                //  intent.putExtra("Id",clave);
                intent.putExtra("Alumno",alumno);
                intent.putExtra("Programa",programa);

                startActivity(intent);
            }
        });
        if(getSupportActionBar()!= null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private void CargarListado(){
        listado = ListaPersonas();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,listado);
        listView.setAdapter(adapter);
    }

    private ArrayList<String> ListaPersonas(){
        ArrayList<String> datos  = new ArrayList<String>();

        BaseHelper helper = new BaseHelper(this, "Demo", null, 1);
        SQLiteDatabase db = helper.getReadableDatabase();
        String sql = "select * from Tickets";
        Cursor c = db.rawQuery(sql,null);

        if(c.moveToFirst()){
            do{
                String linea = c.getInt(0)+". " + c.getString(1)+" " + c.getString(2);
                datos.add(linea);

            }while (c.moveToNext());
        }
        db.close();
        return datos;
    }
}
