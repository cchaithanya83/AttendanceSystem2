package com.example.attendancesystem;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Student {
    private String username;
    private String usn;
    private String subject;
    private String email;
    private String semester;
    private String studentClass;
    private UniqueListExample example;

    // Empty constructor (required for Firebase Firestore)
    public Student() {
        example = new UniqueListExample();
    }

    // Constructor to initialize the student object
    public Student(String username, String usn, String subject, String email, String semester, String studentClass) {
        this.username = username;
        this.usn = usn;
        this.subject = subject;
        this.email = email;
        this.semester = semester;
        this.studentClass = studentClass;
        this.example = new UniqueListExample();
        example.addItem(subject);
    }

    // Getters and setters for the student's attributes
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsn() {
        return usn;
    }

    public void setUsn(String usn) {
        this.usn = usn;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public String getStudentClass() {
        return studentClass;
    }

    public void setStudentClass(String studentClass) {
        this.studentClass = studentClass;
    }

    public class UniqueListExample {
        private List<String> uniqueList;
        private Set<String> uniqueSet;

        public UniqueListExample() {
            uniqueList = new ArrayList<>();
            uniqueSet = new HashSet<>();
        }

        public void addItem(String item) {
            if (!uniqueSet.contains(item)) {
                uniqueList.add(item);
                uniqueSet.add(item);
            }
        }

        public List<String> getUniqueList() {
            return uniqueList;
        }
    }
}
