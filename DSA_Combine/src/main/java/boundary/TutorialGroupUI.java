/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package boundary;

import client.StudentCtrl;
import client.TutorialGroupCtrl;
import adt.SortedDoublyLinkedList;
import adt.SortedLinkedListInterface;
import client.CourseCtrl;
import entity.Course;
import entity.Programme;
import entity.Student;
import entity.TutorialGroup;
import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
/**
 *
 * @author Lee Kar Xin
 */
public class TutorialGroupUI {

    Scanner scanner = new Scanner(System.in);
    public static TutorialGroupCtrl tutorialGroupController = new TutorialGroupCtrl();
    public static SortedLinkedListInterface<Programme> programmeList = CourseCtrl.programmeList;
    public static SortedLinkedListInterface<Student> studentList = StudentCtrl.students;
    public static SortedLinkedListInterface<Course> courseList= CourseCtrl.courseList;
    public static SortedLinkedListInterface <Programme> tutorialGroupList =  tutorialGroupController.preAddAll();
    
    public void displayMenu() {
            while (true) {
        System.out.println("====================================================================");
        System.out.println("\tTutorial Group Management Subsystem");
        System.out.println("====================================================================\n");
        System.out.println("\t1. Add a tutorial group");
        System.out.println("\t2. Remove a tutorial group");
        System.out.println("\t3. List all tutorial groups");
        System.out.println("\t4. Add students to a tutorial group");
        System.out.println("\t5. Remove a student from a tutorial group");
        System.out.println("\t6. Change the tutorial group for a student");
        System.out.println("\t7. List all students in a tutorial group and a programme");
        System.out.println("\t8. Merge tutorial groups");
        System.out.println("\t9. Generate summary reports");
        System.out.println("\t0. Exit\n");
        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1:
                addTutorialGroup();
                break;
            case 2:
                removeTutorialGroup();
                break;
            case 3:
                listTutorialGroups();
                break;
            case 4:
                addStudentsToTutorialGroup();
                break;
            case 5:
                removeStudentFromTutorialGroup();
                break;
            case 6:
                changeTutorialGroupForStudent();
                break;
            case 7:
                listStudentsInTutorialGroupAndProgramme();
                break;
            case 8:
                mergeTutorialGroups();
                break;
            case 9:
                generateSummaryReport();
                break;
            case 0:
                System.out.println("Exiting Tutorial Group Management Subsystem...");
                return;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
            }
    }

   private Programme selectProgramme() {
    System.out.println("Select a programme:");
    System.out.println("Programme Code\t   Name\t\t\t\t\t\t\t\t\t\t     Faculty\t\tMax Tutorial Group(s)\tCurrent Tutorial Group(s)");
    System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------------------------------");
   
    for (int i = 1; i <= programmeList.getNumberOfEntries(); i++) {
        Programme programme = programmeList.getEntry(i);
        String currentTgs = tutorialGroupController.displayCurrentTg(programme);
        System.out.printf("%-18s %-80s %-25s %-15d %-30s%n", programme.getProgrammeCode(), programme.getName(), programme.getFaculty(), programme.getMaxTutorialGrp(), currentTgs);
    }
    
    System.out.print("Enter Programme Code: ");
    String programmeId = scanner.nextLine().trim().toUpperCase();

    Programme selectedProgramme = tutorialGroupController.findProgrammeById(programmeId, programmeList);

    if (selectedProgramme == null) {
        System.out.println("Error: Programme not found. Please try again.");
        return selectProgramme();
    }

    return selectedProgramme;
}


    private TutorialGroup selectTutorialGroup(Programme selectedProgramme) {
        System.out.println("\nSelect a tutorial group:");

        System.out.println("Index\tTutorial Group ID\tMaximum Students\tCurrent Total Students");
        System.out.println("------------------------------------------------------------------------");
        System.out.println(tutorialGroupController.getTutorialGroupList(selectedProgramme));

        System.out.print("Enter the index of the tutorial group: ");
        int index = Integer.parseInt(scanner.nextLine());

        SortedLinkedListInterface<TutorialGroup> tutorialGroups = selectedProgramme.getTutorialGroupList();
        if (index >= 1 && index <= tutorialGroups.getNumberOfEntries()) {
            return tutorialGroups.getEntry(index);
        } else {
            System.out.println("Error: Invalid index. Please try again.");
        
            return null;
            
        }
    }

    public void addTutorialGroup() {
        
       System.out.println("Select a programme:");
    System.out.println("Programme Code\t   Name\t\t\t\t\t\t\t\t\t\t     Faculty\t\tMax Tutorial Group(s)\tCurrent Tutorial Group(s)");
    System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------------------------------");
   
    for (int i = 1; i <= programmeList.getNumberOfEntries(); i++) {
        Programme programme = programmeList.getEntry(i);
        String currentTgs = tutorialGroupController.displayCurrentTg(programme);
        System.out.printf("%-18s %-80s %-25s %-15d %-30s%n", programme.getProgrammeCode(), programme.getName(), programme.getFaculty(), programme.getMaxTutorialGrp(), currentTgs);
    }
    
 System.out.print("Enter the Programme Code that you want to add a tutorial group for: ");
        String selectedProgrammeId = scanner.nextLine().trim().toUpperCase();

        Programme selectedProgramme = null;
        for (int i = 1; i <= programmeList.getNumberOfEntries(); i++) {
            Programme programme = programmeList.getEntry(i);
            if (selectedProgrammeId.equals(programme.getProgrammeCode())) {
                selectedProgramme = programme;
                break;
            }
        }

        if (selectedProgramme != null) {
            // Check the maximum tutorial group capacity
            int maxTutorialGroups = selectedProgramme.getMaxTutorialGrp();
            int currentTutorialGroups = selectedProgramme.getTutorialGroupList().getNumberOfEntries();
            if (currentTutorialGroups >= maxTutorialGroups) {
                System.out.println("The selected programme has reached its maximum tutorial group capacity.");
              
                return;
            }

            String tutorialGroupId = "";
            boolean uniqueId = false;
            while (!uniqueId) {
                System.out.print("Enter the ID of the tutorial group: ");
                tutorialGroupId = scanner.nextLine().trim().toUpperCase();
                uniqueId = true;

                for (int i = 1; i <= selectedProgramme.getTutorialGroupList().getNumberOfEntries(); i++) {
                    if (tutorialGroupId.equals(selectedProgramme.getTutorialGroupList().getEntry(i).getId())) {
                        uniqueId = false; // ID already exists
                        System.out.println("Tutorial group ID '" + tutorialGroupId + "' already exists in the selected program. Please choose a different ID.");
                        break;
                    }
                }
            }

            System.out.print("Confirm to add the tutorial group with ID '" + tutorialGroupId + "' (Y/N): ");
            String confirm = scanner.nextLine().trim().toUpperCase();
            if (!confirm.equals("Y")) {
                System.out.println("Cancelled add tutorial group.");
                
                return;
            }

            int numStudents = 25;
            tutorialGroupId = selectedProgramme.getProgrammeCode() + tutorialGroupId;
            
            boolean added = tutorialGroupController.addTutorialGroup(selectedProgramme, tutorialGroupId, numStudents);
            if (added) {
                System.out.println("Tutorial group added successfully.");
            } else {
                System.out.println("Failed to add tutorial group. Please try again.");
            }
        } else {
            System.out.println("Selected programme not found.");
        }

        return;
    }

    public void removeTutorialGroup() {
        System.out.println("Select a programme:");
    System.out.println("Programme Code\t   Name\t\t\t\t\t\t\t\t\t\t     Faculty\t\tMax Tutorial Group(s)\tCurrent Tutorial Group(s)");
    System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------------------------------");
   
    for (int i = 1; i <= programmeList.getNumberOfEntries(); i++) {
        Programme programme = programmeList.getEntry(i);
        String currentTgs = tutorialGroupController.displayCurrentTg(programme);
        System.out.printf("%-18s %-80s %-25s %-15d %-30s%n", programme.getProgrammeCode(), programme.getName(), programme.getFaculty(), programme.getMaxTutorialGrp(), currentTgs);
    }
    
 System.out.print("Enter the Programme Code that you want to remove a tutorial group from: ");
        String selectedProgrammeId = scanner.nextLine().trim().toUpperCase();

        Programme selectedProgramme = null;
        for (int i = 1; i <= programmeList.getNumberOfEntries(); i++) {
            if (selectedProgrammeId.equals(programmeList.getEntry(i).getProgrammeCode())) {
                selectedProgramme = programmeList.getEntry(i);
                break;
            }
        }

        if (selectedProgramme != null) {
            System.out.print("Enter the ID of the tutorial group to remove: ");
            String tutorialGroupId = scanner.nextLine().trim().toUpperCase();

            System.out.print("Confirm to remove the tutorial group with ID '" + tutorialGroupId + "' (Y/N): ");
            String confirm = scanner.nextLine().trim().toUpperCase();
            if (!confirm.equals("Y")) {
                System.out.println("Cancelled remove tutorial group.");
                return;
                
            }

            boolean removed = tutorialGroupController.removeTutorialGroup(selectedProgramme, tutorialGroupId);
            if (removed) {
                System.out.println("Tutorial group removed successfully.");
            } else {
                System.out.println("Tutorial group ID '" + tutorialGroupId + "' not found in the selected program.");
            }
        } else {
            System.out.println("Selected programme not found.");
        }

        return;
    }

   public void listTutorialGroups() {
         Programme selectedProgramme = selectProgramme();


        if (selectedProgramme!=null) {
            System.out.println("\n\t\t\t\tList of Tutorial Groups");
            System.out.println("----------------------------------------------------------------------------------------------");
            System.out.println("Index \t Tutorial Group ID \tMaximum Students \tCurrent Total Students");
            System.out.println("----------------------------------------------------------------------------------------------");
            SortedLinkedListInterface<TutorialGroup> tutorialGroupList = selectedProgramme.getTutorialGroupList();

             for (int i = 1; i <= tutorialGroupList.getNumberOfEntries(); i++) {
            TutorialGroup tutorialGroup = tutorialGroupList.getEntry(i);
            System.out.println(i + "\t   " + tutorialGroup.getId() + "\t\t\t      " + tutorialGroup.getMaxStudents() + "\t\t\t      " + tutorialGroup.getCurrentNumStud());
        }
        } else {
            System.out.println("No programmes found.");
        }
        System.out.println("\nPress <ENTER> to continue...");
        scanner.nextLine();
        return;
       
    }


    public void addStudentsToTutorialGroup() {

        Programme selectedProgramme = selectProgramme();
        if (selectedProgramme != null) {

            TutorialGroup selectedTutorialGroup = selectTutorialGroup(selectedProgramme);
            if (selectedTutorialGroup != null) {


        System.out.println("\nStudents that can be added:");
    System.out.println("Index\tStudent ID\tName");
    System.out.println("----------------------------------------------");
           // Display the filtered student list
            SortedLinkedListInterface<Student> filteredStudents = tutorialGroupController.getFilteredStudents(programmeList, selectedProgramme);

                //check 他还有没有student可以add
                 if (studentList.getNumberOfEntries() - tutorialGroupController.countStudWithTg(programmeList) == 0) {
                System.out.println("All students have been assigned to a tutorial group.");
               
                return;
            }

                System.out.println("\nSelect an option:");
                System.out.println("1. Add ONE student to the tutorial group");
                System.out.println("2. Add MULTIPLE students to the tutorial group");
                System.out.println("3. Add ALL students without a tutorial group");
                System.out.print("\nEnter your choice: ");

                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        addOneStudent(selectedProgramme,selectedTutorialGroup, filteredStudents);
                        break;
                    case 2:
                        addMultipleStudents(selectedProgramme, selectedTutorialGroup,filteredStudents);
                        break;
                    case 3:
                        addAllStudents(selectedProgramme, selectedTutorialGroup,filteredStudents);
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                        addStudentsToTutorialGroup();
                }
            } else {

                System.out.println("Invalid tutorial group selection.");
                return;

            }
        } else {
            System.out.println("Invalid programme selection.");
                return;
        }

    }

private void addOneStudent(Programme selectedProgramme, TutorialGroup selectedTutorialGroup, SortedLinkedListInterface<Student> filteredStudents) {
    System.out.print("\nEnter the index of the student you want to add: ");
    int studentIndex = scanner.nextInt();
    scanner.nextLine();

    if (studentIndex >= 1 && studentIndex <= filteredStudents.getNumberOfEntries()) {
        // Get the student based on the selected index from the filtered list
        Student student = filteredStudents.getEntry(studentIndex);

        // Validate if the student exists and is not assigned to any tutorial group
        if (student != null) {
            if (!tutorialGroupController.isStudHaveGroup(student, selectedProgramme, programmeList)) {
                if (selectedTutorialGroup.getCurrentNumStud() < selectedTutorialGroup.getMaxStudents()) {
                    selectedTutorialGroup.addStudents(student);
                    selectedTutorialGroup.setCurrentNumStud(selectedTutorialGroup.getCurrentNumStud() + 1);
                    tutorialGroupController.updateStudentTg(student,selectedTutorialGroup);
                    System.out.println("Student added to the tutorial group.");
                } else {
                    System.out.println("Error: The selected tutorial group has reached its maximum capacity.");
                }
            } else {
                System.out.println("Error: The selected student is already assigned to a tutorial group.");
            }
        } else {
            System.out.println("Error: Invalid index or student not found.");
        }
    } else {
        System.out.println("Error: Invalid index.");
    }

    return;
}

private void addMultipleStudents(Programme selectedProgramme, TutorialGroup selectedTutorialGroup, SortedLinkedListInterface<Student> filteredStudents) {
    System.out.print("\nEnter how many students you want to add: ");
    int numStudents = scanner.nextInt();
    scanner.nextLine();

    if (numStudents < 1) {
        System.out.println("Error: You need to add at least 1 student.");
        
       
        return;
    } else if (numStudents > selectedTutorialGroup.getMaxStudents() - selectedTutorialGroup.getCurrentNumStud()) {
        System.out.println("Error: The selected tutorial group has reached its maximum capacity.");
        return;
    } else if (numStudents > filteredStudents.getNumberOfEntries()) {
        System.out.println("Error: Not enough students to be added.");
        return;
    }

    int numStudWantAdd = 0;
 
    while (numStudWantAdd < numStudents) {
       

        System.out.print("Enter the index of student " + (numStudWantAdd + 1) + ": ");
        int studentIndex = scanner.nextInt();
        scanner.nextLine();

        // Validate the selected student index
        if (studentIndex < 1 || studentIndex > filteredStudents.getNumberOfEntries()) {
            System.out.println("Invalid student index. Please try again.");
            continue;
        }

        // get the selected student from the filtered list
        Student student = filteredStudents.getEntry(studentIndex);

        // Check if the selected student is already assigned to a tutorial group
        if (tutorialGroupController.isStudHaveGroup(student, selectedProgramme, programmeList)) {
            System.out.println("Error: The selected student is already assigned to a tutorial group. Please try again.");
            continue;
        }

        selectedTutorialGroup.addStudents(student);
        selectedTutorialGroup.setCurrentNumStud(selectedTutorialGroup.getCurrentNumStud() + 1);
        tutorialGroupController.updateStudentTg(student,selectedTutorialGroup);
        numStudWantAdd++;
    }

    System.out.println("Students added to the tutorial group.");
    return;
}




 private void addAllStudents(Programme selectedProgramme, TutorialGroup selectedTutorialGroup, SortedLinkedListInterface<Student> filteredStudents) {
    int studWithoutTutorialGroup = 0;

    // Count the number of students without a tutorial group
    for (int i = 1; i <= filteredStudents.getNumberOfEntries(); i++) {
        Student student = filteredStudents.getEntry(i);
        if (!tutorialGroupController.isStudHaveGroup(student, selectedProgramme, programmeList)) {
            studWithoutTutorialGroup++;
        }
    }

    if (selectedTutorialGroup.getMaxStudents() - selectedTutorialGroup.getCurrentNumStud() < studWithoutTutorialGroup) {
        System.out.println("Error: Not enough capacity in the tutorial group to add all students.");
        return;
    }

    // Add all students without a tutorial group to the selected tutorial group
    for (int i = 1; i <= filteredStudents.getNumberOfEntries(); i++) {
        Student student = filteredStudents.getEntry(i);
        if (!tutorialGroupController.isStudHaveGroup(student, selectedProgramme, programmeList)) {
            selectedTutorialGroup.addStudents(student);
            selectedTutorialGroup.setCurrentNumStud(selectedTutorialGroup.getCurrentNumStud() + 1);
                    tutorialGroupController.updateStudentTg(student,selectedTutorialGroup);

        }
    }

    System.out.println("\nAll students added to the tutorial group.");
    return;
}

 
    public void removeStudentFromTutorialGroup() {
        Programme selectedProgramme = selectProgramme();
        TutorialGroup selectedTutorialGroup = selectTutorialGroup(selectedProgramme);
        if (selectedTutorialGroup != null) {
            if (selectedTutorialGroup.getStudentList().isEmpty()) {
                System.out.println("Error: There are no students assigned to this tutorial group.");
                return;
            }
            System.out.println("\nStudents in " + selectedTutorialGroup.getId() + ":");
            System.out.println("Index \t Student ID \t Name");
            System.out.println("----------------------------------------");
            for (int i = 1; i <= selectedTutorialGroup.getStudentList().getNumberOfEntries(); i++) {
                System.out.println(i + ".\t    " + selectedTutorialGroup.getStudentList().getEntry(i).getStudentIDLevel()
                        + "\t   " + selectedTutorialGroup.getStudentList().getEntry(i).getName());
            }
            System.out.println("\nSelect an option:");
            System.out.println("1. Remove ONE student");
            System.out.println("2. Remove MULTIPLE students");
            System.out.println("3. Remove ALL students");
            System.out.print("\nEnter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    removeOneStudent(selectedTutorialGroup);
                    break;
                case 2:
                    removeMultipleStudents(selectedTutorialGroup);
                    break;
                case 3:
                    removeAllStudents(selectedTutorialGroup);
                    break;
                default:
                    System.out.println("Invalid choice.");
                    break;
            }
        } else {
            System.out.println("Tutorial group not found.");
        }

        return;
    }

    private void removeOneStudent(TutorialGroup selectedTutorialGroup) {
        int index = -1;
        while (index < 1 || index > selectedTutorialGroup.getStudentList().getNumberOfEntries()) {
            System.out.print("\nEnter the index of the student to remove: ");
            try {
                index = Integer.parseInt(scanner.nextLine());
                if (index < 1 || index > selectedTutorialGroup.getStudentList().getNumberOfEntries()) {
                    System.out.println("Invalid index. Please enter a valid index.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid integer.");
            }
        }

        boolean removed = tutorialGroupController.removeStudentFromTG(selectedTutorialGroup, index);

        if (removed) {
            System.out.println("Student removed from the tutorial group.");
            System.out.println("Current Total Students in " + selectedTutorialGroup.getId() + ": " + selectedTutorialGroup.getCurrentNumStud());
        } else {
            System.out.println("Error: Failed to remove student from the tutorial group.");
        }
    }

    private void removeMultipleStudents(TutorialGroup selectedTutorialGroup) {
        System.out.print("Enter how many students you want to remove: ");
        int numStudents = scanner.nextInt();
        scanner.nextLine();

        if (numStudents < 1) {
            System.out.println("Error: You need to remove at least 1 student.");
            
            return;
        }

        //先store着那个要remove的student先
        int[] studsWantRemove = new int[numStudents];

        for (int i = 0; i < numStudents; i++) {
            System.out.print("\nEnter the index of student " + (i + 1) + " to remove: ");
            int index = scanner.nextInt();
            scanner.nextLine();

            if (index < 1 || index > selectedTutorialGroup.getStudentList().getNumberOfEntries()) {
                System.out.println("Invalid index. Please enter a valid index.");
                i--;  //回去零 重新
            } else {
                studsWantRemove[i] = index;  //把student store进去
            }
        }

        int removedCount = 0;
        for (int j = 0; j < numStudents; j++) {
            int updateIndex = studsWantRemove[j] - removedCount;   //一个一个减然后update index
            boolean removed = tutorialGroupController.removeStudentFromTG(selectedTutorialGroup, updateIndex);
            if (removed) {
                System.out.println("Student at index " + studsWantRemove[j] + " removed from the tutorial group.");
                removedCount++;
            } else {
                System.out.println("Error: Failed to remove student at index " + studsWantRemove[j] + " from the tutorial group.");
            }
        }

        System.out.println("Total students removed: " + removedCount);
        System.out.println("Current Total Students in " + selectedTutorialGroup.getId() + ": " + selectedTutorialGroup.getCurrentNumStud());
        return;
    }

    private void removeAllStudents(TutorialGroup selectedTutorialGroup) {
        int totalStudents = selectedTutorialGroup.getStudentList().getNumberOfEntries();
        for (int i = 0; i < totalStudents; i++) {
            boolean removed = tutorialGroupController.removeStudentFromTG(selectedTutorialGroup, 1); //每次都remove掉第一个
            if (removed) {
                System.out.println("All Student removed from the tutorial group.");
                System.out.println("Current Total Students in " + selectedTutorialGroup.getId() + ": " + selectedTutorialGroup.getCurrentNumStud());
            } else {
                System.out.println("Error: Failed to remove student from the tutorial group.");
            }
        }
    }

    public void changeTutorialGroupForStudent() {
        Programme selectedProgramme = selectProgramme();

        if (selectedProgramme != null) {
            TutorialGroup selectedTutorialGroup = selectTutorialGroup(selectedProgramme);

            if (selectedTutorialGroup != null) {
                System.out.println("Select a student from the following list:");
                System.out.println("Index\tStudent ID\tName");
                System.out.println("---------------------------------");
                SortedLinkedListInterface<Student> studentList = selectedTutorialGroup.getStudentList();

               for (int i = 1; i <= selectedTutorialGroup.getStudentList().getNumberOfEntries(); i++) {
                System.out.println(i + ".\t" + selectedTutorialGroup.getStudentList().getEntry(i).getStudentIDLevel()
                        + "\t" + selectedTutorialGroup.getStudentList().getEntry(i).getName());
            }
                System.out.print("Enter the index of the student you want to select: ");
                int studentIndex = scanner.nextInt();
                scanner.nextLine();

                if (studentIndex < 1 || studentIndex > selectedTutorialGroup.getStudentList().getNumberOfEntries()) {
                    System.out.println("Invalid student index.");
                   return;
                }

                Student selectedStudent = selectedTutorialGroup.getStudentList().getEntry(studentIndex);

                System.out.print("Confirm to change the tutorial group of this student? (Y/N): ");
                String confirmChange = scanner.nextLine().trim().toUpperCase();
                if (confirmChange.equals("Y")) {

                    System.out.println("\nTutorial Groups in " + selectedProgramme.getName() + ": ");
                    TutorialGroup newTutorialGroup = selectTutorialGroup(selectedProgramme);
                    if (newTutorialGroup != null) {
                        if (newTutorialGroup.getCurrentNumStud() < newTutorialGroup.getMaxStudents()) {
                            boolean success = tutorialGroupController.changeTG(selectedStudent, selectedTutorialGroup, selectedProgramme, newTutorialGroup);
                            if (success) {
                                System.out.println("Tutorial group changed successfully.");
                                System.out.println("Student moved from " + selectedTutorialGroup.getId() + " to " + newTutorialGroup.getId());
                            } else {
                                System.out.println("Error: Failed to change tutorial group for the student.");
                            }
                        } else {
                            System.out.println("Error: The selected tutorial group has reached its maximum capacity. Cannot change.");
                        }
                    } else {
                        System.out.println("Invalid tutorial group selection.");
                    }
                } else {
                    System.out.println("Cancel change tutorial group.");
                }
            } else {
                System.out.println("Invalid tutorial group selection.");
            }

        } else {
            System.out.println("Programme not found.");

        }
        return;
    }

    public void listStudentsInTutorialGroupAndProgramme() {
        Programme selectedProgramme = selectProgramme();

        if (selectedProgramme != null) {

            TutorialGroup selectedTutorialGroup = selectTutorialGroup(selectedProgramme);

            if (selectedTutorialGroup != null) {

                System.out.println("\nStudents in " + selectedTutorialGroup.getId() + " (Programme: " + selectedProgramme.getName() + "):");
                SortedLinkedListInterface<Student> studentList = selectedTutorialGroup.getStudentList();
                System.out.println("Student ID \tName");
                System.out.println("----------------------------------------");
                for (int i = 1; i <= studentList.getNumberOfEntries(); i++) {
                    Student student = studentList.getEntry(i);
                    System.out.println(student.getStudentIDLevel()+ "\t" + student.getName());
                }
            } else {
                System.out.println("Tutorial group not found.");
            }
        } else {
            System.out.println("Programme not found.");
        }
        System.out.println("\nPress <ENTER> to continue...");
        scanner.nextLine();
        return;
    }

    public void mergeTutorialGroups() {
        System.out.println("Merge Tutorial Groups based on Number Of Students & CGPA(for degree student only)");
        System.out.println("-----------------------------------------------------------------------------------------------------");
        Programme selectedProgramme = selectProgramme();
        if (selectedProgramme != null) {
            System.out.println("\nTutorial Groups in " + selectedProgramme.getName() + ": ");
            System.out.printf("Tutorial Group ID \tMaximum Students    Current Total Students");
            if (selectedProgramme.getProgrammeCode().charAt(0) == 'R') {
        System.out.printf("\tAverage CGPA");
    }
            System.out.println("\n-----------------------------------------------------------------------------------");
            SortedLinkedListInterface<TutorialGroup> tutorialGroups = selectedProgramme.getTutorialGroupList();

           for (int i = 1; i <= tutorialGroups.getNumberOfEntries(); i++) {
        TutorialGroup tutorialGroup = tutorialGroups.getEntry(i);
        System.out.print(tutorialGroup.getId() + "\t\t\t\t" + tutorialGroup.getMaxStudents() + "\t\t\t" + tutorialGroup.getCurrentNumStud());
        if (selectedProgramme.getProgrammeCode().charAt(0) == 'R') {
            System.out.println("\t\t  " + tutorialGroupController.calculateAverageCGPA(tutorialGroup));
        } else {
            System.out.println();
        }
    }

            SortedLinkedListInterface<TutorialGroup> selectedTutorialGroups = new SortedDoublyLinkedList<>();

            while (true) {
                System.out.println("Select tutorial groups to merge (enter 0 to exit selection):");
                String tutorialGroupId = scanner.nextLine().trim().toUpperCase();

                if (tutorialGroupId.equals("0")) {
                    System.out.println("Exit");
                    break;
                }
                  
                TutorialGroup selectedTutorialGroup = tutorialGroupController.findTutorialGroupById(selectedProgramme, tutorialGroupId);

                if (selectedTutorialGroup != null) {
                    selectedTutorialGroups.add(selectedTutorialGroup);
                } else {
                    System.out.println("Tutorial group not found.");
                }
            }

            //merge 
            if (!selectedTutorialGroups.isEmpty()) {
            TutorialGroup newMergedTutorialGroup = tutorialGroupController.mergeTutorialGroups(selectedProgramme, selectedTutorialGroups);

            if (newMergedTutorialGroup != null) {
                selectedProgramme.addTutorialGroup(newMergedTutorialGroup);
                System.out.println("Tutorial groups merged successfully.");

            }
             } else {
            System.out.println("No tutorial groups selected for merging.");
        }
        } else {
            System.out.println("Programme not found.");
        }
        return;
    }

    public void generateSummaryReport() {
        System.out.println("\nSelect Report to Generate:");
        System.out.println("1. Programme Enrollment Trend Report");
        System.out.println("2. Tutorial Group CGPA Distribution Report");
        System.out.print("\nEnter your choice: ");

        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1:
                programmeEnrollmentReport();
                break;
            case 2:
                tutorialGroupCGPAReport();
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
                generateSummaryReport();
        }
    }
    
    public void programmeEnrollmentReport(){
    System.out.println("\n================================================================================================================================================================");
    System.out.printf("%87s","UNIVERSITY\n");
    System.out.printf("%102s","TUTORIAL GROUP MANAGEMENT SUBSYSTEM\n\n");
    System.out.printf("%100s","PROGRAMME ENROLLMENT TREND REPORT\n");
    System.out.printf("%110s","------------------------------------------------\n\n");

    LocalDateTime now = LocalDateTime.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    System.out.println("Generated on: " + now.format(formatter));
      System.out.println("\n    Programme Code \t Name \t\t\t\t\t\t\t\t\t\t    Number of Tutorial Groups \t     Total Students");
        System.out.println("   -----------------    ------------------------------------------------------------------------------     ---------------------    -----------------------");

    tutorialGroupController.generateProgrammeEnrollmentReport(programmeList);
    System.out.println("\n[NOTE: 0 STUDENTS IS NOT COUNTED]");

    System.out.printf("%n %95s","END OF PROGRAMME ENROLLMENT TREND REPORT");
    System.out.println("\n================================================================================================================================================================");
    System.out.println("Press <ENTER> to continue...");
    scanner.nextLine();
    return;
    }
    
    public void tutorialGroupCGPAReport(){
    System.out.println("\n===============================================================================================================================");
    System.out.printf("%62s","UNIVERSITY\n");
    System.out.printf("%77s","TUTORIAL GROUP MANAGEMENT SUBSYSTEM\n\n");
    System.out.printf("%78s","TUTORIAL GROUP CGPA DISTRIBUTION REPORT\n");
    System.out.printf("%85s","------------------------------------------------------\n\n");

    LocalDateTime now = LocalDateTime.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    System.out.println("Generated on: " + now.format(formatter));
    tutorialGroupController.generateTgCGPAReport(programmeList);
    System.out.println("\n[NOTE: 0 AVERAGE CGPA IS NOT COUNTED]");

    System.out.printf("%n %80s","END OF TUTORIAL GROUP CGPA DISTRIBUTION REPORT");
    System.out.println("\n===============================================================================================================================");
    System.out.println("Press <ENTER> to continue...");
    scanner.nextLine();
    return;
    }

}
