package model;

import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;

public class Customer  {

    private String StudentsID;
    private String name;
    private String semester;
    private String courseName;

    public Customer(String StudentsID, String name, String semester, String courseName) {
        this.StudentsID = StudentsID;
        this.name = name;
        this.semester = semester;
        this.courseName = courseName;
    }

    public String getStudentsID() {
        return StudentsID;
    }

    public void setStudentsID(String StudentsID) {
        this.StudentsID = StudentsID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    @Override
    public String toString() {
        return  "StudentsID=" + StudentsID + ", name=" + name + ", semester=" + semester + ", courseName=" + courseName ;
    }

}