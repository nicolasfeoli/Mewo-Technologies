package com.exampleprueba.nicolasfeoli.miapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.exampleprueba.nicolasfeoli.miapp.R;

public class EditUser extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveUserData();
                goBackAfterSave();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void saveUserData(){
        //user.setFirstName(firstName.getText().toString);
        return;
    }

    private void goBackAfterSave(){
        Intent goBack = new Intent();
        goBack.putExtra("INTENT_EXTRA_GOING_BACK", "didEdit");
        setResult(RESULT_OK, goBack);
        finish();
    }
}
