package com.exampleprueba.nicolasfeoli.miapp;

import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Toast;

public class AgregarUsuario extends AppCompatActivity implements View.OnClickListener {

    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_usuario);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        //getSupportActionBar().setSubtitle("Subtitulo");

        Button agregarButton = (Button)findViewById(R.id.btnAgregar);
        agregarButton.setOnClickListener((View.OnClickListener) this);
        Button cancelarButton = (Button)findViewById(R.id.btnCancelar);
        cancelarButton.setOnClickListener((View.OnClickListener) this);

        //agregarButton.getBackground().setColorFilter(0x008CBA, PorterDuff.Mode.MULTIPLY);

    }
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_menu_super,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        String msg = "";
        switch (item.getItemId())
        {
            case R.id.logout:
                msg = "Logout";
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void onClick(View v)
    {
        switch (v.getId()) {
            case  R.id.btnAgregar: {
                // do something for button 1 click
                Toast.makeText(getApplicationContext(),"Agregar", Toast.LENGTH_LONG).show();
                break;
            }
            case R.id.btnCancelar: {
                // do something for button 2 click
                Toast.makeText(getApplicationContext(),"Cancelar", Toast.LENGTH_LONG).show();
                break;
            }

            //.... etc
        }
    }

    /*
    public void onItemSelected(AdapterView<?> parent, View view,
                               int pos, long id) {
        // An item was selected. You can retrieve the selected item using
        // parent.getItemAtPosition(pos)
    }

    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
    }
     */
}
