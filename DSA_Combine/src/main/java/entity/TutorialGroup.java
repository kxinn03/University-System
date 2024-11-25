/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import adt.SortedDoublyLinkedList;
import adt.SortedLinkedListInterface;

/**
 *
 * @author Lee Kar Xin
 */
public class TutorialGroup implements Comparable<TutorialGroup> {

    private String id;
    private int maxStudents;
    private int currentNumStud;
    private SortedLinkedListInterface<Student> studentList;

    public TutorialGroup(String id, int numStudents) {
        this.id = id;
        this.maxStudents = numStudents;
        this.studentList = new SortedDoublyLinkedList<>();
    }

    public SortedLinkedListInterface<Student> getStudentList() {
        return studentList;
    }

    public void setStudentList(SortedLinkedListInterface<Student> studentList) {
        this.studentList = studentList;
    }

    public void addStudents(Student s) {
        if (s != null && !studentList.contains(s)) {
            studentList.add(s);
        }
    }

    public String getId() {
        return id;
    }

    public int getMaxStudents() {
        return maxStudents;
    }

    public int getCurrentNumStud() {
        return currentNumStud;
    }

    public void setCurrentNumStud(int currentNumStud) {
        this.currentNumStud = currentNumStud;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setMaxStudents(int maxStudents) {
        this.maxStudents = maxStudents;
    }

    @Override
    public int compareTo(TutorialGroup T) {
        return id.compareTo(T.id);
    }
    

    @Override
    public String toString() {
        return "\t" + id + "\t\t\t"
                + maxStudents
                + "\t\t\t  " + currentNumStud;
    }
}