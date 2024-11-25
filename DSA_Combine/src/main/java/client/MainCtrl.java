/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package client;

import adt.SortedDoublyLinkedList;
import adt.SortedLinkedListInterface;
import boundary.TutorialGroupUI;
import dao.DAO;
import java.util.Scanner;
import entity.Course;
import entity.Programme;
import entity.Student;
import entity.TutorialGroup;

/**
 *
 * @author Wong Xin Yee, Ting Xin Yi, Lee Kar Xin
 */
public class MainCtrl {
    public static SortedLinkedListInterface<Course> courseList  = new SortedDoublyLinkedList<>();
    public static SortedLinkedListInterface<Programme> programmeList  = new SortedDoublyLinkedList<>();
    public static SortedLinkedListInterface<Student> students = new SortedDoublyLinkedList<>();
    public static SortedLinkedListInterface<TutorialGroup> tutorialGroupList = new SortedDoublyLinkedList<>();
    
    public static void main(String[] args) {
        courseList = DAO.getAllCourses();
        programmeList = DAO.getAllProgrammes();
        students = DAO.getAllStudent();
        Scanner scanner = new Scanner(System.in);

        boolean exitSystem = false; 

        do {
            System.out.println("  _    _ _   _ _______      ________ _____   _____ _____ _________     __   _______     _______ _______ ______ __  __ ");
            System.out.println(" | |  | | \\ | |_   _\\ \\    / /  ____|  __ \\ / ____|_   _|__   __\\ \\   / /  / ____\\ \\   / / ____|__   __|  ____|  \\/  |");
            System.out.println(" | |  | |  \\| | | |  \\ \\  / /| |__  | |__) | (___   | |    | |   \\ \\_/ /  | (___  \\ \\_/ / (___    | |  | |__  | \\  / |");
            System.out.println(" | |  | | . ` | | |   \\ \\/ / |  __| |  _  / \\___ \\  | |    | |    \\   /    \\___ \\  \\   / \\___ \\   | |  |  __| | |\\/| |");
            System.out.println(" | |__| | |\\  |_| |_   \\  /  | |____| | \\ \\ ____) |_| |_   | |     | |     ____) |  | |  ____) |  | |  | |____| |  | |");
            System.out.println("  \\____/|_| \\_|_____|   \\/   |______|_|  \\_\\_____/|_____|  |_|     |_|    |_____/   |_| |_____/   |_|  |______|_|  |_|");
            System.out.println("\t\t\t\t----------------------------------------------");
            System.out.println("\t\t\t\t\t\t  MAIN MENU");
            System.out.println("\t\t\t\t----------------------------------------------");
            System.out.println("");
            System.out.println("\t\t\t\t1. Course Management Subsystem");
            System.out.println("\t\t\t\t2. Tutorial Group Management Subsystem");
            System.out.println("\t\t\t\t3. Student Registration Management Subsystem");
            System.out.println("\t\t\t\t0. Quit\n");
            System.out.print("\t\t\t\tEnter choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch(choice) {
                case 0:
                    System.out.println("\nExiting system");
                    exitSystem = true;
                    break;
                case 1:
                    CourseCtrl courseCtrl = new CourseCtrl();
                    courseCtrl.runCourseCtrl(); 
                    break;
                case 2:
                    TutorialGroupUI tutorialGroupUI = new TutorialGroupUI();
                    tutorialGroupUI.displayMenu();
                    break;
                case 3:
                    StudentCtrl studentCtrl = new StudentCtrl();
                    studentCtrl.performStudent();
                    break;
                default:
                    System.out.println("\nInvalid choice");
            }
        } while (!exitSystem); 
    }
    
}
