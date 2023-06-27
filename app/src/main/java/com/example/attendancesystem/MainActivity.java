package com.example.attendancesystem;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Button btnLogin;
    private Button btnSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnLogin = findViewById(R.id.btn_signup);
        btnSignUp = findViewById(R.id.btn_login);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start TeacherLoginActivity
                Intent intent = new Intent(MainActivity.this,mainTeacherPage.class);
                startActivity(intent);

                // Display a toast message
                Toast.makeText(MainActivity.this, "Login button clicked", Toast.LENGTH_SHORT).show();
            }
        });

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start TeacherSignUpActivity
                Intent intent = new Intent(MainActivity.this,teacher_signup.class);
                startActivity(intent);

                // Display a toast message
                Toast.makeText(MainActivity.this, "Sign Up button clicked", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
