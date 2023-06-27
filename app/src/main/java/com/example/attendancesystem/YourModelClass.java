package com.example.attendancesystem;


public class YourModelClass {
    private String username;
    private String usn;
    private String semester;



    public YourModelClass() {
        // Default constructor (required for Firebase)
    }

    public YourModelClass(String username, String usn, String semester) {
        this.username = username;
        this.usn = usn;
        this.semester = semester;

    }

    // Getters and setters

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getusn() {
        return usn;
    }

    public void setusn(String usn) {
        this.usn = usn;
    }

    public String getSemester() {  return semester;    }

    public void setsem(String semester) {
        this.semester = semester;
    }

}

