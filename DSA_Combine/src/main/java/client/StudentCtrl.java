/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package client;
import adt.SortedDoublyLinkedList;
import entity.Student;
import java.util.Scanner;
import adt.SortedLinkedListInterface;
import dao.DAO;
import entity.Course;
import entity.Programme;
import entity.TutorialGroup;
import java.time.LocalDateTime;  
import java.time.format.DateTimeFormatter;  


/**
 *
 * @author Ting Xin Yi
 */
public class StudentCtrl {
    public static SortedLinkedListInterface<Course> courseList= CourseCtrl.courseList;
    public static SortedLinkedListInterface<Programme> programmeList = CourseCtrl.programmeList;
    public static SortedLinkedListInterface<TutorialGroup> tutorialGroupList = new SortedDoublyLinkedList<>();
    public static SortedLinkedListInterface<Student> students = MainCtrl.students;
    private DAO DAO = new DAO();
    public static final int PRICE_PER_HOUR = 200;
    
    
    public StudentCtrl() {
        tutorialGroupList = DAO.getAllTutorial();
//        preAddAll(programmeList);
    }
    
     public static SortedLinkedListInterface<Student> getStudentList(){
        return students;
    }
    
  public void performStudent() {
    Scanner input = new Scanner(System.in);
    int performStudentChoice = 0;
    boolean exit = false; // Flag to control the loop
    do{
        System.out.println("\n******************   STUDENT REGISTRATION MANAGEMENT SUSBSYSTEM SECTION   ******************");
        System.out.println("Please select your action:");
        System.out.println("1. Add new student");
        System.out.println("2. Register course");
        System.out.println("3. Search student");
        System.out.println("4. Edit existing student");
        System.out.println("5. Remove student");
        System.out.println("6. Filter student");
        System.out.println("7. Calculate student fees");
        System.out.println("8. Summary Report");
        System.out.println("9. Display all students");
        System.out.println("10. Exit");
        System.out.print("Enter your choice: ");
        performStudentChoice = input.nextInt();
        input.nextLine();  // consume the leftover newline

        switch (performStudentChoice) {
            case 1:
                addStudent();
                break;
            case 2:
                registerCourse();
                break;
            case 3:
                containStudent();
                break;
            case 4:
                amendStudent();
                break;
            case 5:
                deleteStudent();
                break;
            case 6:
                filterStudent();
                break;
            case 7:
                calculateStudentFees();
                break;
            case 8:
                summaryReport();
                break;
            case 9:
                displayAllStudent();
                break;
            case 10:
                System.out.println("\nExiting Student Registration Management Subsystem...");
                exit = true;
                return;
            default:
                System.out.println("Incorrect choice, please choose again!");
                break;
        }
    }while(performStudentChoice!=10);
}

    
//    public static void preAddAll(SortedLinkedListInterface<Programme> programmeList){
//        programmeList.getEntry(1).getTutorialGroupList().add(new TutorialGroup("DBAG1", 2));
//        programmeList.getEntry(2).getTutorialGroupList().add(new TutorialGroup("DBFG3", 3));
//        programmeList.getEntry(2).getTutorialGroupList().add(new TutorialGroup("REIG1", 1));
//        programmeList.getEntry(3).getTutorialGroupList().add(new TutorialGroup("DHMG2", 2));
//        programmeList.getEntry(1).getTutorialGroupList().getEntry(1).getStudentList().add(new Student("TING", 21, "24WMR", 3.8, false));
//        programmeList.getEntry(2).getTutorialGroupList().getEntry(1).getStudentList().add(new Student("Bob", 23, "24WMD", 3.5,false));
//        programmeList.getEntry(2).getTutorialGroupList().getEntry(1).getStudentList().add(new Student("Carmen" ,22, "24WMR", 2.5,false));
//        programmeList.getEntry(3).getTutorialGroupList().getEntry(1).getStudentList().add(new Student("Jack", 22, "24WMD", 3.0,false));
//    }
    
    private void addStudent() {
        Scanner input = new Scanner(System.in);
        char addStudentChoice;
        char mainPageChoice;
        int programChoose = 0 ;
        String programeCode;
        Student student = new Student(); 
        System.out.println("\n**************************************   FILL IN FORM BELOW TO ADD NEW STUDENT  **************************************  ");
        System.out.println("--- PART 1 ---");
        do {
            System.out.println("Select Education Level");
            System.out.println("1. Diploma ");
            System.out.println("2. Degree ");
            System.out.print("Enter your selected level : ");
            int educationChoice = input.nextInt();
            input.nextLine(); 
            System.out.print("Enter your name :");
            String name = input.nextLine();
            student.setName(name);       
            // Check if the student with the same name exists in the list
            boolean alreadyExists = false;
            for (int i = 1; i <= students.getNumberOfEntries(); i++) {
                if (students.getEntry(i).getName().equalsIgnoreCase(name)) {
                    alreadyExists = true;
                    break;
                }
            }
            if (alreadyExists) {
                System.out.println("Student with the same name already exists. Please enter a different name.");
            } else{           
                if(educationChoice == 1){
                    String studentIDDiploma = "24WMD";
                    student.setStudentIDLevel(studentIDDiploma); 
                }else{
                    String studentIDDegree = "24WMR";
                    student.setStudentIDLevel(studentIDDegree); 
                }      
                System.out.println("Your Student ID :" + student.getStudentIDLevel());   
                  System.out.println("\n--- PART 2 ---");
                System.out.print("Enter your age :");
                int age = input.nextInt();
                input.nextLine();
                student.setAge(age);
                
                if(educationChoice == 2){
                    System.out.print("Enter your diploma last semester CGPA :");
                    double cgpa = input.nextDouble();
                    input.nextLine();
                    student.setCgpa(cgpa);
                }  
                students.add(student); 
            } 
            System.out.print("\nDo you want to add another student? (Y/N) :");
            addStudentChoice = input.nextLine().charAt(0);               
        } while (addStudentChoice == 'Y' || addStudentChoice == 'y');
            System.out.println("******************************************************************");
            System.out.println("\nLastest Student List: \n");
            System.out.println(String.format("%-3s %-24s %-5s %-11s %-5s", "No", "Name", "Age", "StudentID", "CGPA")); 
            for(int i=0; i<students.getNumberOfEntries();i++){
                System.out.println((i+1) + ".  " + students.getEntry(i+1));
            }
            System.out.println("\n******************************************************************");
            System.out.print("\nBack To Student Main Page ? ('Y' / 'N') :");
            char backMain = input.nextLine().toUpperCase().charAt(0);
                if(backMain == 'Y'){
                    performStudent(); 
                }else{
                    addStudent(); 
                }
    }
    
    private void registerCourse() {
        Scanner input = new Scanner(System.in);  
        char contiRegChoice;
        int courseAmount = 0;
        int[] storeSelectedCourse; 
        int courseChoosen = 0;
        int score =0;
        boolean duplicate = false;
        boolean status = false;
        Course selectedCourse;
        System.out.println("\n**************************************   REGISTER STUDENT SECTION  ************************************** ");
        System.out.println("Student List:");
        System.out.println(String.format("%-20s %-5s %-10s %-6s", "Name", "Age", "StudentID", "Tutorial ID"));
        for (int n = 1; n <= programmeList.getNumberOfEntries(); n++) {
            for (int p = 1; p <= programmeList.getEntry(n).getTutorialGroupList().getNumberOfEntries(); p++) {
                TutorialGroup tutorialGroup = programmeList.getEntry(n).getTutorialGroupList().getEntry(p);
                for (int t = 1; t <= tutorialGroup.getStudentList().getNumberOfEntries(); t++) {
                    Student student = tutorialGroup.getStudentList().getEntry(t);
                    System.out.println(String.format("%-20s %-5d %-10s %-6s", student.getName(), student.getAge(), student.getStudentIDLevel(), tutorialGroup.getId()));
                }
            }
        }
        do{
            System.out.print("\nEnter student id to register course : ");
            String studentID = input.nextLine();
            boolean studentFound = false;
            outer:
            for(int i=1;  i<= programmeList.getNumberOfEntries(); i++){
                for(int j=1; j<= programmeList.getEntry(i).getTutorialGroupList().getNumberOfEntries(); j++){
                    for(int s=1; s<= programmeList.getEntry(i).getTutorialGroupList().getEntry(j).getStudentList().getNumberOfEntries(); s++){
                        Student student = programmeList.getEntry(i).getTutorialGroupList().getEntry(j).getStudentList().getEntry(s);
                        if (student.getStudentIDLevel().equals(studentID)) {
                            studentFound = true;
                            System.out.println("Student ID: " + programmeList.getEntry(i).getTutorialGroupList().getEntry(j).getStudentList().getEntry(s).getStudentIDLevel());
                            System.out.println("Name: " + programmeList.getEntry(i).getTutorialGroupList().getEntry(j).getStudentList().getEntry(s).getName());
                            System.out.println("Age: " + programmeList.getEntry(i).getTutorialGroupList().getEntry(j).getStudentList().getEntry(s).getAge());
                            System.out.println("ProgramID: " + programmeList.getEntry(i).getTutorialGroupList().getEntry(j).getId());
                            System.out.println("ProgramCode: " + programmeList.getEntry(i).getProgrammeCode());
                            System.out.println("\n*******************************************  Register course form  *******************************************");
                            System.out.println("---------------------------------------------   Course Offered   --------------------------------------------- ");
                            for (int c = 1; c <= programmeList.getEntry(i).getCourseList().getNumberOfEntries(); c++) {
                                Course course = programmeList.getEntry(i).getCourseList().getEntry(c);
                                System.out.printf("%d. %-10s %-40s %d %10s %10s %20s%n", c, 
                                course.getCourseCode(), course.getTitle(), course.getCreditHour(), 
                                course.getSemester(), course.getFaculty(), course.getStatus());
                            }
                            System.out.print("Enter amount of courses to be taken: ");
                            courseAmount = input.nextInt();
                            input.nextLine();
                            storeSelectedCourse = new int[courseAmount];
                            
                            if (courseAmount > programmeList.getEntry(i).getCourseList().getNumberOfEntries()) {
                                System.out.println("The number of courses entered exceeds the total number of courses offered!");
                                System.out.println("Please re-enter: ");
                                
                            }else{
                                for (int askQuesLoop = 1; askQuesLoop <= courseAmount; askQuesLoop++) {
                                    do{
                                        System.out.print("Enter your " + askQuesLoop + " course selection: ");
                                        courseChoosen = input.nextInt();
                                        input.nextLine(); 
                                        duplicate = false;

                                        System.out.print("Do u have previous result for course "+ "< "+ programmeList.getEntry(i).getCourseList().getEntry(courseChoosen).getTitle()+ "> ,(Y/N) :");
                                        char result = input.nextLine().toUpperCase().charAt(0);
                                        if(result == 'Y'){
                                           System.out.print("Please enter score:");
                                           score = input.nextInt();
                                           input.nextLine();
                                           status = true;
                                        }
                                        // Check for duplicates
                                        for (int d = 0; d < storeSelectedCourse.length; d++) {
                                            if (storeSelectedCourse[d] == courseChoosen) {
                                                System.out.println("You have already selected this course. Please choose a different one.");
                                                duplicate = true;
                                                break;
                                            }
                                        }
                                        // If no duplicate
                                        if (!duplicate) {
                                            storeSelectedCourse[askQuesLoop -1] = courseChoosen;
                                             if (status == true) {
                                                if (score >= 40 && score <= 50) {
                                                    selectedCourse = new Course(programmeList.getEntry(i).getCourseList().getEntry(courseChoosen).getCourseCode(),
                                                        programmeList.getEntry(i).getCourseList().getEntry(courseChoosen).getTitle(),
                                                        programmeList.getEntry(i).getCourseList().getEntry(courseChoosen).getCreditHour(),
                                                        programmeList.getEntry(i).getCourseList().getEntry(courseChoosen).getSemester(),
                                                        programmeList.getEntry(i).getCourseList().getEntry(courseChoosen).getFaculty(),"RESIT");
                                                        status = false;
                                                } else if (score < 40) {
                                                    selectedCourse = new Course(programmeList.getEntry(i).getCourseList().getEntry(courseChoosen).getCourseCode(),
                                                        programmeList.getEntry(i).getCourseList().getEntry(courseChoosen).getTitle(),
                                                        programmeList.getEntry(i).getCourseList().getEntry(courseChoosen).getCreditHour(),
                                                        programmeList.getEntry(i).getCourseList().getEntry(courseChoosen).getSemester(),
                                                        programmeList.getEntry(i).getCourseList().getEntry(courseChoosen).getFaculty(),"REPEAT");
                                                        status = false;
                                                } else {
                                                    selectedCourse = new Course(programmeList.getEntry(i).getCourseList().getEntry(courseChoosen).getCourseCode(),
                                                        programmeList.getEntry(i).getCourseList().getEntry(courseChoosen).getTitle(),
                                                        programmeList.getEntry(i).getCourseList().getEntry(courseChoosen).getCreditHour(),
                                                        programmeList.getEntry(i).getCourseList().getEntry(courseChoosen).getSemester(),
                                                        programmeList.getEntry(i).getCourseList().getEntry(courseChoosen).getFaculty(),
                                                        programmeList.getEntry(i).getCourseList().getEntry(courseChoosen).getStatus());
                                                }
                                            } else {
                                                selectedCourse = new Course(programmeList.getEntry(i).getCourseList().getEntry(courseChoosen).getCourseCode(),
                                                    programmeList.getEntry(i).getCourseList().getEntry(courseChoosen).getTitle(),
                                                    programmeList.getEntry(i).getCourseList().getEntry(courseChoosen).getCreditHour(),
                                                    programmeList.getEntry(i).getCourseList().getEntry(courseChoosen).getSemester(),
                                                    programmeList.getEntry(i).getCourseList().getEntry(courseChoosen).getFaculty(),
                                                    programmeList.getEntry(i).getCourseList().getEntry(courseChoosen).getStatus());
                                            }
                                            programmeList.getEntry(i).getTutorialGroupList().getEntry(j).getStudentList().getEntry(s).getCourseList().add(selectedCourse);

                                        } 
                                    }while(duplicate);   
                                }
                                System.out.println("\n------------------------------------------------------    Course Registered    ------------------------------------------------------");
                                System.out.println("Latest Chosen Course:");
                                for(int showCourse = 1; showCourse <= programmeList.getEntry(i).getTutorialGroupList().getEntry(j).getStudentList().getEntry(s).getCourseList().getNumberOfEntries();showCourse++){
                                    System.out.println(programmeList.getEntry(i).getTutorialGroupList().getEntry(j).getStudentList().getEntry(s).getCourseList().getEntry(showCourse));
                                }
                                System.out.println("\n> REGISTER SUCCESSFUL !! \n");
                                System.out.print("Check for student bill ? (Y/N): ");
                                char checkChoice = input.nextLine().toUpperCase().charAt(0);
                                if(checkChoice == 'Y'){
                                    calculateStudentFees();   
                                }else{
                                    break outer;
                                }
                            }
                        }
                    }
                    
                }
            }
            if (!studentFound) {
                System.out.println("Student with ID " + studentID + " not found in the tutorial group.");
            }
            System.out.print("\nContinue register for course ? ('Y' / 'N') :");
            contiRegChoice = input.nextLine().toUpperCase().charAt(0);
        }while(contiRegChoice == 'Y');
            System.out.print("\nBack To Student Main Page ? ('Y' / 'N') :");
            char back = input.nextLine().toUpperCase().charAt(0);
            if(back == 'Y'){
                System.out.print("Press enter to return main page..");
                input.nextLine();
                performStudent();    
            }else{
                System.out.print("Press enter to continue..");
                input.nextLine();
            }
        }
    

    private void containStudent() {
        Student student = new Student();
        if (students.isEmpty()) {
            return;
        }
        System.out.println("\n**************************************  SEARCH STUDENT SECTION  ************************************** ");
        Scanner input = new Scanner(System.in);
        int searchChoice;
        boolean searchStudent = false;

        System.out.println("\n");
        System.out.println("1. Search student from student list");
        System.out.println("2. Search student for registerd courses");
        System.out.println("3. Exit");
        System.out.print("Enter your choice: ");
        searchChoice = input.nextInt();

        switch (searchChoice) {
            case 1: // Search by Name
                System.out.print("Enter student name: ");
                input.nextLine();
                String name = input.nextLine();

                boolean foundByName = false;                
                for (int i = 1; i <= students.getNumberOfEntries(); i++) {
                    if (students.getEntry(i).getName().equalsIgnoreCase(name)) {  // ignore camel casing
                        System.out.println("\n--------------------   RESULT   -------------------");
                        System.out.println("Student found !");
                        System.out.println(String.format("%-20s %-10s %-10s %-10s", "Name", "Age", "StudentID", "CGPA"));
                        System.out.println(String.format("%-20s %-10s %-10s %-10s", students.getEntry(i).getName(), students.getEntry(i).getAge(), students.getEntry(i).getStudentIDLevel(), students.getEntry(i).getCgpa()));

                        foundByName = true;
                        students.search(students.getEntry(i));
                        System.out.println("\n");
                    }
                }

                if (!foundByName) {
                    System.out.println("\n--------------   RESULT   --------------");
                    System.out.println("No student found with the given name...\n\n");
                }
                break;
            case 2:
                System.out.print("Enter student id: ");
                input.nextLine();
                String studentID = input.nextLine();
                System.out.println("\n------------------------------   RESULT   --------------------------------");
                System.out.println(String.format("\n%-12s %-40s %-12s", "Name", "Course", "Status"));
                for(int i=1;  i<= programmeList.getNumberOfEntries(); i++){
                    for(int j=1; j<=programmeList.getEntry(i).getTutorialGroupList().getNumberOfEntries(); j++){
                        for(int s=1; s<=programmeList.getEntry(i).getTutorialGroupList().getEntry(j).getStudentList().getNumberOfEntries(); s++){
                            if (programmeList.getEntry(i).getTutorialGroupList().getEntry(j).getStudentList().getEntry(s).getStudentIDLevel().equals(studentID)) {
                                searchStudent = true;
                                for(int c =1; c<=programmeList.getEntry(i).getTutorialGroupList().getEntry(j).getStudentList().getEntry(s).getCourseList().getNumberOfEntries();c++){
                                    System.out.printf("%-12s %-40s %-13s\n",programmeList.getEntry(i).getTutorialGroupList().getEntry(j).getStudentList().getEntry(s).getName(),programmeList.getEntry(i).getTutorialGroupList().getEntry(j).getStudentList().getEntry(s).getCourseList().getEntry(c).getTitle(),
                                                                            programmeList.getEntry(i).getTutorialGroupList().getEntry(j).getStudentList().getEntry(s).getCourseList().getEntry(c).getStatus());
                                }
                            }
                        }
                    }
                }
                if(!searchStudent){
                    System.out.println("No student found with the given id...\n\n");
                }
            break;
            case 3:
                System.out.println("Exiting this section...");
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
                break;
        }
        
    } // Contain student end
    
    private void amendStudent() {
        Scanner input = new Scanner(System.in);
        double newCGPA = 0;
        double newCGPAIndividual = 0;
        int amendOption = 0;
        int studentFoundCount = 0;
        
        System.out.println("\n***  AMEND STUDENT SECTION  ***");
        System.out.println("Select an option");
        System.out.println("1. Amend few student at one time");
        System.out.println("2. Amend one student at one time");
        System.out.println("3. Exit");
        System.out.print("Your option: ");
        amendOption = input.nextInt();
        input.nextLine();           

        
        switch(amendOption) {
            case 1:
                System.out.print("\nEnter the CGPA to be amended: ");
                double cgpaToAmend = input.nextDouble();

                boolean foundAtLeastOne = false;
                System.out.println("\nStudent Which CGPA Is " + cgpaToAmend );
                System.out.println(String.format("%-3s %-30s %-5s %-10s %-5s", "No", "Name", "Age" ,"StudentID", "GPA"));
                for (int i = 1; i <= students.getNumberOfEntries(); i++) {
                    Student currentStudent = students.getEntry(i);
                    if (currentStudent.getCgpa() == cgpaToAmend) {
                        foundAtLeastOne = true;
                        studentFoundCount++;
                        System.out.println(String.format("%-3s %-30s %-5s %-10s %-5s", studentFoundCount, currentStudent.getName(), currentStudent.getAge() ,currentStudent.getStudentIDLevel(), currentStudent.getGpa()));
                    }
                }

                if (!foundAtLeastOne) {
                    System.out.println("No students found with the specified GPA.");
                } else {
                    do {
                        System.out.print("Enter the new CGPA: ");
                        newCGPA = input.nextDouble();
                        input.nextLine();
                        if (newCGPA < 0 || newCGPA > 4.0) {
                            System.out.println("Incorrect format, CGPA should be between 0 and 4.0.");
                        }
                    } while (newCGPA < 0 || newCGPA > 4.0);

                    for (int i = 1; i <= students.getNumberOfEntries(); i++) {
                        Student currentStudent = students.getEntry(i);
                        if (currentStudent.getCgpa() == cgpaToAmend) {
                            Student newStudent = new Student(currentStudent.getName(), currentStudent.getAge(), currentStudent.getStudentIDLevel(), newCGPA, false);
                            boolean amendmentResult = students.amend(currentStudent, newStudent);
                            if (amendmentResult) {
                                System.out.println("CGPA amended successfully for Student ID " + currentStudent.getStudentIDLevel());
                            } else {
                                System.out.println("Failed to amend CGPA for Student ID " + currentStudent.getStudentIDLevel());
                            }
                        }
                    }
                }
                break;
            case 2:
                boolean studentFound = false;
                String studentID = "";
                System.out.print("Enter student ID to be amended: ");
                studentID = input.nextLine();
                System.out.println("Information for student " + studentID);
                for (int i = 1; i <= students.getNumberOfEntries(); i++) {
                    if (students.getEntry(i).getStudentIDLevel().equals(studentID)) {
                        studentFound = true;
                        System.out.println("Student Name :" + students.getEntry(i).getName());
                        System.out.println("        ID :" + students.getEntry(i).getStudentIDLevel());
                        System.out.println("        Age :" + students.getEntry(i).getAge());
                        if (students.getEntry(i).getCgpa() == 0.0) {
                            System.out.println("        CGPA : none");
                        } else {
                            System.out.println("        CGPA :" + students.getEntry(i).getCgpa());
                        }
                        break; // No need to continue looping once the student is found
                    }
                }
                    
                if (!studentFound) {
                    System.out.println("No students found with the ID entered.");
                } else {
                        do {
                            System.out.print("Enter the new CGPA for " + studentID + ": ");
                            newCGPAIndividual = input.nextDouble();
                            input.nextLine();

                            if (newCGPAIndividual < 0 || newCGPAIndividual > 4.0) {
                                System.out.println("Incorrect format, CGPA should be between 0 and 4.0.");
                            }
                        } while (newCGPAIndividual < 0 || newCGPAIndividual > 4.0);

                        //replace the edited student's gpa
                        for (int i = 1; i <= students.getNumberOfEntries(); i++) {
                            Student currentStudent = students.getEntry(i);
                            if (currentStudent.getStudentIDLevel().equals(studentID)) {
                                Student newStudent = new Student(currentStudent.getName(), currentStudent.getAge(), currentStudent.getStudentIDLevel(), newCGPAIndividual, false);
                                boolean amendmentResult = students.amend(currentStudent, newStudent);
                                if (amendmentResult) {
                                    System.out.println("GPA amended successfully for Student ID " + currentStudent.getStudentIDLevel());
                                } else {
                                    System.out.println("Failed to amend GPA for Student ID " + currentStudent.getStudentIDLevel());
                                }
                            }
                        }
                    }
                break;
            case 3:
                System.out.println("Exiting this section...");
                break;
            default:
                System.out.println("Invalid option.");
                break;
        }
   
    }

    private void deleteStudent() {
        Student student;
        Scanner input = new Scanner(System.in);
        int i = 1;
        int j = 1;
        int s = 1;
        int c = 1;
        String studentID ="";
        System.out.println("\n**************************************   DELETE STUDENT SECTION  ************************************** ");
        System.out.println("\n Enter your selection");
        System.out.println("1. Delete by view student list");
        System.out.println("2. Delete student from course");
        System.out.println("3. Exit");
        System.out.print(" Your selection : ");
        int selection = input.nextInt();
        input.nextLine(); 
        switch(selection){
            case 1:      
                int studentIndex;
                System.out.println("\nForm Of Student List: ");
                System.out.println(String.format("%-3s %-24s %-5s %-11s %-5s", "No", "Name", "Age", "StudentID", "CGPA")); 
                for(int stdEnteries = 0; stdEnteries < students.getNumberOfEntries(); stdEnteries++){
                    System.out.println((stdEnteries + 1) + ".  " + students.getEntry(stdEnteries + 1));
                }
                System.out.print("\nEnter the *No* for the student to be deleted (1, 2, 3): ");   
                studentIndex = input.nextInt();
                input.nextLine();
                if (studentIndex >= 1 && studentIndex <= students.getNumberOfEntries()) {
                    students.removeAt(studentIndex);
                    System.out.println("Student with No. " + studentIndex + " has been deleted successfully.");
                } else {
                    System.out.println("\nInvalid *No* entered. No student deleted.\n");
                }
                break;
            case 2: 
                System.out.println(String.format("%32s", "\nCourse List "));
                System.out.println("--------------------------------------------------------------------------------------");
                System.out.println(String.format("%3s %-18s %-32s", "No", "Course Code", "Course"));
                for (int loopCourse = 1; loopCourse <= courseList.getNumberOfEntries(); loopCourse++) {
                    System.out.println(String.format("%-3d %-18s %-32s", loopCourse, courseList.getEntry(loopCourse).getCourseCode(), courseList.getEntry(loopCourse).getTitle()));
                }
                char contiRemove;
                do{
                    System.out.print("Select course index (eg. 1 / 2 / 3): ");
                    int selectedCourseIndex = input.nextInt();
                    input.nextLine();
                    if (selectedCourseIndex >= 1 && selectedCourseIndex <= courseList.getNumberOfEntries()) {
                        Course selectedCourse = courseList.getEntry(selectedCourseIndex);

                        System.out.println("\nStudents enrolled in the course <" + selectedCourse.getTitle() + "> :");
                        boolean studentFound = false;
                        // Display all available student in the selected course list
                        for (i = 1; i <= programmeList.getNumberOfEntries(); i++) {
                            for (j = 1; j <= programmeList.getEntry(i).getTutorialGroupList().getNumberOfEntries(); j++) {
                                TutorialGroup tutorialGroup = programmeList.getEntry(i).getTutorialGroupList().getEntry(j);
                                for (s = 1; s <= tutorialGroup.getStudentList().getNumberOfEntries(); s++) {
                                    student = tutorialGroup.getStudentList().getEntry(s);
                                    for(c = 1; c <= student.getCourseList().getNumberOfEntries(); c++){
                                        Course course = student.getCourseList().getEntry(c);
                                        if(course.getTitle().equals(selectedCourse.getTitle())){
                                            studentFound = true;
                                            System.out.println(String.format("%-3d %-20s %-5d %-10s", (c), student.getName(), student.getAge(), student.getStudentIDLevel()));
                                        }
                                    } 
                                }
                            }
                        }
                        if(studentFound){
                            System.out.print("Enter student ID to be remove: ");
                           studentID = input.nextLine();
                        }
                        
                        boolean courseFound = false;
                        for (i = 1; i <= programmeList.getNumberOfEntries(); i++) {
                            for (j = 1; j <= programmeList.getEntry(i).getTutorialGroupList().getNumberOfEntries(); j++) {
                                TutorialGroup tutorialGroup = programmeList.getEntry(i).getTutorialGroupList().getEntry(j);
                                for (s = 1; s <= tutorialGroup.getStudentList().getNumberOfEntries(); s++) {
                                    student = tutorialGroup.getStudentList().getEntry(s);
                                    if(student.getStudentIDLevel().equals(studentID)){
                                        for(c = 1; c <= student.getCourseList().getNumberOfEntries(); c++){
                                            Course course = student.getCourseList().getEntry(c);
                                            if(course.getTitle().equals(selectedCourse.getTitle())){
                                                courseFound = true;
                                                studentFound = true;
                                                student.getCourseList().remove(course);
                                                System.out.print("Student with ID " +student.getStudentIDLevel() + "have deleted from " + selectedCourse.getTitle() );
                                               
                                            }
                                            
                                        } 
                                        studentFound = true;
                                    }
                                }
                            }
                        }
                        if (!studentFound) {
                            System.out.println("> Students does not enrolled in this course.");
                        }
                        if (!courseFound) {
                            System.out.println("> Student entered are not in the course.");
                        }
                    } else {
                        System.out.println("\nInvalid course selection.");
                    }
                    System.out.print("\nContinue remove student? ('Y' / 'N') :");
                    contiRemove = input.nextLine().toUpperCase().charAt(0);
                    
                }while(contiRemove == 'Y');
                performStudent(); 
                break;
            case 3:
                System.out.println("Exiting this section...");
                performStudent();
            default:
                System.out.println("Invalid choice. Please try again.");   
                break;
        }  
  
    } // Delete student end

 
    private void calculateStudentFees() {
        Scanner input = new Scanner(System.in);
        int courseFees = 0;
        System.out.println("\n********************************   STUDENT's TOTAL FEES FOR REGISTERED COURSE   ********************************");
        System.out.print("Enter student ID :");
        String studentID = input.nextLine();
                    
       int totalFeesAmount = 0;
       System.out.println("                               ___ _  ___   _____ ___ ___ ___");
       System.out.println("                              |_ _| \\| \\ \\ / / _ \\_ _/ __| __|");
       System.out.println("                              | || .` |\\ V / (_) | | (__| _| ");
       System.out.println("                              |___|_|\\_| \\_/ \\___/___\\___|___|\n");
       System.out.println("_____________________________________________________________________________________________________________________\n");
        System.out.println(String.format("%-12s %-45s %-12s %10s %10s", "Name", "Course", "Status", "Credit Hour", "Amount"));
        for(int i=1;  i<= programmeList.getNumberOfEntries(); i++){
            for(int j=1; j<=programmeList.getEntry(i).getTutorialGroupList().getNumberOfEntries(); j++){
                for(int s=1; s<=programmeList.getEntry(i).getTutorialGroupList().getEntry(j).getStudentList().getNumberOfEntries(); s++){
                    if (programmeList.getEntry(i).getTutorialGroupList().getEntry(j).getStudentList().getEntry(s).getStudentIDLevel().equals(studentID)) {
                        Student student = programmeList.getEntry(i).getTutorialGroupList().getEntry(j).getStudentList().getEntry(s);
                            for (int c = 1; c <= student.getCourseList().getNumberOfEntries(); c++) {
                                Course course = student.getCourseList().getEntry(c);

                                for(int pc = 1; pc <= programmeList.getEntry(i).getCourseList().getNumberOfEntries(); pc++){   
                                    if(course.getCourseCode().equals(programmeList.getEntry(i).getCourseList().getEntry(pc).getCourseCode())){
                                            course.setCreditHour(programmeList.getEntry(i).getCourseList().getEntry(pc).getCreditHour());
                                            course.setTitle(programmeList.getEntry(i).getCourseList().getEntry(pc).getTitle());
                                            if(!course.getStatus().toUpperCase().equals("RESIT")&& !course.getStatus().toUpperCase().equals("REPEAT")){
                                                course.setStatus(programmeList.getEntry(i).getCourseList().getEntry(pc).getStatus());
                                            }
                                    }         
                                }
                                courseFees = course.getCreditHour() * PRICE_PER_HOUR;
                                totalFeesAmount += courseFees;
                                System.out.printf("%-12s %-45s %-10s %10d %10d\n",
                                student.getName(),course.getTitle(), course.getStatus(), course.getCreditHour(), courseFees);
                            }
                        System.out.println("The total fees amount : (RM)" + totalFeesAmount);
                        System.out.println("\n_____________________________________________________________________________________________________________________");
                    }
                }
            }
        }
        System.out.print("\nPress ENTER to continue..");    
        input.nextLine();
    }
    
    private void filterStudent(){
        Scanner input = new Scanner(System.in);
        int filterChoice = 0;
        boolean validInput = false;
        
            System.out.println("\n***  FILTER STUDENT SECTION  ***");
            System.out.println("Select your choice");
            System.out.println("1. Filter student status (eg. MAIN, ELECTIVE, RESIT, REPEAT) for a course");
            System.out.println("2. Filter total number of student for a course based on (WMD / WMR)");
            System.out.println("3. Exit to student main page"); 
            System.out.print("Your choice : ");
            filterChoice = input.nextInt();
            input.nextLine(); 
            switch(filterChoice){
                case 1:
                    System.out.println("\n   Course available");
                    System.out.println("-------------------------");
                    for (int i = 1; i <= courseList.getNumberOfEntries(); i++) {
                        String courseCode = courseList.getEntry(i).getCourseCode();
                        String courseName = courseList.getEntry(i).getTitle();
                        System.out.printf("%-8s %-45s\n", courseCode , courseName);
                    }
                    System.out.print("Enter course code to be filter :");
                    String courseCodeEntered = input.nextLine();

                    System.out.println("Select filter type : --");
                    System.out.println("    MAIN");
                    System.out.println("    ELECTIVE");
                    System.out.println("    MAIN/ELECTIVE");
                    System.out.println("    RESIT");
                    System.out.println("    REPEAT");
                    System.out.print("Enter type :");
                    String filterCourseType = input.nextLine();
                    
                    System.out.println("\nStudent list for the selected programme ( + status)");
                    boolean foundCourse = false;
                    boolean foundStudents = false;
                    for (int i = 1; i <= programmeList.getNumberOfEntries(); i++) {
                        for (int t = 1; t <= programmeList.getEntry(i).getTutorialGroupList().getNumberOfEntries(); t++) {
                            TutorialGroup tutorialGroup = programmeList.getEntry(i).getTutorialGroupList().getEntry(t);
                            for (int s = 1; s <= tutorialGroup.getStudentList().getNumberOfEntries(); s++) {
                                Student student = tutorialGroup.getStudentList().getEntry(s);
                                for(int c = 1; c <= student.getCourseList().getNumberOfEntries(); c++){
                                    Course course = student.getCourseList().getEntry(c);
                                    if(course.getCourseCode().equalsIgnoreCase(courseCodeEntered)){
                                        foundCourse = true;
                                        student.getCourseList().toString();
                                        for(int fs = 1; fs <= student.getCourseList().getNumberOfEntries(); fs++){
                                            if(student.getCourseList().getEntry(fs).getStatus().equalsIgnoreCase(filterCourseType.toUpperCase())){
                                                foundStudents = true;
                                                System.out.println(String.format("%-3d %-20s %-5d %-10s", (fs), student.getName(), student.getAge(), student.getStudentIDLevel()));
                                            }
                                        }
                                    }
                                } 
                            }
                        }
                    } 
                    if (!foundCourse) {
                        System.out.println("No such course found.");
                    } 
                    if (!foundStudents) {
                        System.out.println("No students enrolled in this course with the specified status.");
                    }
                    break;
                 case 2:
                      String whichLevel = "";
                        int countWMDstudent = 0;
                        int countWMRstudent = 0;
                        do {
                            System.out.println("\nDo you want to filter for which level?");
                            System.out.println("1. Diploma Level, enter 'WMD'");
                            System.out.println("2. Degree Level, enter 'WMR'");
                            System.out.print("\nEnter your choice: ");
                            whichLevel = input.nextLine().trim().toUpperCase();

                            if (whichLevel.equals("WMD") || whichLevel.equals("WMR")) {
                                validInput = true;
                                String courseLevel = (whichLevel.equals("WMD")) ? "D" : "R";
                                System.out.println(String.format("%-15s %-40s %-5s", "COURSE CODE", "COURSE", "TOTAL NO"));
                                for (int i = 1; i <= courseList.getNumberOfEntries(); i++) {
                                    String courseCode = courseList.getEntry(i).getCourseCode();
                                    String courseName = courseList.getEntry(i).getTitle();
                                    int countStudents = 0;

                                    for (int p = 1; p <= programmeList.getNumberOfEntries(); p++) {
                                        for (int j = 1; j <= programmeList.getEntry(p).getTutorialGroupList().getNumberOfEntries(); j++) {
                                            for (int s = 1; s <= programmeList.getEntry(p).getTutorialGroupList().getEntry(j).getStudentList().getNumberOfEntries(); s++) {
                                                Student student = programmeList.getEntry(p).getTutorialGroupList().getEntry(j).getStudentList().getEntry(s);
                                                if (student.getStudentIDLevel().charAt(4) == courseLevel.charAt(0)) {
                                                    for (int c = 1; c <= student.getCourseList().getNumberOfEntries(); c++) {
                                                        Course course = student.getCourseList().getEntry(c);
                                                        if (course.getCourseCode().equals(courseCode)) {
                                                            countStudents++;
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                    System.out.printf("%-15s %-45s %-7d\n", courseCode, courseName, countStudents);
                                }
                                System.out.print("\nPress ENTER to continue..");
                                input.nextLine();
                            } else {
                                System.out.println("** Invalid input! Please enter either 'WMD' or 'WMR'. **");
                            }
                    } while (!validInput);
                    break;
                case 3:
                    break;
                default: System.out.println("** Invalid input!");
                break;
              
            }
        
    }
    
    private void summaryReport() {
        Scanner input = new Scanner(System.in);
        LocalDateTime myDateObj = LocalDateTime.now();  
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("E, dd MMM yyyy HH:mm:ss");  
        String formattedDate = myDateObj.format(myFormatObj); 
        int countMain = 0;
        int countElective = 0;
        int countResit = 0;
        int countRepeat = 0;
        int countP2Repeat = 0;
        String type = "";
        String typeP2 = "";
        double countRepeatPercentage = 0;
        System.out.println("**************************************************************************************************************************************************************");
        System.out.println(String.format("%105s", "TUNKU ABDUL RAHMAN UNIVERSITY OF MANAGEMENT AND TECHNOLOGY\n")); 
        System.out.println(String.format("%90s", "STUDENT MANAGEMENT SUBSYSTEM\n"));  
        System.out.println(String.format("%86s", "STUDENT SUMMARY REPORT")) ;   
        System.out.println(String.format("%91s", "----------------------------------")) ;  
        System.out.println(String.format("%6s ", "Generated at : " + formattedDate)) ;  
        System.out.println(String.format("%6s ", "\n< The total number of student for each course based on different status>"));  
        System.out.println(String.format("%-15s %-40s %-12s %-16s %-6s %10s %10s %10s", "COURSE CODE", "COURSE", "CREDIT HOUR", "FACULTY" ,"MAIN", "ELECTIVE", "RESIT", "REPEAT"));  
        
        // Display course
        for (int i = 1; i <= courseList.getNumberOfEntries(); i++) {
            String courseCode = courseList.getEntry(i).getCourseCode();
            String courseName = courseList.getEntry(i).getTitle();
            int courseCreditHr = courseList.getEntry(i).getCreditHour();
            String courseFaculty = courseList.getEntry(i).getFaculty();
            
            countMain = 0;
            countElective = 0;
            countResit = 0;
            countRepeat = 0;
            countP2Repeat = 0;
    
            // Calculate student
            for (int p = 1; p <= programmeList.getNumberOfEntries(); p++) {
                for (int j = 1; j <= programmeList.getEntry(p).getTutorialGroupList().getNumberOfEntries(); j++) {
                    for (int s = 1; s <= programmeList.getEntry(p).getTutorialGroupList().getEntry(j).getStudentList().getNumberOfEntries(); s++) {
                        Student student = programmeList.getEntry(p).getTutorialGroupList().getEntry(j).getStudentList().getEntry(s);
                        for (int c = 1; c <= student.getCourseList().getNumberOfEntries(); c++) {
                            Course course = student.getCourseList().getEntry(c);
                            if (course.getCourseCode().equals(courseCode)) {
                                type = course.getStatus();
                                if(type == "MAIN"){
                                    countMain++;
                                }else if(type == "ELECTIVE"){
                                    countElective++;
                                }else if(type == "RESIT"){
                                    countResit++;
                                }else{
                                    countRepeat++;
                                    countP2Repeat++;
                                }
                            }
                        }
                    }
                }
            }  
            System.out.printf("%-15s %-45s %-7s %-12s %6d %10d %12d %10d\n",courseCode, courseName, courseCreditHr, courseFaculty, countMain, countElective, countResit, countRepeat);       
        }

        System.out.println(String.format("%135s", "PAGE 1"));
        System.out.println("============================================================================================================================================================");
        System.out.println(String.format("%6s", "\n < Analysis course repeat >"));
        System.out.println(String.format("%-15s %-40s %25s %12s", "COURSE CODE", "COURSE", "TOTAL REPEAT STUDENT", "Percentage%"));
        part2SummaryReport();
        System.out.println(String.format("%138s", "PAGE 2\n"));
        System.out.println(String.format("%90s", "END OF STUDENT SUMMARY REPORT"));
        System.out.println("**************************************************************************************************************************************************************\n");
        System.out.print("\nPress ENTER to continue..");
        input.nextLine();
    }
 
    private void displayAllStudent() {
        Scanner input = new Scanner(System.in);
        System.out.println("******************************************************************");
        System.out.println("\nForm Of Student List: \n");
        System.out.println(String.format("%-3s %-24s %-5s %-11s %-5s", "No", "Name", "Age", "StudentID", "CGPA"));
        for(int i=0; i<students.getNumberOfEntries();i++){
            System.out.println((i+1) + ".  " + students.getEntry(i+1));
        }
        System.out.println("\n******************************************************************");
        System.out.print("\nPress ENTER to continue..");
        input.nextLine();
    } // display all available students' details end
    
    private void part2SummaryReport(){
        int countMain2 = 0;
        int countElective2 = 0;
        int countResit2 = 0;
        int countRepeat2 = 0;
        double repeatPercentage;
        int totalStudents = 0;
        double maxRepeatPercentage = 0;
        String maxRepeatCourseCode = "";
        String maxRepeatCourseName = "";
    
    for (int p2i = 1; p2i <= courseList.getNumberOfEntries(); p2i++) {
        String courseCode = courseList.getEntry(p2i).getCourseCode();
        String courseName = courseList.getEntry(p2i).getTitle();

        countMain2 = 0;
        countElective2 = 0;
        countResit2 = 0;
        countRepeat2 = 0;

        for (int p2 = 1; p2 <= programmeList.getNumberOfEntries(); p2++) {
            for (int j2 = 1; j2 <= programmeList.getEntry(p2).getTutorialGroupList().getNumberOfEntries(); j2++) {
                for (int s2 = 1; s2 <= programmeList.getEntry(p2).getTutorialGroupList().getEntry(j2).getStudentList().getNumberOfEntries(); s2++) {
                    Student student = programmeList.getEntry(p2).getTutorialGroupList().getEntry(j2).getStudentList().getEntry(s2);
                    for (int c = 1; c <= student.getCourseList().getNumberOfEntries(); c++) {
                        Course course = student.getCourseList().getEntry(c);
                        if (course.getCourseCode().equals(courseCode)) {
                            String type = course.getStatus();
                            switch (type) {
                                case "MAIN":
                                    countMain2++;
                                    break;
                                case "ELECTIVE":
                                    countElective2++;
                                    break;
                                case "RESIT":
                                    countResit2++;
                                    break;
                                case "REPEAT":
                                    countRepeat2++;
                                    break;
                            }
                        }
                    }
                }
            }
        }
        totalStudents = countMain2 + countElective2 + countResit2 + countRepeat2;
        repeatPercentage = totalStudents > 0 ? (double) countRepeat2 / totalStudents * 100 : 0;
        
        if (repeatPercentage > maxRepeatPercentage) {
            maxRepeatPercentage = repeatPercentage;
            maxRepeatCourseCode = courseCode;
            maxRepeatCourseName = courseName;
        }
        System.out.printf("%-15s %-45s %14d %15.2f\n", courseCode, courseName, countRepeat2, repeatPercentage);
    }
    System.out.println("\nCourse with the highest repeat percentage:");
    System.out.printf("Course Code: %s\nCourse Name: %s\nRepeat Percentage: %.2f%%\n",
                      maxRepeatCourseCode, maxRepeatCourseName, maxRepeatPercentage);
    }
}