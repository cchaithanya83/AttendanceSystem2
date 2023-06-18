package com.example.attendancesystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;
public class mainTeacherPage extends AppCompatActivity  {


    private Button addStudentButton;
    private Button attendancePerStudentButton;
    private Button viewAttendanceButton;
    private Button viewStudentButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_teacher_page);
        addStudentButton=findViewById(R.id.button4);
        attendancePerStudentButton = findViewById(R.id.button6);
        viewAttendanceButton = findViewById(R.id.button3);
        viewStudentButton = findViewById(R.id.button5);
        addStudentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle add student functionality
                // Add your logic here to navigate to the add student page

                // Display a toast message

                Intent intent=new Intent(mainTeacherPage.this,addStudent.class);
                startActivity(intent);
                Toast.makeText(mainTeacherPage.this, "Add Student Clicked", Toast.LENGTH_SHORT).show();
            }
        });

        // Set click listener for attendance per student button
        attendancePerStudentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle attendance per student functionality
                // Add your logic here to navigate to the attendance per student page

                // Display a toast message
                Intent intent1=new Intent(mainTeacherPage.this,attendace_veiwer.class);
                startActivity(intent1);
                Toast.makeText(mainTeacherPage.this, "Attendance per Student Clicked", Toast.LENGTH_SHORT).show();
            }
        });

        // Set click listener for view attendance button
        viewAttendanceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle view attendance functionality
                // Add your logic here to navigate to the view attendance page

                // Display a toast message
                Intent intent=new Intent(mainTeacherPage.this,view_student.class);
                startActivity(intent);
                Toast.makeText(mainTeacherPage.this, "View Attendance Clicked", Toast.LENGTH_SHORT).show();
            }
        });

        // Set click listener for view student button
        viewStudentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle view student functionality
                // Add your logic here to navigate to the view student page

                // Display a toast message
                Intent intent=new Intent(mainTeacherPage.this,view_student.class);
                startActivity(intent);
                Toast.makeText(mainTeacherPage.this, "View Student Clicked", Toast.LENGTH_SHORT).show();
            }
        });

    }
}