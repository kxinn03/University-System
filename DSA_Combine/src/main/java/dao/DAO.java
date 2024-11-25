/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import adt.SortedDoublyLinkedList;
import adt.SortedLinkedListInterface;
import entity.Course;
import entity.Student;
import entity.Programme;
import entity.TutorialGroup;

/**
 *
 * @author Wong Xin Yee, Ting Xin Yi, Lee Kar Xin
 */
public class DAO {
    public static SortedLinkedListInterface<Course> getAllCourses() {
        SortedLinkedListInterface<Course> courseList = new SortedDoublyLinkedList<>();

        courseList.add(new Course("BACS2063", "DATA STRUCTURES AND ALGORITHMS", 3, "Y1S3", "FOCS", "MAIN"));
        courseList.add(new Course("BHLA2263", "INTRODUCTION TO SHORT STORY", 3, "Y1S2", "FOCS FAFB", "ELECTIVE"));
        courseList.add(new Course("ABFT1023", "Taxation I", 3, "Y1S3", "FAFB", "MAIN"));
        courseList.add(new Course("BACS2023", "TAXATION I", 4, "Y2S2", "FOCS", "MAIN"));
        courseList.add(new Course("AACS1074", "PROGRAMMING CONCEPTS AND DESIGN I", 4, "Y1S1", "FOCS", "MAIN"));
        courseList.add(new Course("BBMF1813", "PRINCIPLES OF FINANCE", 3, "Y3S1", "FAFB", "MAIN"));
        courseList.add(new Course("ABFA1173", "Principles of Accounting", 2, "Y3S1", "FOCS FAFB", "MAIN/ELECTIVE"));
        courseList.add(new Course("BACS2003", "PRINCIPLES OF ACCOUNTING", 3, "Y3S1", "FOET FOCS", "MAIN/ELECTIVE"));
        courseList.add(new Course("BAFS1313", "INTRODUCTION TO FOOD SCIENCE", 4, "Y1S1", "FOAS", "MAIN"));
        courseList.add(new Course("KHHM2203", "FOOD AND BEVERAGE MANAGEMENT", 5, "Y1S1", "FOSH", "ELECTIVE"));
        return courseList;
    
    }
    
    public static SortedLinkedListInterface<Programme> getAllProgrammes() {
        SortedLinkedListInterface<Programme> programmeList = new SortedDoublyLinkedList<>();

        programmeList.add(new Programme("REI", "Bachelor of Information Systems (Honours) in Enterprise Information Systems", "FOCS", 10));
        programmeList.add(new Programme("RDS", "Bachelor of Computer Science (Honours) in Data Science", "FOCS", 25));
        programmeList.add(new Programme("RIS", "Bachelor of Information Technology (Honours) in Information Security", "FOCS", 30));
        programmeList.add(new Programme("RBX", "Bachelor of Business (Honours) in Business Analytics", "FAFB", 30));
        programmeList.add(new Programme("RME", "Bachelor of Mechanical Engineering with Honours", "FOET", 10));
        programmeList.add(new Programme("RFN", "Bachelor of Science (Hons) in Food Science", "FOAS", 15));
        programmeList.add(new Programme("DHM", "Diploma in Hotel Management", "FOSH", 25));
        programmeList.add(new Programme("DIS", "Diploma in Information Systems", "FOCS", 10));
        programmeList.add(new Programme("DBF", "Diploma in Banking and Finance", "FAFB", 15));
        programmeList.add(new Programme("DBA", "Diploma in Business Administration", "FAFB", 5));


        return programmeList;
    }
    
    public static SortedLinkedListInterface<Student> getAllStudent(){ 
        SortedLinkedListInterface<Student> studentList = new SortedDoublyLinkedList<>(); 
        studentList.add(new Student("CK", 20, "24WMR", 3.5 , true));
        studentList.add(new Student( "Ah Wong", 22, "24WMD", 0.0, true));
        studentList.add(new Student( "Karxin", 21, "24WMR", 2.5, true));
        studentList.add(new Student( "Kaiwah", 21, "24WMD", 0.0, true));
        studentList.add(new Student( "Ming king", 21, "24WMR", 4.0, true));
        studentList.add(new Student( "Fung Pin", 21, "24WMD", 0.0, true));
        studentList.add(new Student( "Kirthi", 20, "24WMR", 3.6 , true));
        studentList.add(new Student( "Muk zui", 22, "24WMD", 0.0, true));
        studentList.add(new Student( "Yik Seing", 21, "24WMR", 2.5, true));
        studentList.add(new Student( "Jit Liang", 21, "24WMD", 0.0, true));
        studentList.add(new Student( "Zi Jian", 21, "24WMR", 4.0, true));
        studentList.add(new Student( "Amirul", 21, "24WMD", 0.0, true));
        studentList.add(new Student( "Jonny", 20, "24WMR", 3.6 , true));
        studentList.add(new Student( "Cathrine", 22, "24WMD", 0.0, true));
        studentList.add(new Student( "Sophia", 21, "24WMR", 1.5, true));
        studentList.add(new Student( "Miru", 21, "24WMD", 0.0, true));
        studentList.add(new Student( "Mickey", 21, "24WMR", 4.1, true));
        studentList.add(new Student( "Donald", 21, "24WMD", 0.0, true));
        studentList.add(new Student( "John", 20, "24WMR", 2.5 , true));
        studentList.add(new Student( "Alice", 22, "24WMD", 0.0, true));
        studentList.add(new Student( "Bob", 21, "24WMR", 2.5, true));
        studentList.add(new Student( "Elyy", 21, "24WMD", 0.0, true));
        studentList.add(new Student( "Ali", 21, "24WMR", 2.0, true));
        studentList.add(new Student( "Emily", 21, "24WMD", 0.0, true));  
        return studentList;
    }
    
    public static SortedLinkedListInterface getAllTutorial(){
        SortedLinkedListInterface<TutorialGroup> tutorialGroupList = new SortedDoublyLinkedList<>();
        tutorialGroupList.add(new TutorialGroup("RSDG2", 2));
        tutorialGroupList.add(new TutorialGroup("REIG1", 1));
        tutorialGroupList.add(new TutorialGroup("RISG3", 3));
        return tutorialGroupList;
        
    }
    
//     public SortedLinkedListInterface assignStudentToCourse(){
//        SortedLinkedListInterface<Programme> programmeList = new SortedDoublyLinkedList<>();
//        SortedLinkedListInterface<TutorialGroup> tutorialGroupList = new SortedDoublyLinkedList<>();
//        SortedLinkedListInterface<Student> students = new SortedDoublyLinkedList<>();
//        
//        programmeList.getEntry(1).getTutorialGroupList().getEntry(1).getStudentList().getEntry(1).getCourseList().add("RIS", "Bachelor of Information Technology (Honours) in Information Security", "FOCS", 30);
//        
//        return programmeList;
//        return tutorialGroupList;
//        return students;
//     }
    
    
    
//    public static void hardcodeProgramme(ProgrammeCtrl controller) {
//        controller.addProgramme("RIS", "Information Security", "FOCS", 3);
//        controller.addProgramme("CS", "Computer Science", "FOCS", 4);
//        controller.addProgramme("AI", "Artificial Intelligence", "FOCS", 5);
//    }

//    public static void hardcodeStudents(ProgrammeCtrl controller) {
//      
//        controller.addStudent("1", "KX",2.0);
//        controller.addStudent("2", "Wong",3.0);
//        controller.addStudent("3", "Ting ",2.0);
//        controller.addStudent("4", "WongKW",3.5);
//        controller.addStudent("5", "FP",3.5);
//        controller.addStudent("6", "CK",2.7);
//        controller.addStudent("7", "MK",4.0);
//    }
    
}
