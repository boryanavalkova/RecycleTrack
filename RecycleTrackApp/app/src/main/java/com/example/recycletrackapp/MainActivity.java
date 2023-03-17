package com.example.recycletrackapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //called when log in button is tapped
    public void login(View view){
        EditText username = findViewById(R.id.usernamein);
        String usernameStr = username.getText().toString();
        EditText password = findViewById(R.id.passwordin);
        String passwordStr = password.getText().toString();

        //check if input details are correct
        //created for specific required user eee
        if(TextUtils.isEmpty(username.getText()) && TextUtils.isEmpty(password.getText())){
            username.setError("Enter Username!");
            password.setError("Enter Password!");
        }else if (usernameStr.equals("eee") & passwordStr.equals("eee")){
            Intent intent = new Intent(getApplicationContext(), Logwaste.class);
            startActivity(intent);
        } else {
            username.setError("Incorrect Username!");
            password.setError("Incorrect Password!");
        }
    }
}