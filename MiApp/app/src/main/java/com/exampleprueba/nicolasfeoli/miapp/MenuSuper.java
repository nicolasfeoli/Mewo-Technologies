package com.exampleprueba.nicolasfeoli.miapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.exampleprueba.nicolasfeoli.miapp.ui.login.LoginActivity;

public class MenuSuper extends AppCompatActivity {

    Toolbar toolbar;
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_super);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Menu");
        getSupportActionBar().setSubtitle("Subtitulo");

        Intent myIntent = getIntent(); // gets the previously created intent
        String given_name = myIntent.getStringExtra("givenName"); // will return "FirstKeyValue"
        //Log.d("PRUEBA", given_name);
        editText = (EditText) findViewById(R.id.name);
        editText.setText(given_name);
        //String secondKeyName= myIntent.getStringExtra("secondKeyName"); // will return "SecondKeyValue"
/*
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

 */


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
                Log.d("LOGOUT","LOGOUT");
                Intent intent = new Intent(MenuSuper.this, LoginActivity.class);
                startActivity(intent);
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
