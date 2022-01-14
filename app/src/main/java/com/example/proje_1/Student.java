package com.example.proje_1;

import java.io.Serializable;

public class Student implements Serializable {

    private String name;
    private String studentID;
    public Boolean getStudentRoll() {
        return studentRoll;
    }

    public void setStudentRoll(Boolean studentRoll) {
        this.studentRoll = studentRoll;
    }

    private Boolean studentRoll=false;

    public String getName(){
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public String getStudentID() {
        return studentID;
    }
}
