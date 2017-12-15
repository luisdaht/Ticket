package com.example.sergio.ticked;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Menu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }
    public void lanzarAL(View view){
            Intent i = new Intent(this, MainActivity.class);
            startActivity(i);
    }
    public void lanzarPro(View view){
        Intent i = new Intent(this, programa.class);
        startActivity(i);
    }

    public void lanzarTic(View view){
        Intent i = new Intent(this, Ticket.class);
        startActivity(i);
    }

}
