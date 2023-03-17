package com.example.recycletrackapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Logwaste extends AppCompatActivity {

    //Initialize attributes for submit form
    Button submit;
    EditText numRecycle;
    EditText numGeneral;
    TextView displayRecycle;
    TextView displayGeneral;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logwaste);



        numRecycle = findViewById(R.id.inputRecycled);
        numGeneral = findViewById(R.id.inputGeneral);
        submit = findViewById(R.id.submitBtn);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogModel logmodel;
                try {
                    logmodel = new LogModel(-1,
                            Integer.parseInt(numRecycle.getText().toString()),
                            Integer.parseInt(numGeneral.getText().toString()));

                    Toast.makeText(getApplicationContext(), logmodel.toString(), Toast.LENGTH_SHORT).show();
                } catch (Exception exception){
                    numRecycle.setError("Enter Valid Data!");
                    numGeneral.setError("Enter Valid Data!");
                    Toast.makeText(getApplicationContext(), "Incorrect Details.", Toast.LENGTH_SHORT).show();
                    logmodel = new LogModel(-1, 0, 0);
                }

                DBHelper dataBaseHelper = new DBHelper(Logwaste.this);
                boolean isAdded = dataBaseHelper.added(logmodel);
                Toast.makeText(getApplicationContext(), "Success = " + isAdded, Toast.LENGTH_SHORT).show();
            }
        });


        //Navigation set-up
        BottomNavigationView bottomNavigationView = findViewById(R.id.navigation);
        bottomNavigationView.setSelectedItemId(R.id.logwaste);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.dashboard:
                        startActivity(new Intent(getApplicationContext(), Dashboard.class));
                        overridePendingTransition(0, 0);
                        return true;

                    case R.id.analytics:
                        startActivity(new Intent(getApplicationContext(), Analytics.class));
                        overridePendingTransition(0, 0);
                        return true;

                    case R.id.help:
                        startActivity(new Intent(getApplicationContext(), Help.class));
                        overridePendingTransition(0, 0);
                        return true;

                    case R.id.logwaste:
                        return true;
                }
                return false;
            }
        });
    }
}