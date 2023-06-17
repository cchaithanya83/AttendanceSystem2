package com.example.attendancesystem;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.security.auth.Subject;

public class view_student extends AppCompatActivity {

    private Button viewStudentsButton;
    private RecyclerView recyclerViewSubjects;
    private TextView tvNoSubjects;

    private FirebaseFirestore db;
    private CollectionReference subjectsCollection;
    private List<Subject> subjectList;
    private SubjectAdapter subjectAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_student);

        db = FirebaseFirestore.getInstance();
        subjectsCollection = db.collection("subjects");

        viewStudentsButton = findViewById(R.id.btnViewStudents);
        recyclerViewSubjects = findViewById(R.id.recyclerViewStudents);
        tvNoSubjects = findViewById(R.id.tvNoStudents);

        // Set up the RecyclerView
        recyclerViewSubjects.setLayoutManager(new LinearLayoutManager(this));
        subjectList = new ArrayList<>();
        subjectAdapter = new SubjectAdapter(subjectList);
        recyclerViewSubjects.setAdapter(subjectAdapter);

        // Set click listener for View Students button
        viewStudentsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fetchSubjects();
            }
        });
    }

    private void fetchSubjects() {
        subjectList.clear();

        subjectsCollection
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        for (DocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                            String subjectName = documentSnapshot.getId();
                            List<String> studentNames = new ArrayList<>();

                            // Retrieve the list of student names for the subject
                            List<String> studentIds = (List<String>) documentSnapshot.get("students");
                            if (studentIds != null) {
                                fetchStudentNames(studentIds, studentNames, new FetchStudentNamesCallback() {
                                    @Override
                                    public void onStudentNamesFetched() {
                                        // Create a Subject object with subject name and student names
                                        Subject subject = new Subject(subjectName, studentNames);
                                        subjectList.add(subject);
                                        subjectAdapter.notifyDataSetChanged();
                                    }
                                });
                            } else {
                                // Create a Subject object with subject name and an empty student names list
                                Subject subject = new Subject(subjectName, studentNames);
                                subjectList.add(subject);
                                subjectAdapter.notifyDataSetChanged();
                            }
                        }

                        if (subjectList.isEmpty()) {
                            recyclerViewSubjects.setVisibility(View.GONE);
                            tvNoSubjects.setVisibility(View.VISIBLE);
                        } else {
                            recyclerViewSubjects.setVisibility(View.VISIBLE);
                            tvNoSubjects.setVisibility(View.GONE);
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(view_student.this, "Failed to fetch subjects", Toast.LENGTH_SHORT).show();
                        // Handle error while fetching subjects
                    }
                });
    }

    private void fetchStudentNames(List<String> studentIds, List<String> studentNames, FetchStudentNamesCallback callback) {
        CollectionReference studentsCollection = db.collection("students");

        for (String studentId : studentIds) {
            studentsCollection.document(studentId)
                    .get()
                    .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                        @Override
                        public void onSuccess(DocumentSnapshot documentSnapshot) {
                            String studentName = documentSnapshot.getString("username");
                            studentNames.add(studentName);

                            // Check if all student names have been fetched
                            if (studentNames.size() == studentIds.size()) {
                                callback.onStudentNamesFetched();
                            }
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(view_student.this, "Failed to fetch student names", Toast.LENGTH_SHORT).show();
                            // Handle error while fetching student names
                        }
                    });
        }
    }

    private interface FetchStudentNamesCallback {
        void onStudentNamesFetched();
    }
}


