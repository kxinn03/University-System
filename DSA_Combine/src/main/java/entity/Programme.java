/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import adt.SortedDoublyLinkedList;
import adt.SortedLinkedListInterface;

/**
 *
 * @author Wong Xin Yee, Lee Kar Xin
 */
public class Programme implements Comparable<Programme> {
    private String programmeCode;
    private String name;
    private String faculty;
    private int maxTutorialGrp;
    private SortedLinkedListInterface<Course> courseList  = new SortedDoublyLinkedList<>();
    private SortedLinkedListInterface<TutorialGroup> tutorialGroupList = new SortedDoublyLinkedList<>();
    private SortedLinkedListInterface<Student> studentList;
    
    public Programme(String progammeCode, String name, String faculty, int maxTutorialGrp) {
        this.programmeCode = progammeCode;
        this.name = name;
        this.faculty = faculty;
        this.maxTutorialGrp = maxTutorialGrp;
        this.tutorialGroupList = new SortedDoublyLinkedList<>();
    } 

    public String getProgrammeCode() {
        return programmeCode;
    }

    public void setProgammeCode(String progammeCode) {
        this.programmeCode = progammeCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFaculty() {
        return faculty;
    }

    public void setFalculty(String falculty) {
        this.faculty = falculty;
    }

    public int getMaxTutorialGrp() {
        return maxTutorialGrp;
    }

    public void setMaxTutorialGrp(int maxTutorialGrp) {
        this.maxTutorialGrp = maxTutorialGrp;
    }
    
     public int compareTo(Programme p) {
        return programmeCode.compareTo(p.programmeCode);
    }

    public SortedLinkedListInterface<Course> getCourseList() {
        return courseList;
    }

    public void setCourseList(SortedLinkedListInterface<Course> courseList) {
        this.courseList = courseList;
    }
    
    public SortedLinkedListInterface<TutorialGroup> getTutorialGroupList() {
        return tutorialGroupList;
    }

    public void setTutorialGroupList(SortedLinkedListInterface<TutorialGroup> tutorialGroupList) {
        this.tutorialGroupList = tutorialGroupList;
    }

    public void addTutorialGroup(TutorialGroup T) {
        if (T != null) {
            if (!tutorialGroupList.contains(T)) {
                tutorialGroupList.add(T);
            }
        }
    }

    public SortedLinkedListInterface<Student> getStudentList() {
        return studentList;
    }

    public void setStudentList(SortedLinkedListInterface<Student> studentList) {
        this.studentList = studentList;
    }
    

    @Override
    public String toString() {
        return String.format("%-12s %-88s %-10s %10d", programmeCode, name, faculty, maxTutorialGrp);
    }
     
}

