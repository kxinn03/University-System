/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import adt.SortedDoublyLinkedList;
import adt.SortedLinkedListInterface;
/**
 *
 * @author Ting Xin Yi
 */
public class Student implements Comparable<Student> {
  private String studentIDLevel = "";
    private static int nextIDNO = 1001;
    private static int numberInfront = 0;
    private String name = "";
    private int age;
    private double cgpa = 0.0;
    
    private SortedLinkedListInterface <Course> courseList;
    private TutorialGroup tutorialGroupList;
    
    public Student() {
    }

    public Student(String name, int age, String studentIDLevel, double cgpa, boolean newStudent) {
        if(newStudent){
        this.name = name;
        this.age = age;
        this.studentIDLevel = studentIDLevel + nextIDNO++;
        this.cgpa = cgpa;
        this.courseList = new SortedDoublyLinkedList<>();
        }else{
        this.name = name;
        this.age = age;
        this.studentIDLevel = studentIDLevel;
        this.cgpa = cgpa;
        this.courseList = new SortedDoublyLinkedList<>();
        
        }      
    }

    public SortedLinkedListInterface<Course> getCourseList() {
        return courseList;
    }

    public void setCourseList(SortedLinkedListInterface<Course> courseList) {
        this.courseList = courseList;
    }
    
    public String getStudentIDLevel() {
        return studentIDLevel;
    }

    public void setStudentIDLevel(String studentIDLevel) {
        this.studentIDLevel = studentIDLevel + nextIDNO++;
    }

    
    public static void setNumberInfront(int numberInfront) {
        Student.numberInfront = numberInfront;
    }

    public int getNumberInfront() {
        return numberInfront++;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public static int getNextIDNO() {
        return nextIDNO;
    }

    public static void setNextIDNO(int nextIDNO) {
        Student.nextIDNO = nextIDNO;
    }

    public double getCgpa() {
        return cgpa;
    }

    public void setCgpa(double cgpa) {
        this.cgpa = cgpa;
    }

    public double getGpa() {
        return cgpa;
    }

    public void setGpa(double gpa) {
        this.cgpa = gpa;
    }
   
    public TutorialGroup getTutorialGroupList() {
        return tutorialGroupList;
    }

    public void setTutorialGroupList(TutorialGroup tutorialGroupList) {
        this.tutorialGroupList = tutorialGroupList;
    }
    
    public String toString() {
        // Define the width for each field
        int nameWidth = 25; 
        int ageWidth = 6;  
        int idWidth = 12; 
        int gpaWidth = 3;

        // Format the fields with the specified width
        String formattedName = String.format("%-" + nameWidth + "s", name);
        String formattedAge = String.format("%-" + ageWidth + "s", age);
        String formattedID = String.format("%-" + idWidth + "s", studentIDLevel);  
        String formattedGPA = String.format("%-" + gpaWidth + "s", cgpa); 
        return formattedName +  formattedAge +  formattedID + formattedGPA;
    }
  
    @Override
    public int compareTo(Student s){
        return name.compareTo(s.name); 
    }
}