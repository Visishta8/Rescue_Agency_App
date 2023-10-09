package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;

public class loginpage extends AppCompatActivity {

    @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_loginpage);

            // Initialize your UI elements and set up event listeners
            // ...

            // Example: Set up an onClick listener for the Sign Up button
            findViewById(R.id.SignUpButton).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Create an Intent to navigate to the SignUpActivity
                    Intent signUpIntent = new Intent(loginpage.this, signuppage.class);
                    startActivity(signUpIntent);
                }
            });
        }
    }
