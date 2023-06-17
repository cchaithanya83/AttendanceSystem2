package com.example.attendancesystem;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class teacher_login extends AppCompatActivity {
    private EditText usernameEditText;
    private EditText passwordEditText;
    private Button loginButton;
    private Button forgotPasswordButton;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_login);

        // Initialize views
        usernameEditText = findViewById(R.id.txt_TeacherLoginUname);
        passwordEditText = findViewById(R.id.txt_TeacherLoginPass);
        loginButton = findViewById(R.id.btn_login2);
        forgotPasswordButton = findViewById(R.id.btn_login3);
        mAuth = FirebaseAuth.getInstance();

        // Set click listener for login button
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the entered username and password
                String username = usernameEditText.getText().toString();
                String password = passwordEditText.getText().toString();

                // Perform login authentication
                mAuth.signInWithEmailAndPassword(username, password)
                        .addOnCompleteListener(teacher_login.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    FirebaseUser user = mAuth.getCurrentUser();

                                    if (startsWithNumbers(username)) {
                                        Toast.makeText(teacher_login.this, "Login as Student", Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(teacher_login.this,studentpage.class);
                                        startActivity(intent);
                                    } else {
                                        Toast.makeText(teacher_login.this, "Login as Teacher", Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(teacher_login.this,mainTeacherPage.class);
                                        startActivity(intent);
                                    }
                                } else {
                                    // If sign in fails, display a message to the user.
                                    Toast.makeText(teacher_login.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });

        // Set click listener for forgot password button
        forgotPasswordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle the forgot password functionality
                // Add your logic here to implement the forgot password flow
                Intent intent = new Intent(teacher_login.this, studentpage.class);
                startActivity(intent);
                Toast.makeText(teacher_login.this, "Forgot Password Clicked", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private boolean startsWithNumbers(String text) {
        String[] parts = text.split("@");
        if (parts.length >= 2) {
            String domain = parts[1];
            return domain.equalsIgnoreCase("sjec.ac.in");
        }
        return false;
    }
}
