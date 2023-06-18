package com.example.attendancesystem;


import java.util.List;

public class Subject {
    private String subjectName;
    private List<String> studentNames;

    public Subject(String subjectName, List<String> studentNames) {
        this.subjectName = subjectName;
        this.studentNames = studentNames;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public List<String> getStudentNames() {
        return studentNames;
    }
}
