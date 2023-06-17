package com.example.attendancesystem;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class forgot extends AppCompatActivity {

    private EditText emailEditText;
    private Button resetPasswordButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot);

        // Initialize views
        emailEditText = findViewById(R.id.etEmail);
        resetPasswordButton = findViewById(R.id.btnResetPassword);

        // Set click listener for reset password button
        resetPasswordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the entered email
                String email = emailEditText.getText().toString();

                // Perform password reset
                // Add your logic here to handle the password reset process

                // Display a toast message
                String message = "Password reset initiated for email: " + email;
                Toast.makeText(forgot.this, message, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
