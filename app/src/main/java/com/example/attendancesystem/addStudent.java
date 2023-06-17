package com.example.attendancesystem;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.attendancesystem.R;
import com.example.attendancesystem.Student;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class addStudent extends AppCompatActivity {

    private EditText usernameEditText;
    private EditText usnEditText;
    private EditText subjectEditText;
    private EditText emailEditText;
    private EditText semesterEditText;
    private EditText classEditText;
    private Button addStudentButton;

    private FirebaseFirestore db;
    private CollectionReference studentsCollection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);

        // Initialize Firestore and the students collection reference
        db = FirebaseFirestore.getInstance();

        // Initialize views
        usernameEditText = findViewById(R.id.etUsername);
        usnEditText = findViewById(R.id.etUSN);
        subjectEditText = findViewById(R.id.etSubject);
        emailEditText = findViewById(R.id.etEmail);
        semesterEditText = findViewById(R.id.etSemester);
        classEditText = findViewById(R.id.etClass);
        addStudentButton = findViewById(R.id.btnAddStudent);

        // Set click listener for add student button
        addStudentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the entered student details
                String username = usernameEditText.getText().toString();
                String usn = usnEditText.getText().toString();
                String subject = subjectEditText.getText().toString();
                String email = emailEditText.getText().toString();
                int semester = Integer.parseInt(semesterEditText.getText().toString());
                String studentClass = classEditText.getText().toString();

                // Create a Student object
                Student student = new Student(username, usn, subject, email, semester, studentClass);
                String id=email+"$"+subject;

                // Add the student to the database

                studentsCollection = db.collection(subject);


                studentsCollection.document().collection(email).add(student)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                // Display a toast message to indicate successful addition
                                String message = "Student added!\nUsername: " + username + "\nUSN: " + usn +
                                        "\nSubject: " + subject + "\nEmail: " + email +
                                        "\nSemester: " + semester + "\nClass: " + studentClass;
                                Toast.makeText(addStudent.this, message, Toast.LENGTH_SHORT).show();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                // Display a toast message to indicate failure

                                Toast.makeText(addStudent.this, "Failed to add student", Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });
    }
}
