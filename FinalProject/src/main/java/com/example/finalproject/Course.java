package com.example.finalproject;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer courseId;
    private String courseName;
    private String courseNumber;
    private String capacity;
    private String year;
    private String semester;
    private String pid;

    public Integer getCourseId() {
        return courseId;
    }
    public void setCourseId(Integer id) {
        this.courseId = id;
    }


    public String getCourseName() {
        return courseName;
    }
    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }


    public String getCourseNumber() {
        return courseNumber;
    }
    public void setCourseNumber(String courseNumber) {
        this.courseNumber = courseNumber;
    }


    public String getCapacity() {
        return capacity;
    }
    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }


    public String getYear() {
        return year;
    }
    public void setYear(String year) {
        this.year = year;
    }


    public String getSemester() {
        return semester;
    }
    public void setSemester(String semester) {
        this.semester = semester;
    }


    public String getPid() {
        return pid;
    }
    public void setPid(String pid) {
        this.pid = pid;
    }

}
