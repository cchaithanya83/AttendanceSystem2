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

public class teacher_signup extends AppCompatActivity {
    private EditText usernameEditText;
    private EditText passwordEditText;
    private EditText specialKeyEditText;
    private Button signupButton;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_signup2);

        // Initialize views
        usernameEditText = findViewById(R.id.txt_TaecherSignupUn);
        passwordEditText = findViewById(R.id.txt_TeacherSignupPass);
        specialKeyEditText = findViewById(R.id.txt_TaecherSignupUn2);
        signupButton = findViewById(R.id.btn_signup);

        // Initialize FirebaseAuth instance
        mAuth = FirebaseAuth.getInstance();

        // Set click listener for signup button
        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the entered data
                String username = usernameEditText.getText().toString();
                String password = passwordEditText.getText().toString();
                String specialKey = specialKeyEditText.getText().toString();

                // Check if special key matches
                if (specialKey.equals("12345678")) {
                    // Create a Bundle to store the data
                    Bundle dataBundle = new Bundle();
                    dataBundle.putString("username", username);
                    dataBundle.putString("password", password);

                    // Display a toast message with the entered data
                    String message = "Username: " + username + "\nPassword: " + password;
                    Toast.makeText(teacher_signup.this, message, Toast.LENGTH_SHORT).show();

                    // Create user with email and password
                    mAuth.createUserWithEmailAndPassword(username, password)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        // Sign in success, update UI with the signed-in user's information
                                        FirebaseUser user = mAuth.getCurrentUser();
                                        // Update UI
                                    } else {
                                        // If sign in fails, display a message to the user.
                                        Toast.makeText(teacher_signup.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });

                    // Start the MainActivity
                    Intent intent = new Intent(teacher_signup.this, MainActivity.class);
                    intent.putExtras(dataBundle);
                    startActivity(intent);
                } else {
                    // Display an error toast message
                    Toast.makeText(teacher_signup.this, "Incorrect special key", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        // Update UI
    }
}
