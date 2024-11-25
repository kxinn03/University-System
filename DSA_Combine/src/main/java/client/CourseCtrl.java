/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package client;

import adt.SortedDoublyLinkedList;
import adt.SortedLinkedListInterface;
import boundary.CourseUI;
import entity.Course;
import entity.Programme;
import java.util.Scanner;

/**
 *
 * @author Wong Xin Yee
 */
public class CourseCtrl {
    public static SortedLinkedListInterface<Course> courseList  = MainCtrl.courseList;
    public static SortedLinkedListInterface<Programme> programmeList  = MainCtrl.programmeList;
    private CourseUI courseUI = new CourseUI();
    
    
    public void runCourseCtrl() {
        Scanner input = new Scanner(System.in);
        int choice = 0;
        do {
          choice = courseUI.getMenuChoice();
          switch(choice) {
            case 0:
                System.out.println("\nExiting Course Management Subsystem...");
                break;
            case 1:
                courseUI.listAllCourse(getAllCourse());
                System.out.print("\nPress <ENTER> key to go back to Course Management Subsystem Main Menu...");
                input.nextLine();
                break;
            case 2:
                addNewCourse(courseList);
                courseUI.listAllCourse(getAllCourse());
                System.out.print("\nPress <ENTER> key to go back to Course Management Subsystem Main Menu...");
                input.nextLine();
                break;
            case 3:
                addProgrammeToCourse(programmeList, courseList);
              break;
            case 4:   
                removeProgrammeFromCourse(programmeList, courseList);
              break;
            case 5:
                addCourseToProgramme(courseList, programmeList);
            break;
            case 6:
                removeCourseFromProgramme(courseList, programmeList);
            break;
            case 7:
                searchCourseInSemester(courseList);
            break;
            case 8:
                amendCourseDetailsForProgramme(courseList, programmeList);
            break;
            case 9:
                listAllCourseByFaculty(courseList);
            break;
            case 10:
                listAllCourseForProgramme(courseList, programmeList);           
            break;
            case 11:
                listAllProgrammeForCourse(programmeList, courseList);           
            break;
            case 12:
                summaryReport1(courseList, programmeList);
            break;
            case 13:
                summaryReport2(courseList, programmeList);
            break;
            default:
                System.out.println("\nInvalid choice");
          } 
        } while (choice != 0);
    }
    
public void addProgrammeToCourse(SortedLinkedListInterface<Programme> p, SortedLinkedListInterface<Course> c) {
    Scanner input = new Scanner(System.in);
    boolean continueAdding = true;

    do {
        int selectedCourse, selectedProgramme;
        courseUI.listAllCourse(getAllCourse());
        System.out.print("Please select the course to which you want to add a programme (exp: 1/2/3): \n>> ");
        selectedCourse = input.nextInt();
        input.nextLine();
        if (selectedCourse >= 0 && selectedCourse <= c.getNumberOfEntries()) { 
            Course course = c.getEntry(selectedCourse);

            courseUI.listAllProgrammes(getAllProgramme());
            System.out.print("Enter the number of the programme to add: ");
            selectedProgramme = input.nextInt();
            input.nextLine();

            if (selectedProgramme >= 0 && selectedProgramme <= p.getNumberOfEntries()) {
                while (course.getProgrammeList().contains(p.getEntry(selectedProgramme))) {
                    System.out.println("the selected programme already exists in the course.");
                    System.out.print("Please enter another programme: ");
                    selectedProgramme = input.nextInt();
                    input.nextLine();
                }
                while(!course.getFaculty().contains(p.getEntry(selectedProgramme).getFaculty())) {
                    System.out.println("This programme is not allowed to add to the selected course, this programme is only available for course that offered " + p.getEntry(selectedProgramme).getFaculty());
                    System.out.print("Please enter another programme: ");
                    selectedProgramme = input.nextInt();
                    input.nextLine();
                     while (course.getProgrammeList().contains(p.getEntry(selectedProgramme))) {
                        System.out.println("the selected programme already exists in the course.");
                        System.out.print("Please enter another programme: ");
                        selectedProgramme = input.nextInt();
                        input.nextLine();
                    }
                }
                Programme programme = p.getEntry(selectedProgramme);

                
                course.getProgrammeList().add(programme);
                System.out.println("Programme added successfully.");
                programme.getCourseList().add(course);
                System.out.println("Programmes in " + course.getCourseCode() + ":");
                courseUI.programmeHeader();
                for (int i = 1; i <= course.getProgrammeList().getNumberOfEntries(); i++) {
                    programme = course.getProgrammeList().getEntry(i);
                    System.out.println(i + ". " + programme);
                }

            } else {
                System.out.println("Invalid programme selection.");
            }
        } else {
            System.out.println("Invalid course selection.");
        }

        System.out.print("\nDo you want to add another programme to a course? (Y/N): ");
        String choice = input.nextLine().toUpperCase();
        continueAdding = choice.equals("Y");
    } while (continueAdding);

}
    
    public void removeProgrammeFromCourse(SortedLinkedListInterface<Programme> p, SortedLinkedListInterface<Course> c) {
        Scanner input = new Scanner(System.in);
        boolean continueRemove = true;

        do {
            courseUI.listAllCourse(getAllCourse());
            System.out.print("Please select the course from which you want to remove a programme (exp: 1/2/3): \n>> ");
            int selectedCourseIndex = input.nextInt();
            input.nextLine();

            if (selectedCourseIndex >= 1 && selectedCourseIndex <= c.getNumberOfEntries()) { 
                Course course= c.getEntry(selectedCourseIndex);
                if(course.getProgrammeList().isEmpty()){
                     System.out.println("\nThere are " + course.getProgrammeList().getNumberOfEntries() + " programme in " +  course.getCourseCode() + ".");
                }else{
                    System.out.println("Programme in " + course.getCourseCode() + ":");
                    courseUI.programmeHeader();

                    for (int i = 1; i <= course.getProgrammeList().getNumberOfEntries(); i++) {
                        Programme programme = course.getProgrammeList().getEntry(i);
                        System.out.println(i + ". " + programme);
                    }

                    System.out.print("Enter the number of the programme to remove: ");
                    int selectedProgrammeIndex = input.nextInt();
                    input.nextLine();

                    if (selectedProgrammeIndex >= 1 && selectedProgrammeIndex <= course.getProgrammeList().getNumberOfEntries()) {
                        Programme selectedProgramme = course.getProgrammeList().getEntry(selectedProgrammeIndex);

                        boolean removed = course.getProgrammeList().remove(selectedProgramme);

                        if (removed) {
                            selectedProgramme.getCourseList().remove(course);
                            System.out.println("Programme removed successfully.");
                        } else {
                            System.out.println("Failed to remove the programme.");
                        }
                    } else {
                        System.out.println("Invalid programme selection.");
                    }
                }
                
            } else {
                System.out.println("Invalid course selection.");
            }
            
            System.out.print("\nDo you want to remove another programme from a course? (Y/N): ");
            String choice = input.nextLine().toUpperCase();
            continueRemove = choice.equals("Y");
        } while (continueRemove);
   
    }
    
    public void addCourseToProgramme(SortedLinkedListInterface<Course> c, SortedLinkedListInterface<Programme> p) {
        Scanner input = new Scanner(System.in);
        boolean continueAdding = true;
        boolean validSelection = false;
        boolean CourseExistsInSemester, FacultyNotMatch;
        String semester;
        String faculty;

        do {
            do{
                int selectedProgramme, selectedCourse, selectedChoice;
                courseUI.getAddChoice();
                System.out.print("Enter the number of choice: ");
                selectedChoice = input.nextInt();
                input.nextLine();
                switch (selectedChoice) {
                    case 1:
                        courseUI.listAllProgrammes(getAllProgramme());
                        System.out.print("Please select the programme to which you want to add a course (exp: 1/2/3): \n>> ");
                        selectedProgramme = input.nextInt();
                        input.nextLine();

                        if (selectedProgramme >= 0 && selectedProgramme <= p.getNumberOfEntries()) { 
                            Programme programme = p.getEntry(selectedProgramme);
                            courseUI.listAllCourse(getAllCourse());
                            System.out.print("Enter the number of the course to add: ");
                            selectedCourse = input.nextInt();
                            input.nextLine();

                            if (selectedCourse >= 0 && selectedCourse <= c.getNumberOfEntries()) {
                                while (programme.getCourseList().contains(c.getEntry(selectedCourse))) {
                                    System.out.println("The selected course already exists in the programme.");
                                    System.out.print("Please select another course: ");
                                    selectedCourse = input.nextInt();
                                    input.nextLine();
                                }
                                while(!c.getEntry(selectedCourse).getFaculty().contains(programme.getFaculty())) { //to check the selected course's falculty contains the selected programme's faculty
                                    System.out.println("This course is not allowed to add to the selected programme, this course is only available for programme that are from " + c.getEntry(selectedCourse).getFaculty());
                                    System.out.print("Please enter another programme: ");
                                    selectedCourse = input.nextInt();
                                    input.nextLine();
                                    while (programme.getCourseList().contains(c.getEntry(selectedCourse))) {
                                        System.out.println("The selected course already exists in the programme.");
                                        System.out.print("Please select another course: ");
                                        selectedCourse = input.nextInt();
                                        input.nextLine();
                                    }
                                }

                                Course course = c.getEntry(selectedCourse);

                                programme.getCourseList().add(course);
                                System.out.println("Course added successfully.");
                                course.getProgrammeList().add(programme);
                                System.out.println("Courses in " + programme.getProgrammeCode() + ":");
                                courseUI.courseHeader();
                                for (int i = 1; i <= programme.getCourseList().getNumberOfEntries(); i++) {
                                    Course currentcourse = programme.getCourseList().getEntry(i);
                                    System.out.println(i + ". " + currentcourse);
                                }
                            } else {
                                System.out.println("Invalid course selection.");
                            }
                        } else {
                            System.out.println("Invalid programme selection.");
                        }
                        validSelection = true;
                        break;
                    case 2:
                        courseUI.listAllProgrammes(getAllProgramme());
                        System.out.print("Please select the programme to which you want to add a course (exp: 1/2/3): \n>> ");
                        selectedProgramme = input.nextInt();
                        input.nextLine();

                        if (selectedProgramme >= 0 && selectedProgramme <= p.getNumberOfEntries()) { 
                            Programme programme = p.getEntry(selectedProgramme);

                            String courseCode = courseUI.inputCourseCode();
                            String title = courseUI.inputCourseTitle();
                            int creditHour = courseUI.inputCreditHour();

                            do {
                                CourseExistsInSemester = false;
                                FacultyNotMatch = false;
                                semester = courseUI.inputSemester();

                                for (int i = 1; i <= c.getNumberOfEntries(); i++) {
                                    if (courseCode.equals(c.getEntry(i).getCourseCode()) && 
                                        title.equals(c.getEntry(i).getTitle()) && 
                                        semester.equals(c.getEntry(i).getSemester())) {
                                        CourseExistsInSemester = true;
                                        System.out.println("A course with the same course code and title already exists in this semester. Please try again.");
                                        System.out.println("Do you want to enter a new semester(Y for enter new semester, N for enter new course details)(Y/N)?");
                                        String choice = input.nextLine().toUpperCase();
                                        if (choice.equals("Y")) {
                                            break;
                                        } else {
                                            courseCode = courseUI.inputCourseCode();
                                            title = courseUI.inputCourseTitle();
                                            creditHour = courseUI.inputCreditHour();
                                        }
                                    }
                                }

                            } while (CourseExistsInSemester);  


                            do {
                                FacultyNotMatch = false;
                                faculty = courseUI.inputFaculty().toUpperCase();

                                if (!faculty.contains(p.getEntry(selectedProgramme).getFaculty())) {
                                    FacultyNotMatch = true;
                                    System.out.println("The new course's faculty must contains the selected programme's faculty. Please try again.");
                                }

                            } while (FacultyNotMatch);
                            String status = courseUI.inputStatus();
                            System.out.println();

                            Course course = new Course(courseCode, title, creditHour, semester, faculty, status);
                            c.add(course); // Add the course to course list

                            programme.getCourseList().add(course);
                            System.out.println("Course added successfully.");
                            course.getProgrammeList().add(programme);
                            System.out.println("Courses in " + programme.getProgrammeCode() + ":");
                            courseUI.courseHeader();
                            for (int i = 1; i <= programme.getCourseList().getNumberOfEntries(); i++) {
                                Course currentcourse = programme.getCourseList().getEntry(i);
                                System.out.println(i + ". " + currentcourse);
                            }

                        } else {
                            System.out.println("Invalid programme selection.");
                        }
                        validSelection = true;
                        break;
                    default:
                        System.out.println("\nInvalid selection. Please select again.\n");
                        break;

                }
            } while (!validSelection);

            System.out.print("\nDo you want to add another programme to a course? (Y/N): ");
            String choice = input.nextLine().toUpperCase();
            continueAdding = choice.equals("Y");
        } while (continueAdding);
    
    }
    
    public void removeCourseFromProgramme(SortedLinkedListInterface<Course> c, SortedLinkedListInterface<Programme> p) {
        Scanner input = new Scanner(System.in);
        boolean continueRemove = true;

        do {
            courseUI.listAllProgrammes(getAllProgramme());
            System.out.print("Please select the programme from which you want to remove a course (exp: 1/2/3): \n>> ");
            int selectedProgrammeIndex = input.nextInt();
            input.nextLine();

            if (selectedProgrammeIndex >= 1 && selectedProgrammeIndex <= p.getNumberOfEntries()) { 
                Programme programme = p.getEntry(selectedProgrammeIndex);
                if(programme.getCourseList().isEmpty()){
                     System.out.println("\nThere are " + programme.getCourseList().getNumberOfEntries() + " course from " +  programme.getProgrammeCode() + ".");
                }else{
                    System.out.println("Courses in " + programme.getProgrammeCode() + ":");
                    for (int i = 1; i <= programme.getCourseList().getNumberOfEntries(); i++) {
                        Course course = programme.getCourseList().getEntry(i);
                        System.out.println(i + ". " + course);
                    }

                    System.out.print("Enter the number of the course to remove: ");
                    int selectedCourseIndex = input.nextInt();
                    input.nextLine();

                    if (selectedCourseIndex >= 1 && selectedCourseIndex <= programme.getCourseList().getNumberOfEntries()) {
                        Course selectedCourse = programme.getCourseList().getEntry(selectedCourseIndex);

                        boolean removed = programme.getCourseList().remove(selectedCourse);
                        if (removed) {
                            selectedCourse.getProgrammeList().remove(programme);
                            System.out.println("Course removed successfully.");
                        } else {
                            System.out.println("Failed to remove the course.");
                        }
                    } else {
                        System.out.println("Invalid course selection.");
                    }
                }
                
            } else {
                System.out.println("Invalid programme selection.");
            }

            System.out.print("\nDo you want to remove another course from a programme? (Y/N): ");
            String choice = input.nextLine().toUpperCase();
            continueRemove = choice.equals("Y");
        } while (continueRemove);

    }
    
    public void searchCourseInSemester(SortedLinkedListInterface<Course> c) {
        Scanner input = new Scanner(System.in);
        boolean continueSearch = true;

        do {
            System.out.print("Please enter the semester to view the offered courses (e.g., Y1S1): \n>> ");
            String semesterInput = input.nextLine().toUpperCase();
            System.out.println("Courses offered in " + semesterInput + ":");

            boolean found = false;
            int count = 0;

            for (int i = 1; i <= c.getNumberOfEntries(); i++) {
                Course course = c.getEntry(i);
                if (semesterInput.equalsIgnoreCase(course.getSemester())) {
                    count++;
                    System.out.println(count + ". " + course);
                    found = true;
                }
            }

            if (!found) {
                System.out.println("No courses offered in " + semesterInput);
            }
            
            System.out.print("\nDo you want to search another semester? (Y/N): ");
            String choice = input.nextLine().toUpperCase();
            continueSearch = choice.equals("Y");
        } while (continueSearch);
 
    }
    
    
    public void amendCourseDetailsForProgramme(SortedLinkedListInterface<Course> c, SortedLinkedListInterface<Programme> p) {
        Scanner input = new Scanner(System.in);
        boolean continueAmend = true;
        boolean validSelection = false;
        
        do {
            
                int selectedProgrammeIndex, selectedCourseIndex, choice;
                courseUI.listAllProgrammes(getAllProgramme());
                System.out.print("Please select the programme which you want to amend the course details(exp: 1/2/3): \n>> ");
                selectedProgrammeIndex = input.nextInt();
                input.nextLine();
                if (selectedProgrammeIndex >= 0 && selectedProgrammeIndex <= p.getNumberOfEntries()) { 
                    Programme programme = p.getEntry(selectedProgrammeIndex);
                    if(programme.getCourseList().isEmpty()){
                         System.out.println("\nThere are " + programme.getCourseList().getNumberOfEntries() + " course from " +  programme.getProgrammeCode() + ".");
                    }else{
                        System.out.println("Courses in " + programme.getProgrammeCode() + ":");
                        for (int i = 1; i <= programme.getCourseList().getNumberOfEntries(); i++) {
                            Course course = programme.getCourseList().getEntry(i);
                            System.out.println(i + ". " + course);
                        }
                        System.out.print("Enter the number of the course to amend the details: ");
                        selectedCourseIndex = input.nextInt();
                        input.nextLine();

                        if (selectedCourseIndex >= 1 && selectedCourseIndex <= programme.getCourseList().getNumberOfEntries()) {
                            Course selectedCourse = programme.getCourseList().getEntry(selectedCourseIndex);

                            Course course = c.getEntry(selectedCourseIndex);
                            do{
                                courseUI.editCourseDetails(selectedCourse);
                                System.out.print("Enter the number of course details to amend: ");
                                choice = input.nextInt();
                                input.nextLine();
                                switch (choice) {
                                    case 1:
                                        String newCourseTitle;
                                        boolean CourseTitleExists;

                                        do {
                                            newCourseTitle = courseUI.inputCourseTitle();
                                            CourseTitleExists = false;

                                            for (int i = 1; i <= c.getNumberOfEntries(); i++) {
                                                if (newCourseTitle.equals(c.getEntry(i).getTitle())) {
                                                    CourseTitleExists = true;
                                                    break;
                                                }
                                            }

                                            if (CourseTitleExists) {
                                                System.out.println("This course title already exists. Please enter again.");
                                            }

                                        } while (CourseTitleExists); 

                                        if (!CourseTitleExists){
                                            Course newCourse = new Course(selectedCourse.getCourseCode(), newCourseTitle, selectedCourse.getCreditHour(), selectedCourse.getSemester(), selectedCourse.getFaculty(), selectedCourse.getStatus());
                                            boolean amended = programme.getCourseList().amend(selectedCourse, newCourse);
                                            if (amended) {
                                                System.out.println("Course title amended successfully.");
                                                System.out.println("Courses in " + programme.getProgrammeCode() + ":");
                                                courseUI.courseHeader();
                                                for (int i = 1; i <= programme.getCourseList().getNumberOfEntries(); i++) {
                                                    course = programme.getCourseList().getEntry(i);
                                                    System.out.println(i + ". " + course);
                                                }
                                            } else {
                                                System.out.println("Failed to amend course title.");
                                            }
                                        }
                                        validSelection = true;
                                        break;
                                    case 2:
                                        int newCreditHour;
                                        boolean invalidCreditHour;

                                        do {
                                            newCreditHour = courseUI.inputCreditHour();
                                            invalidCreditHour = false;

                                            if (newCreditHour <= 0) {
                                              invalidCreditHour = true;
                                              System.out.println("Please enter a valid credit hour.");
                                            }


                                        } while (invalidCreditHour); 

                                        if (!invalidCreditHour){
                                            Course newCourse = new Course(selectedCourse.getCourseCode(), selectedCourse.getTitle(), newCreditHour, selectedCourse.getSemester(), selectedCourse.getFaculty(), selectedCourse.getStatus());
                                            boolean amended = programme.getCourseList().amend(selectedCourse, newCourse);
                                            if (amended) {
                                                System.out.println("Credit hour amended successfully.");
                                                System.out.println("Courses in " + programme.getProgrammeCode() + ":");
                                                courseUI.courseHeader();
                                                for (int i = 1; i <= programme.getCourseList().getNumberOfEntries(); i++) {
                                                    course = programme.getCourseList().getEntry(i);
                                                    System.out.println(i + ". " + course);
                                                }
                                            } else {
                                                System.out.println("Failed to amend credit hour.");
                                            }
                                        }
                                        validSelection = true;
                                        break;
                                        case 3:
                                        String newStatus;
                                        boolean isStatusNull;

                                        do {
                                            newStatus = courseUI.inputStatus();
                                            isStatusNull = false;

                                            if (newStatus == "") {
                                                isStatusNull = true;
                                                System.out.println("Please enter the status");
                                            }

                                        } while (isStatusNull);

                                        if (!isStatusNull) {
                                            Course newCourse = new Course(selectedCourse.getCourseCode(), selectedCourse.getTitle(), selectedCourse.getCreditHour(), selectedCourse.getSemester(), selectedCourse.getFaculty(), newStatus);
                                            boolean amended = programme.getCourseList().amend(selectedCourse, newCourse);
                                            if (amended) {
                                                System.out.println("Status amended successfully.");
                                                System.out.println("Courses in " + programme.getProgrammeCode() + ":");
                                                courseUI.courseHeader();
                                                for (int i = 1; i <= programme.getCourseList().getNumberOfEntries(); i++) {
                                                    course = programme.getCourseList().getEntry(i);
                                                    System.out.println(i + ". " + course);
                                                }
                                            } else {
                                                System.out.println("Failed to amend status.");
                                            }
                                        }
                                        validSelection = true;
                                        break;
                                    case 0:
                                        validSelection = true;
                                    break;
                                    default:
                                    System.out.println("\nInvalid course details selection. Please select again.\n");
                                    break;

                                }            
                            } while (!validSelection);

                        } else {
                            System.out.println("Invalid course selection.");
                        }
                    }
                    
                } else {
                    System.out.println("Invalid programme selection.");
                }

            System.out.print("\nDo you want to amend another course details for a programme? (Y/N): ");
            String choiceContinue = input.nextLine().toUpperCase();
            continueAmend = choiceContinue.equals("Y");
        } while (continueAmend);

    }
    
    
    public void listAllCourseByFaculty(SortedLinkedListInterface<Course> c) {
        Scanner input = new Scanner(System.in);
        boolean continueList = true;

        do {
            System.out.print("Please enter the faculty to view the courses taken (e.g., FOCS): \n>> ");
            String facultyInput = input.nextLine().toUpperCase();
            System.out.println("Courses taken by " + facultyInput + ":");

            boolean found = false;
            int count = 0;

            for (int i = 1; i <= c.getNumberOfEntries(); i++) {
                Course course = c.getEntry(i);
                if (course.getFaculty().contains(facultyInput)) {
                    count++; 
                    System.out.println(count + ". " + course); 
                    found = true;
                }
            }

            if (!found) {
                System.out.println("No courses taken by " + facultyInput);
            } else {
                System.out.println("Total number of courses taken by " + facultyInput + ": " + count);
            }

            System.out.print("\nDo you want to list all the course taken by another faculty? (Y/N): ");
            String choice = input.nextLine().toUpperCase();
            continueList = choice.equals("Y");
        } while (continueList);

        
    }
    
    public void listAllCourseForProgramme(SortedLinkedListInterface<Course> c, SortedLinkedListInterface<Programme> p) {
        Scanner input = new Scanner(System.in);
        boolean continueList = true;

        do {
            boolean validSelection = false;
            courseUI.listAllProgrammes(getAllProgramme());
            do {
                System.out.print("Enter the number of programme: ");
                int selectedProgramme = input.nextInt();
                input.nextLine();

                if (selectedProgramme >= 0 && selectedProgramme < p.getNumberOfEntries()) {
                    Programme programme = p.getEntry(selectedProgramme);
                    if(programme.getCourseList().isEmpty()){
                        System.out.println("There are 0 course in " + programme.getProgrammeCode());
                    }else{
                        System.out.println("Courses in " + programme.getProgrammeCode());
                        courseUI.courseHeader();
                        for (int i = 1; i <= programme.getCourseList().getNumberOfEntries(); i++) {
                            Course currentcourse = programme.getCourseList().getEntry(i);
                            System.out.println(i + ". " + currentcourse);
                        }
                    }


                    validSelection = true;
                } else {
                    System.out.println("Invalid programme selection. Please try again.");
                }
            } while (!validSelection);



            System.out.print("\nDo you want to list all the courses for another programme? (Y/N): ");
            String choice = input.nextLine().toUpperCase();
            continueList = choice.equals("Y");
        } while (continueList);
        

        
    }

    public void listAllProgrammeForCourse(SortedLinkedListInterface<Programme> p, SortedLinkedListInterface<Course> c) {
        Scanner input = new Scanner(System.in);
        boolean continueList = true;

        do {
            boolean validSelection = false;
            courseUI.listAllCourse(getAllCourse());
            do {
                System.out.print("Enter the number of course: ");
                int selectedCourse = input.nextInt();
                input.nextLine();

                if (selectedCourse >= 0 && selectedCourse < c.getNumberOfEntries()) {
                    Course course = c.getEntry(selectedCourse);

                    System.out.println("Programme in " + course.getCourseCode() + ":");

                    if(course.getProgrammeList().isEmpty()){
                        System.out.println("There are 0 programme for " + course.getCourseCode());
                    }else{
                        courseUI.programmeHeader();
                        for (int i = 1; i <= course.getProgrammeList().getNumberOfEntries(); i++) {
                            Programme programme = course.getProgrammeList().getEntry(i);
                            System.out.println(i + ". " + programme);
                        }
                    }
                    validSelection = true;
                } else {
                    System.out.println("Invalid course selection. Please try again.");
                }
            } while (!validSelection);

            System.out.print("\nDo you want to list all the programmes for another course? (Y/N): ");
            String choice = input.nextLine().toUpperCase();
            continueList = choice.equals("Y");
        } while (continueList);

    }
    
    public void summaryReport1(SortedLinkedListInterface<Course> c, SortedLinkedListInterface<Programme> p) {
        Scanner input = new Scanner(System.in);
        int countCourse = 0, maxProgrammeEnrolled = 0, minProgrammeEnrolled = 100, maxFacultyOffered = 0, minFacultyOffered = 100;
        courseUI.reportHeader();
        System.out.printf("%90s\n", "*Course Enrollment and Faculty Summary Report*\n");
        SortedLinkedListInterface<Course> courseWithMaxEnrollment  = new SortedDoublyLinkedList<>();
        SortedLinkedListInterface<Course> courseWithMinEnrollment  = new SortedDoublyLinkedList<>();
        SortedLinkedListInterface<Course> coursesWithMaxFaculty  = new SortedDoublyLinkedList<>();
        SortedLinkedListInterface<Course> coursesWithMinFaculty  = new SortedDoublyLinkedList<>();
        
        courseUI.summaryReport1ListHeader();
        for (int i = 1; i <= c.getNumberOfEntries(); i++) {
            Course course = c.getEntry(i);
            int countProgrammeOffer = 0;
            int countFaculty = 0;
            int countProgrammeEnrolled = 0;
            
            String[] faculties = course.getFaculty().split("\\s+");
            for (int f = 0; f < faculties.length; f++) {
                countFaculty++;
            }
            for (int k = 1; k <= p.getNumberOfEntries(); k++) {
                Programme programme = p.getEntry(k);
                if (course.getFaculty().contains(programme.getFaculty())) {
                        countProgrammeOffer++;   
                }
            }
            
 
            for (int j = 1; j <= course.getProgrammeList().getNumberOfEntries(); j++) {
                countProgrammeEnrolled++;     
            }
            
            countCourse++;
            
            if (countProgrammeEnrolled > maxProgrammeEnrolled && countProgrammeEnrolled > 0) {
                        maxProgrammeEnrolled = countProgrammeEnrolled;
                        courseWithMaxEnrollment.clear(); // Clear the list when finding a new minimum
                        courseWithMaxEnrollment.add(course);
            } 
            else if (countProgrammeEnrolled == maxProgrammeEnrolled) {
                        courseWithMaxEnrollment.add(course);
            }
            
            if (countProgrammeEnrolled < minProgrammeEnrolled) {
                        minProgrammeEnrolled = countProgrammeEnrolled;
                        courseWithMinEnrollment.clear();
                        courseWithMinEnrollment.add(course);
            } 
            else if (countProgrammeEnrolled == minProgrammeEnrolled) {
                        courseWithMinEnrollment.add(course);
            }
            
            if (countFaculty > maxFacultyOffered && countFaculty > 0) {
                        maxFacultyOffered = countFaculty;
                        coursesWithMaxFaculty.clear();
                        coursesWithMaxFaculty.add(course);
            } 
            else if (countFaculty == maxFacultyOffered) {
                        coursesWithMaxFaculty.add(course);
            }
            
            if (countFaculty < minFacultyOffered) {
                        minFacultyOffered = countFaculty;
                        coursesWithMinFaculty.clear();
                        coursesWithMinFaculty.add(course);
            } 
            else if (countFaculty == minFacultyOffered) {
                        coursesWithMinFaculty.add(course);
            }
            
            System.out.printf("%-4d %-15s %-74s %4d %-1s %-10d %20d\n", i, courseList.getEntry(i).getCourseCode(), courseList.getEntry(i).getTitle(), countProgrammeEnrolled, "/", countProgrammeOffer, countFaculty);
            
        }
        System.out.println("\nTotal number of course: " + countCourse);
        courseUI.line();
        
        System.out.println("\nCourse with the highest number of programme enrollment:");
        for (int i = 1; i <= courseWithMaxEnrollment.getNumberOfEntries(); i++) {
            Course course = courseWithMaxEnrollment.getEntry(i);
            System.out.println(course.getCourseCode() + " - " + course.getTitle() + " (Programme Enrolled: " + maxProgrammeEnrolled + ")");
                
        }
        if(courseWithMaxEnrollment.getNumberOfEntries() == 0 && maxProgrammeEnrolled == 0){
            System.out.println("\nThere is no highest number of programme enrollment, all of the courses have 0 programme enrollment.");
        }else{
            System.out.println("\n" + courseWithMaxEnrollment.getNumberOfEntries() + " course(s) have the highest number of " + maxProgrammeEnrolled + " programme enrollment.");
        }
        
       

        courseUI.line();
        System.out.println("\nCourse with the lowest number of programme enrollment:");
        for (int i = 1; i <= courseWithMinEnrollment.getNumberOfEntries(); i++) {
            Course course = courseWithMinEnrollment.getEntry(i);
            System.out.println(course.getCourseCode() + " - " + course.getTitle() + " (Programme Enrolled: " + minProgrammeEnrolled + ")");
                
        }
        
        System.out.println("\n" + courseWithMinEnrollment.getNumberOfEntries() + " course(s) have the lowest number of " + minProgrammeEnrolled + " programme enrollment.");
        courseUI.line();
        
        System.out.println("\nCourse with the highest number of faculties offered:");
        for (int i = 1; i <= coursesWithMaxFaculty.getNumberOfEntries(); i++) {
            Course course = coursesWithMaxFaculty.getEntry(i);
                System.out.println(course.getCourseCode() + " - " + course.getTitle() + " (Faculties Offered: " + maxFacultyOffered + ")");
                
        }
        
        System.out.println("\n" + coursesWithMaxFaculty.getNumberOfEntries() + " course(s) have the highest number of " + maxFacultyOffered + " faculties offered.");
        courseUI.line();
        
        System.out.println("\nCourse with the lowest number of faculties offered:");
        for (int i = 1; i <= coursesWithMinFaculty.getNumberOfEntries(); i++) {
            Course course = coursesWithMinFaculty.getEntry(i);
                System.out.println(course.getCourseCode() + " - " + course.getTitle() + " (Faculties Offered: " + minFacultyOffered + ")");
                
        }

        System.out.println("\n" + coursesWithMinFaculty.getNumberOfEntries() + " course(s) have the lowest number of " + minFacultyOffered + " faculty offered.\n");
        courseUI.reportFooter();
        System.out.print("\nPress <ENTER> key to go back to Course Management Subsystem Main Menu...");
        input.nextLine();
        
         
    }
    
    public void summaryReport2(SortedLinkedListInterface<Course> c, SortedLinkedListInterface<Programme> p) {
        Scanner input = new Scanner(System.in);
        int countCourse = 0, countMain = 0, countElective = 0, countMainElective = 0, countCourseInSemester, maxCourses = 0, minCourses = 100, totalCreditHours, maxCreditHours = 0, minCreditHours= 100;
        SortedLinkedListInterface<Course> courseSemester  = new SortedDoublyLinkedList<>();
        SortedLinkedListInterface<Course> semesterWithMinCourses  = new SortedDoublyLinkedList<>();
        SortedLinkedListInterface<Course> semesterWithMaxCourses  = new SortedDoublyLinkedList<>();
        SortedLinkedListInterface<Course> semesterWithMinCreditHours  = new SortedDoublyLinkedList<>();
        SortedLinkedListInterface<Course> semesterWithMaxCreditHours = new SortedDoublyLinkedList<>();
        courseUI.reportHeader();
        System.out.printf("%92s\n", "*Course Offering and Credit Hours Summary Report*\n");
        courseUI.summaryReport2ListHeader();
        for (int i = 1; i <= c.getNumberOfEntries(); i++) {
            Course course = c.getEntry(i);
            countCourse++;
            System.out.printf("%-4d %-15s %-74s %-15s %10d %16s\n", i, courseList.getEntry(i).getCourseCode(), courseList.getEntry(i).getTitle(), courseList.getEntry(i).getStatus(),  courseList.getEntry(i).getCreditHour(), courseList.getEntry(i).getSemester());
            if(course.getStatus() == "MAIN"){
                countMain++;
            }else if(course.getStatus() == "ELECTIVE"){
                countElective++;
            }else{
                countMainElective++;
            }

        }
        System.out.println("\nTotal number of course: " + countCourse + " (" + countMain + " Main" + " | " + countElective + " Elective" + " | " + countMainElective + " Main/Elective" + ")");
        courseUI.line();

        courseSemester.add(c.getEntry(1));

        for (int j = 2; j <= c.getNumberOfEntries(); j++) {
            Course currentCourse = c.getEntry(j);
            String currentSemester = currentCourse.getSemester();
            boolean semesterExists = false;

            for (int k = 1; k <= courseSemester.getNumberOfEntries(); k++) {
                String existingSemester = courseSemester.getEntry(k).getSemester();
                if (existingSemester.equals(currentSemester)) {
                    semesterExists = true;
                    break;
                }
            }


            if (!semesterExists) {
                courseSemester.add(currentCourse);
            }
        }


        for (int k = 1; k <= courseSemester.getNumberOfEntries(); k++) {
            countCourseInSemester = 0;
            totalCreditHours = 0;
            System.out.println("Courses Offered in " + courseSemester.getEntry(k).getSemester() + ":- ");
            for (int j = 1; j <= c.getNumberOfEntries(); j++) {
                Course course = c.getEntry(j);
                if (course.getSemester().equals(courseSemester.getEntry(k).getSemester())) {
                    countCourseInSemester++;
                    totalCreditHours += course.getCreditHour();
                    System.out.println(course.getCourseCode() + " - " + course.getTitle());
                }
            }
            System.out.println("Total Courses of " + courseSemester.getEntry(k).getSemester() + ": " + countCourseInSemester);
            System.out.println("Total Credit Hours of " + courseSemester.getEntry(k).getSemester() + ": " + totalCreditHours + "\n");
            if (countCourseInSemester > maxCourses) {
                maxCourses = countCourseInSemester;
                semesterWithMaxCourses.clear();
                semesterWithMaxCourses.add(courseSemester.getEntry(k));
            } 
            else if (countCourseInSemester == maxCourses) {
                semesterWithMaxCourses.add(courseSemester.getEntry(k));
            }
            if (countCourseInSemester < minCourses) {
                minCourses = countCourseInSemester;
                semesterWithMinCourses.clear();
                semesterWithMinCourses.add(courseSemester.getEntry(k));
            } 
            else if (countCourseInSemester == minCourses) {
                semesterWithMinCourses.add(courseSemester.getEntry(k));
            }
            
            
            if (totalCreditHours > maxCreditHours) {
                maxCreditHours = totalCreditHours;
                semesterWithMaxCreditHours.clear();
                semesterWithMaxCreditHours.add(courseSemester.getEntry(k));
            } 
            else if (totalCreditHours == maxCreditHours) {
                semesterWithMaxCreditHours.add(courseSemester.getEntry(k));
            }
            if (totalCreditHours < minCreditHours) {
                minCreditHours = totalCreditHours;
                semesterWithMinCreditHours.clear();
                semesterWithMinCreditHours.add(courseSemester.getEntry(k));
            } 
            else if (totalCreditHours == minCreditHours) {
                semesterWithMinCreditHours.add(courseSemester.getEntry(k));
            }

        }
        courseUI.line();
        System.out.println("\nSemester with the highest number of courses:");
        for (int i = 1; i <= semesterWithMaxCourses.getNumberOfEntries(); i++) {
            Course course = semesterWithMaxCourses.getEntry(i);
            System.out.println(course.getSemester() + " (Courses Offered: " + maxCourses + ")");
        }
        
        courseUI.line();
        System.out.println("\nSemester with the lowest number of courses:");
        for (int i = 1; i <= semesterWithMinCourses.getNumberOfEntries(); i++) {
            Course course = semesterWithMinCourses.getEntry(i);
            System.out.println(course.getSemester() + " (Courses Offered: " + minCourses + ")");
        }
        
        courseUI.line();
        System.out.println("\nSemester with the highest number of credit hours:");
        for (int i = 1; i <= semesterWithMaxCreditHours.getNumberOfEntries(); i++) {
            Course course = semesterWithMaxCreditHours.getEntry(i);
            System.out.println(course.getSemester() + " (Total Credit Hours: " + maxCreditHours + ")");
        }
        
        courseUI.line();
        System.out.println("\nSemester with the lowest number of credit hours:");
        for (int i = 1; i <= semesterWithMinCreditHours.getNumberOfEntries(); i++) {
            Course course = semesterWithMinCreditHours.getEntry(i);
            System.out.println(course.getSemester() + " (Total Credit Hours: " + minCreditHours + ")");
        }
        courseUI.reportFooter();
        System.out.print("\nPress <ENTER> key to go back to Course Management Subsystem Main Menu...");
        input.nextLine();
 
    }

    public void addNewCourse(SortedLinkedListInterface<Course> c) {
        Scanner input = new Scanner(System.in);
        boolean continueAdding = true;

        do {
            boolean CourseExistsInSemester;
            String semester;

            String courseCode = courseUI.inputCourseCode();
            String title = courseUI.inputCourseTitle();
            int creditHour = courseUI.inputCreditHour();


            do {
                CourseExistsInSemester = false;
                semester = courseUI.inputSemester();

                for (int i = 1; i <= c.getNumberOfEntries(); i++) {
                    if (courseCode.equals(c.getEntry(i).getCourseCode()) && 
                        title.equals(c.getEntry(i).getTitle()) && 
                        semester.equals(c.getEntry(i).getSemester())) {
                        CourseExistsInSemester = true;
                        System.out.println("A course with the same course code and title already exists in this semester. Please try again.");
                        System.out.println("Do you want to enter a new semester(Y for enter new semester, N for enter new course details)(Y/N)?");
                        String choice = input.nextLine().toUpperCase();
                        if (choice.equals("Y")) {
                            break; 
                        } else {
                            courseCode = courseUI.inputCourseCode();
                            title = courseUI.inputCourseTitle();
                            creditHour = courseUI.inputCreditHour();
                        }
                    }
                }

            } while (CourseExistsInSemester);  

            String faculty = courseUI.inputFaculty();
            String status = courseUI.inputStatus();
            System.out.println();

            Course course = new Course(courseCode, title, creditHour, semester, faculty, status);
            courseList.add(course);

            System.out.print("Do you want to add another new course? (Y/N): ");
            String choice = input.nextLine().toUpperCase();
            continueAdding = choice.equals("Y");
        } while (continueAdding);


    }


    public String getAllCourse() {
        String outputStr = "";  
        for (int i = 1; i <= courseList.getNumberOfEntries(); i++) {
            outputStr += i + ". " + courseList.getEntry(i) + "\n";
        }
        return outputStr;
    }
    
    public String getAllProgramme() {
        String outputStr = "";
        for (int i = 1; i <= programmeList.getNumberOfEntries(); i++) {
            outputStr += i + ". " + programmeList.getEntry(i) + "\n";
        }
        return outputStr;
    }
 
  
    public void displayCourse() {
        courseUI.listAllCourse(getAllCourse());
    }
}
