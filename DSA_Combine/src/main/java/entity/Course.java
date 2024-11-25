/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import adt.SortedDoublyLinkedList;
import adt.SortedLinkedListInterface;
import java.io.Serializable;

/**
 *
 * @author Wong Xin Yee
 */
public class Course implements Comparable<Course>, Serializable {
    private String courseCode;
    private String title;
    private int creditHour;
    private String semester;
    private String faculty;
    private String status;
    private SortedLinkedListInterface<Programme> programmeList  = new SortedDoublyLinkedList<>();

    public Course(String courseCode, String title, int creditHour, String semester, String faculty, String status) {
        this.courseCode = courseCode;
        this.title = title;
        this.creditHour = creditHour;
        this.semester = semester;
        this.faculty = faculty;
        this.status = status;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getCreditHour() {
        return creditHour;
    }

    public void setCreditHour(int creditHour) {
        this.creditHour = creditHour;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    
    public SortedLinkedListInterface<Programme> getProgrammeList() {
        return programmeList;
    }

    public void setProgrammeList(SortedLinkedListInterface<Programme> programmeList) {
        this.programmeList = programmeList;
    }

    public int compareTo(Course c) {
        return courseCode.compareTo(c.courseCode);
    }

    @Override
    public String toString() {
        return String.format("%-10s %-70s %-10d %-10s %-15s %-25s", courseCode, title, creditHour, semester, faculty, status);
    }
    
    
}
