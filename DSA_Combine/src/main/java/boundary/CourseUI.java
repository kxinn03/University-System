/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package boundary;

import adt.SortedLinkedListInterface;
import entity.Course;
import java.util.Scanner;
import dao.DAO;
import entity.Programme;
import java.time.format.DateTimeFormatter;  
import java.time.LocalDateTime;  
/**
 *
 * @author Wong Xin Yee
 */
public class CourseUI {
    Scanner scanner = new Scanner(System.in);
    

    public int getMenuChoice() {
        System.out.println(" _____                            ___  ___                                                  _     _____       _                   _                 ");
        System.out.println("/  __ \\                           |  \\/  |                                                 | |   /  ___|     | |                 | |                ");
        System.out.println("| /  \\/ ___  _   _ _ __ ___  ___  | .  . | __ _ _ __   __ _  __ _  ___ _ __ ___   ___ _ __ | |_  \\ `--. _   _| |__  ___ _   _ ___| |_ ___ _ __ ___  ");
        System.out.println("| |    / _ \\| | | | '__/ __|/ _ \\ | |\\/| |/ _` | '_ \\ / _` |/ _` |/ _ \\ '_ ` _ \\ / _ \\ '_ \\| __|  `--. \\ | | | '_ \\/ __| | | / __| __/ _ \\ '_ ` _ \\ ");
        System.out.println("| \\__/\\ (_) | |_| | |  \\__ \\  __/ | |  | | (_| | | | | (_| | (_| |  __/ | | | | |  __/ | | | |_  /\\__/ / |_| | |_) \\__ \\ |_| \\__ \\ ||  __/ | | | | |");
        System.out.println(" \\____/\\___/ \\__,_|_|  |___/\\___| \\_|  |_/\\__,_|_| |_|\\__,_|\\__, |\\___|_| |_| |_|\\___|_| |_|\\__| \\____/ \\__,_|_.__/|___/\\__, |___/\\__\\___|_| |_| |_|");
        System.out.println("                                                             __/ |                                                       __/ |                      ");
        System.out.println("                                                            |___/                                                       |___/                       ");

        System.out.println("\nCOURSE MANAGEMENT SUBSYSTEM MAIN MENU");
        System.out.println("1. List all courses");
        System.out.println("2. Add new courses");
        System.out.println("3. Add a programme to courses");
        System.out.println("4. Remove a programme from a course");
        System.out.println("5. Add a new course to programmes");
        System.out.println("6. Remove a course from a programme");
        System.out.println("7. Search courses offered in a semester");
        System.out.println("8. Amend course details for a programme");
        System.out.println("9. List courses taken by different faculties");
        System.out.println("10. List all courses for a programme");
        System.out.println("11. List all programmes for a course");
        System.out.println("12. Course Enrollment and Faculty Summary Report");
        System.out.println("13. Course Offering and Credit Hours Summary Report");
        System.out.println("0. Quit");
        System.out.print("Enter choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine();
        System.out.println();
        return choice;
    }

    public void listAllCourse(String outputStr) {
        System.out.println("\nList of Courses:\n");
        courseHeader();
        System.out.println(outputStr);
    }

    public void listAllProgrammes(String outputStr) {
        DAO p = new DAO();
        SortedLinkedListInterface<Programme> programmeList = p.getAllProgrammes();
        System.out.println("\nList of Programme:\n" + outputStr);

    }

    public void printCourseDetails(Course course) {
        System.out.println("Course Details");
        System.out.println("Course Code:" + course.getCourseCode());
        System.out.println("Course Title: " + course.getTitle());
        System.out.println("Credit Hours: " + course.getCreditHour());
        System.out.println("Semester: " + course.getSemester());
        System.out.println("Falculty: " + course.getFaculty());
        System.out.println("Status: " + course.getStatus());
    }

    public void editCourseDetails(Course course) {
        System.out.println("Course Details:");
        System.out.println("1.Course Title: " + course.getTitle());
        System.out.println("2.Credit Hours: " + course.getCreditHour());
        System.out.println("3.Status: " + course.getStatus());
    }

    public String inputCourseCode() {
        System.out.print("Enter Course Code: ");
        String courseCode = scanner.nextLine();
        return courseCode.toUpperCase();
    }

    public String inputCourseTitle() {
        System.out.print("Enter Course Title: ");
        String title = scanner.nextLine();
        return title.toUpperCase();
    }

  
    public int inputCreditHour() {
        int creditHour;
        while (true) {
            System.out.print("Enter Credit Hours: ");
            try {
                creditHour = Integer.parseInt(scanner.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid integer for credit hours.");
            }
        }
        return creditHour;
    }

    public String inputSemester() {
        System.out.print("Enter Semester(exp: Y1S1): ");
        String semester = scanner.nextLine();
        return semester.toUpperCase();
    }

    public String inputFaculty() {
        System.out.print("Enter Faculty(exp: FOCS FAFB(can be more than one faculty)): ");
        String faculty = scanner.nextLine();
        return faculty.toUpperCase();
    }

    public String inputStatus() {
        System.out.print("Enter Status(exp: main, elective, main/elective): ");
        String status = scanner.nextLine();
        return status.toUpperCase();
    }
    
    public void getAddChoice() {
        System.out.println("1. Add by selecting existing course");
        System.out.println("2. Add by adding new course");
    }
    
    public void courseHeader() {
        System.out.printf("%3s %-10s %30s %45s %9s %10s %15s\n", "No.", "Course Code", "Course Title", "Credit Hour", "Semester", "Faculty", "Status");
    }
    
    public void programmeHeader() {
        System.out.printf("%-16s %40s %53s %24s\n", "Programme Code", "Programme Name", "Faculty", "Max No of Tutorial Grp");
    }
    
    public void reportHeader() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
        LocalDateTime now = LocalDateTime.now();  
        System.out.println("==============================================================================================================================================");
        System.out.printf("%-30s %63s %-10s\n\n", "TUNKU ABDUL RAHMAN UNIVERSITY OF MANAGEMENT AND TECHNOLOGY", "Generated at:", dtf.format(now));
        System.out.printf("%-30s\n", "Ground Floor, Bangunan Tan Sri Khaw Kai Boh (Block A), ");
        System.out.printf("%-30s\n", "Jalan Genting Kelang, Setapak,");
        System.out.printf("%-30s\n", "53300 Kuala Lumpur, Federal Territory of Kuala Lumpur.\n");
        System.out.printf("%80s\n", "COURSE MANAGEMENT SUBSYSTEM");
        System.out.printf("%80s\n", "---------------------------");
    }
    
    public void summaryReport1ListHeader() {
        System.out.printf("%-4s %-35s %-40s %-10s %-1s %-10s %20s\n", "No.", "Course Code", "Course Title", "Programme Enrolled", "/", "Programme Offered", "Faculty Offered");
        System.out.printf("%-4s %-15s %-60s %-14s %20s\n", "---", "-----------", "---------------------------------------------------------", "--------------------------------------", "---------------");
    }
    
    public void summaryReport2ListHeader() {
        System.out.printf("%-4s %-40s %-52s %-15s %-10s %13s\n", "No.", "Course Code", "Course Title", "Status", "Credit Hours", "Semester");
        System.out.printf("%-4s %-15s %-60s %18s %16s %13s\n", "---", "-----------", "----------------------------------------------------------------------", "---------------", "------------", "--------");
    }
    
    public void reportFooter() {
        System.out.printf("%85s\n", "END OF THE COURSE SUMMARY REPORT");
        System.out.println("==============================================================================================================================================");
    }
    
    public void line() {
        System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------");
    }
    
    

}
