/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package client;

import adt.SortedLinkedListInterface;
import adt.SortedDoublyLinkedList;
import entity.Course;
import entity.Programme;
import entity.Student;
import entity.TutorialGroup;

/**
 *
 * @author Lee Kar Xin
 */
public class TutorialGroupCtrl {

    public static SortedLinkedListInterface<Student> studentList = StudentCtrl.students;
    public static SortedLinkedListInterface<Course> courseList = CourseCtrl.courseList;
    public static SortedLinkedListInterface<Programme> programmeList = CourseCtrl.programmeList;
    
    public static SortedLinkedListInterface<Programme>  preAddAll() {
        programmeList.getEntry(1).getTutorialGroupList().add(new TutorialGroup("DBAG1", 25));
        programmeList.getEntry(2).getTutorialGroupList().add(new TutorialGroup("DBFG1", 25));
        programmeList.getEntry(3).getTutorialGroupList().add(new TutorialGroup("DHMG1", 25));
        programmeList.getEntry(4).getTutorialGroupList().add(new TutorialGroup("DISG1", 25));
        programmeList.getEntry(5).getTutorialGroupList().add(new TutorialGroup("RBXG1", 25));
        programmeList.getEntry(6).getTutorialGroupList().add(new TutorialGroup("RDSG1", 25));
        programmeList.getEntry(7).getTutorialGroupList().add(new TutorialGroup("REIG1", 25));
        programmeList.getEntry(8).getTutorialGroupList().add(new TutorialGroup("RFNG1", 25));
        programmeList.getEntry(9).getTutorialGroupList().add(new TutorialGroup("RISG1", 25));
        programmeList.getEntry(10).getTutorialGroupList().add(new TutorialGroup("RMEG1", 25));
        return programmeList;
    }

    public Programme findProgrammeById(String programmeId, SortedLinkedListInterface<Programme> programmeList) {
        for (int i = 1; i <= programmeList.getNumberOfEntries(); i++) {
            Programme programme = programmeList.getEntry(i);
            if (programme.getProgrammeCode().equals(programmeId)) {
                return programme;
            }
        }
        return null;
    }

    public String displayCurrentTg(Programme programme) {
        String currentTutorialGroup = "";
        SortedLinkedListInterface<TutorialGroup> tutorialGroups = programme.getTutorialGroupList();

        if (!tutorialGroups.isEmpty()) {
            for (int i = 1; i <= tutorialGroups.getNumberOfEntries(); i++) {
                TutorialGroup tutorialGroup = tutorialGroups.getEntry(i);
                currentTutorialGroup += tutorialGroup.getId();
                if (i < tutorialGroups.getNumberOfEntries()) {
                    currentTutorialGroup += ", ";
                }
            }
        } else {
            currentTutorialGroup += "None";
        }

        return currentTutorialGroup;
    }

    public String getTutorialGroupList(Programme programme) {
        String result = "";

        int index = 1;
        SortedLinkedListInterface<TutorialGroup> tutorialGroups = programme.getTutorialGroupList();

        for (int j = 1; j <= tutorialGroups.getNumberOfEntries(); j++) {
            TutorialGroup tutorialGroup = tutorialGroups.getEntry(j);

            result += index++ + "\t     ";
            result += tutorialGroup.getId() + "\t\t\t";
            result += tutorialGroup.getMaxStudents() + "\t\t\t" + tutorialGroup.getCurrentNumStud();
            result += "\n";
        }

        return result;
    }

    public boolean addTutorialGroup(Programme selectedProgramme, String tutorialGroupId, int maxStudents) {
        int maxTutorialGroups = selectedProgramme.getMaxTutorialGrp();
        int currentTutorialGroups = selectedProgramme.getTutorialGroupList().getNumberOfEntries();

        if (currentTutorialGroups >= maxTutorialGroups) {
            return false;
        }

        SortedLinkedListInterface<TutorialGroup> tutorialGroups = selectedProgramme.getTutorialGroupList();
        for (int i = 1; i <= tutorialGroups.getNumberOfEntries(); i++) {
            if (tutorialGroupId.equals(tutorialGroups.getEntry(i).getId())) {
                return false; // Tutorial group ID exists
            }
        }

        // Create a new tutorial group
        TutorialGroup newTutorialGroup = new TutorialGroup(tutorialGroupId, maxStudents);

        selectedProgramme.addTutorialGroup(newTutorialGroup);

        return true;
    }

    public boolean removeTutorialGroup(Programme selectedProgramme, String tutorialGroupId) {
        SortedLinkedListInterface<TutorialGroup> tutorialGroups = selectedProgramme.getTutorialGroupList();

        for (int i = 1; i <= tutorialGroups.getNumberOfEntries(); i++) {
            if (tutorialGroupId.equals(tutorialGroups.getEntry(i).getId())) {
                tutorialGroups.remove(tutorialGroups.getEntry(i));
                return true; // remove
            }
        }

        return false;
    }

    public boolean isStudHaveGroup(Student student, Programme p, SortedLinkedListInterface<Programme> programmeList) {
        for (int i = 1; i <= programmeList.getNumberOfEntries(); i++) {
            programmeList.getEntry(i);
            SortedLinkedListInterface<TutorialGroup> tutorialGroups = p.getTutorialGroupList();

            for (int j = 1; j <= tutorialGroups.getNumberOfEntries(); j++) {
                TutorialGroup tutorialGroup = tutorialGroups.getEntry(j);
                if (tutorialGroup.getStudentList().contains(student)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean removeStudentFromTG(TutorialGroup selectedTutorialGroup, int index) {
        SortedLinkedListInterface<Student> studentList = selectedTutorialGroup.getStudentList();

        if (index >= 1 && index <= studentList.getNumberOfEntries()) {
            Student selectedStudent = studentList.getEntry(index);
            studentList.remove(selectedStudent);
            selectedTutorialGroup.setCurrentNumStud(studentList.getNumberOfEntries());
            updateStudentTg(selectedStudent, null);
            return true;
        } else {
            return false;
        }
    }

    public boolean changeTG(Student student, TutorialGroup currentTutorialGroup, Programme newProgramme, TutorialGroup newTutorialGroup) {
        if (student != null && currentTutorialGroup != null && newProgramme != null && newTutorialGroup != null) {
            if (newTutorialGroup != currentTutorialGroup) {
                int maxNumStudents = newTutorialGroup.getMaxStudents();
                if (newTutorialGroup.getStudentList().getNumberOfEntries() == maxNumStudents) {
                    return false;
                } else {
                    currentTutorialGroup.getStudentList().remove(student);
                    currentTutorialGroup.setCurrentNumStud(currentTutorialGroup.getStudentList().getNumberOfEntries());

                    newTutorialGroup.addStudents(student);
                    newTutorialGroup.setCurrentNumStud(newTutorialGroup.getStudentList().getNumberOfEntries());
                    updateStudentTg(student, newTutorialGroup);

                    return true;
                }
            }
        }
        return false;
    }

    public TutorialGroup findTutorialGroupById(Programme selectedProgramme, String tutorialGroupId) {
        for (int i = 1; i <= selectedProgramme.getTutorialGroupList().getNumberOfEntries(); i++) {
            TutorialGroup tutorialGroup = selectedProgramme.getTutorialGroupList().getEntry(i);
            if (tutorialGroup.getId().equalsIgnoreCase(tutorialGroupId)) {
                return tutorialGroup;
            }
        }
        return null;
    }

    public SortedLinkedListInterface<Student> getFilteredStudents(SortedLinkedListInterface<Programme> programmeList, Programme selectedProgramme) {

        SortedLinkedListInterface<Student> filteredStudents = new SortedDoublyLinkedList<>();

        for (int i = 1; i <= programmeList.getNumberOfEntries(); i++) {
            Programme programme = programmeList.getEntry(i);
            SortedLinkedListInterface<Student> students = studentList;

            for (int j = 1; j <= students.getNumberOfEntries(); j++) {
                Student student = students.getEntry(j);
                if (validateStudentId(student, selectedProgramme)) {
                    // Check student have assigned to group or not
                    if (!isStudentAssigned(student, programmeList)) {

                        if (!filteredStudents.contains(student)) {
                            filteredStudents.add(student);
                        }
                    }
                }
            }
        }

        int index = 1;

        for (int i = 1; i <= filteredStudents.getNumberOfEntries(); i++) {
            Student student = filteredStudents.getEntry(i);
            System.out.println(index + "\t" + student.getStudentIDLevel() + "\t" + student.getName());
            index++;
        }

        return filteredStudents;
    }

    private boolean isStudentAssigned(Student student, SortedLinkedListInterface<Programme> programmeList) {
        for (int i = 1; i <= programmeList.getNumberOfEntries(); i++) {
            Programme programme = programmeList.getEntry(i);
            SortedLinkedListInterface<TutorialGroup> tutorialGroups = programme.getTutorialGroupList();

            for (int j = 1; j <= tutorialGroups.getNumberOfEntries(); j++) {
                TutorialGroup tutorialGroup = tutorialGroups.getEntry(j);
                SortedLinkedListInterface<Student> assignedStudents = tutorialGroup.getStudentList();

                if (assignedStudents.contains(student)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean validateStudentId(Student student, Programme selectedProgramme) {
        String studentId = student.getStudentIDLevel();
        String programmeId = selectedProgramme.getProgrammeCode();

        // Check student id 5th char & programme ID 1st char
        if (studentId.length() >= 5 && programmeId.length() >= 1) {
            char studentIdChar = studentId.charAt(4);
            char programmeIdChar = programmeId.charAt(0);

            //  match
            if ((studentIdChar == 'D' && programmeIdChar == 'D') || (studentIdChar == 'R' && programmeIdChar == 'R')) {
                return true;
            }
        }

        return false;
    }

    //=use index to check the student that no tg
    public Student getStudNoTgByIndex(SortedLinkedListInterface<Programme> programmeList, int index) {
        int currentIndex = 1;
        for (int i = 1; i <= programmeList.getNumberOfEntries(); i++) {
            Programme programme = programmeList.getEntry(i);
            SortedLinkedListInterface<Student> students = studentList;

            for (int j = 1; j <= students.getNumberOfEntries(); j++) {
                Student student = students.getEntry(j);
                if (!isStudHaveGroup(student, programme, programmeList)) {
                    if (currentIndex == index) {
                        return student;
                    }
                    currentIndex++;
                }
            }
        }
        return null;
    }

    //use to check have student can be add or not (like all student have been assign so that no need proceed to add student to tg)
    public int countStudWithTg(SortedLinkedListInterface<Programme> programmeList) {
        int count = 0;

        for (int i = 1; i <= programmeList.getNumberOfEntries(); i++) {
            Programme programme = programmeList.getEntry(i);
            SortedLinkedListInterface<Student> students = studentList;

            for (int j = 1; j <= students.getNumberOfEntries(); j++) {
                Student student = students.getEntry(j);
                if (isStudHaveGroup(student, programme, programmeList)) {
                    count++;
                }
            }
        }

        return count;
    }

    public double calculateAverageCGPA(TutorialGroup tutorialGroup) {
        double totalCGPA = 0.00;
        double averageGPA=0.00;
        SortedLinkedListInterface<Student> students = tutorialGroup.getStudentList();
       if (students.isEmpty()) {
        return 0.0; 
    }
        for (int i = 1; i <= students.getNumberOfEntries(); i++) {
            totalCGPA += students.getEntry(i).getCgpa();
        }
        averageGPA=totalCGPA / students.getNumberOfEntries();
           // Round off 
            averageGPA = Math.round(averageGPA * 100.0) / 100.0;


        return averageGPA;
    }

    public TutorialGroup mergeTutorialGroups(Programme selectedProgramme, SortedLinkedListInterface<TutorialGroup> selectedTutorialGroups) {
        int totalStudents = 0;
        double totalCGPA = 0.0;
        for (int i = 1; i <= selectedTutorialGroups.getNumberOfEntries(); i++) {
            TutorialGroup group = selectedTutorialGroups.getEntry(i);
            totalStudents += group.getCurrentNumStud();
            totalCGPA += calculateAverageCGPA(group) * group.getCurrentNumStud();
        }
        double averageCGPA = totalCGPA / totalStudents;
        char programmeIdChar = selectedProgramme.getProgrammeCode().charAt(0);

        if (totalStudents > 25) {
            System.out.println("Cannot merge tutorial groups. Total students exceed 25.");
            return null;
        }

        if (programmeIdChar == 'R' && averageCGPA < 2.5) {
            System.out.println("Cannot merge tutorial groups. Average CGPA is less than 2.5.");
            return null;
        }

        // create mergegroup id 
        String newTutorialGroupId = "";
        for (int i = 1; i <= selectedTutorialGroups.getNumberOfEntries(); i++) {
            newTutorialGroupId += selectedTutorialGroups.getEntry(i).getId();
        }
        int increment = 2;
        SortedLinkedListInterface<TutorialGroup> tutorialGroups = selectedProgramme.getTutorialGroupList();
        for (int i = 1; i <= tutorialGroups.getNumberOfEntries(); i++) {
            while (tutorialGroups.getEntry(i).getId().equals(newTutorialGroupId)) {
                newTutorialGroupId = newTutorialGroupId + increment;
                increment++;
            }
        }
        // Create merged group
        TutorialGroup newMergedTutorialGroup = new TutorialGroup(newTutorialGroupId, 25);
        for (int i = 1; i <= selectedTutorialGroups.getNumberOfEntries(); i++) {
            TutorialGroup group = selectedTutorialGroups.getEntry(i);
            // Move all students to merged group
            for (int j = 1; j <= group.getStudentList().getNumberOfEntries(); j++) {
                newMergedTutorialGroup.addStudents(group.getStudentList().getEntry(j));
                updateStudentTg(group.getStudentList().getEntry(j), newMergedTutorialGroup);
            }
            // Remove the selected groups
            selectedProgramme.getTutorialGroupList().remove(group);
        }

        // Update 
        newMergedTutorialGroup.setCurrentNumStud(totalStudents);

        return newMergedTutorialGroup;
    }

    public boolean updateStudentTg(Student student, TutorialGroup tutorialGroup) {
        if (student != null) {
            student.setTutorialGroupList(tutorialGroup);
            return true;
        }
        return false;
    }

    public void generateProgrammeEnrollmentReport(SortedLinkedListInterface<Programme> programmeList) {
        //use this all list to compare and store the most and least
        SortedLinkedListInterface<Programme> mostDegreeStudents = new SortedDoublyLinkedList<>();
        SortedLinkedListInterface<Programme> leastDegreeStudents = new SortedDoublyLinkedList<>();
        SortedLinkedListInterface<Programme> mostDiplomaStudents = new SortedDoublyLinkedList<>();
        SortedLinkedListInterface<Programme> leastDiplomaStudents = new SortedDoublyLinkedList<>();

        int maxDegreeStud = 1;
        int minDegreeStud = Integer.MAX_VALUE;
        int maxDiplomaStud = 1;
        int minDiplomaStud = Integer.MAX_VALUE;

        int totalRecords = 0;
        int index = 1;

        for (int i = 1; i <= programmeList.getNumberOfEntries(); i++) {
            Programme programme = programmeList.getEntry(i);

            SortedLinkedListInterface<TutorialGroup> tutorialGroups = programme.getTutorialGroupList();
            int totalStudents = 0;

            for (int j = 1; j <= tutorialGroups.getNumberOfEntries(); j++) {
                TutorialGroup tutorialGroup = tutorialGroups.getEntry(j);
                totalStudents += tutorialGroup.getCurrentNumStud();
            }

            System.out.printf("%-2s %-1s %-18s %-90s %-28d %-40d\n", index, ".", programme.getProgrammeCode(), programme.getName(), tutorialGroups.getNumberOfEntries(), totalStudents);

            //compare degree and diploma de students
            char programmeIdChar = programme.getProgrammeCode().charAt(0);

            //Degree
            if (programmeIdChar == 'R') {
                if (totalStudents > maxDegreeStud) {  //if tutorial group's student > 0
                    maxDegreeStud = totalStudents;  //pass inside to replace so every time if the entry larger than then the entry will replace last tiem added
                    mostDegreeStudents.clear();         // Clear previous entries
                    mostDegreeStudents.add(programme); // Add the new one
                } else if (totalStudents == maxDegreeStud) {
                    mostDegreeStudents.add(programme); // Add if equal
                }

                if (totalStudents > 0 && (totalStudents < minDegreeStud)) {
                    minDegreeStud = totalStudents;
                    leastDegreeStudents.clear();
                    leastDegreeStudents.add(programme);
                } else if (totalStudents == minDegreeStud) {
                    leastDegreeStudents.add(programme);
                }
            } else if (programmeIdChar == 'D') {
                if (totalStudents > maxDiplomaStud) {
                    maxDiplomaStud = totalStudents;
                    mostDiplomaStudents.clear();
                    mostDiplomaStudents.add(programme);
                } else if (totalStudents == maxDiplomaStud) {
                    mostDiplomaStudents.add(programme);
                }

                if (totalStudents > 0 && (totalStudents < minDiplomaStud)) {
                    minDiplomaStud = totalStudents;
                    leastDiplomaStudents.clear();
                    leastDiplomaStudents.add(programme);
                } else if (totalStudents == minDiplomaStud) {
                    leastDiplomaStudents.add(programme);
                }
            }

            totalRecords += totalStudents;
            index++;
        }

        System.out.println("\nTotal " + programmeList.getNumberOfEntries() + " Programme: " + totalRecords + " Students");
        System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------");

        // Summary 
        System.out.println("\nMOST STUDENTS IN DIPLOMA PROGRAMME:");
        for (int i = 1; i <= mostDiplomaStudents.getNumberOfEntries(); i++) {
            Programme programme = mostDiplomaStudents.getEntry(i);
            System.out.printf("   -> <%s> %-10s [%d Students]\n", programme.getProgrammeCode(), programme.getName(), maxDiplomaStud);
        }

        System.out.println("\nLEAST STUDENTS IN DIPLOMA PROGRAMME:");
        for (int i = 1; i <= leastDiplomaStudents.getNumberOfEntries(); i++) {
            Programme programme = leastDiplomaStudents.getEntry(i);
            System.out.printf("   -> <%s> %-10s [%d Students]\n", programme.getProgrammeCode(), programme.getName(), minDiplomaStud);
        }
        System.out.println("\n******************************************************************************************");

        System.out.println("\nMOST STUDENTS IN DEGREE PROGRAMME:");
        for (int i = 1; i <= mostDegreeStudents.getNumberOfEntries(); i++) {
            Programme programme = mostDegreeStudents.getEntry(i);
            System.out.printf("   -> <%s> %-10s [%d Students]\n", programme.getProgrammeCode(), programme.getName(), maxDegreeStud);
        }

        System.out.println("\nLEAST STUDENTS IN DEGREE PROGRAMME:");
        for (int i = 1; i <= leastDegreeStudents.getNumberOfEntries(); i++) {
            Programme programme = leastDegreeStudents.getEntry(i);
            System.out.printf("   -> <%s> %-10s [%d Students]\n", programme.getProgrammeCode(), programme.getName(), minDegreeStud);
        }

    }

    public void generateTgCGPAReport(SortedLinkedListInterface<Programme> programmeList) {

        double maxCGPA = 1;
        double minCGPA = Double.MAX_VALUE;

        // create list to store the highest and lowest cgpa
        SortedLinkedListInterface<TutorialGroup> highestCGPAGroups = new SortedDoublyLinkedList<>();
        SortedLinkedListInterface<TutorialGroup> lowestCGPAGroups = new SortedDoublyLinkedList<>();

        int totalRecords = 0;
        int TGcount = 0;
        int index = 1;
        System.out.println("\n    Tutorial Group ID \t    Number of Students \t\t Minimum CGPA       Maximum CGPA \t   Average CGPA");
        System.out.println("   --------------------    ---------------------    -----------------    -----------------    -----------------");

        for (int i = 1; i <= programmeList.getNumberOfEntries(); i++) {
            Programme programme = programmeList.getEntry(i);

            if (programme.getProgrammeCode().startsWith("R")) {
                SortedLinkedListInterface<TutorialGroup> tutorialGroups = programme.getTutorialGroupList();
                TGcount += tutorialGroups.getNumberOfEntries();

                for (int j = 1; j <= tutorialGroups.getNumberOfEntries(); j++) {
                    TutorialGroup tutorialGroup = tutorialGroups.getEntry(j);

                    int numStudents = tutorialGroup.getCurrentNumStud();
                    double averageCGPA = calculateAverageCGPA(tutorialGroup);
                    double minCGPAGroup = getMinCGPA(tutorialGroup);
                    double maxCGPAGroup = getMaxCGPA(tutorialGroup);

                    totalRecords += numStudents;

                    System.out.printf("%-2s %-1s %-28s %-25d %-20.2f %-20.2f %-15.2f\n", index, ".", tutorialGroup.getId(), numStudents, minCGPAGroup, maxCGPAGroup, averageCGPA);

                    // compare groups with the highest average CGPA
                    if (averageCGPA >= maxCGPA) {    //highest replace and compare one by one
                        maxCGPA = averageCGPA;
                        highestCGPAGroups.clear();
                        highestCGPAGroups.add(tutorialGroup);
                    } else if (averageCGPA == maxCGPA) {
                        highestCGPAGroups.add(tutorialGroup);
                    }

                    // compare groups with the lowest average CGPA
                    if (averageCGPA > 0 && averageCGPA < minCGPA) {  //lowest replace and compare one by one
                        minCGPA = averageCGPA;
                        lowestCGPAGroups.clear();
                        lowestCGPAGroups.add(tutorialGroup);
                    } else if (averageCGPA > 0 && averageCGPA == minCGPA) {
                        lowestCGPAGroups.add(tutorialGroup);
                    }
                    index++;
                }
            }
        }

        System.out.println("\nTotal " + TGcount + " Tutorial Groups: " + totalRecords + " Students");
        System.out.println("------------------------------------------------------------------------------------------------------------------");

        //  summary
        System.out.println("\nTUTORIAL GROUPS WITH THE HIGHEST AVERAGE CGPA:");
        if (highestCGPAGroups.getNumberOfEntries() == 0) {
            System.out.println("N/A");
        } else {
            for (int i = 1; i <= highestCGPAGroups.getNumberOfEntries(); i++) {
                TutorialGroup group = highestCGPAGroups.getEntry(i);
                System.out.printf("  -> %s (Average CGPA: %.2f)\n", group.getId(), maxCGPA);
            }
        }

        System.out.println("\nTUTORIAL GROUPS WITH THE LOWEST AVERAGE CGPA:");
        if (lowestCGPAGroups.getNumberOfEntries() == 0) {
            System.out.println("N/A");
        } else {
            for (int i = 1; i <= lowestCGPAGroups.getNumberOfEntries(); i++) {
                TutorialGroup group = lowestCGPAGroups.getEntry(i);
                System.out.printf("  -> %s (Average CGPA: %.2f)\n", group.getId(), minCGPA);
            }
        }

    }

    public double getMinCGPA(TutorialGroup tutorialGroup) {
        if (tutorialGroup.getStudentList().getNumberOfEntries() == 0) {
            return 0.00;
        }

        double minCGPA = Double.MAX_VALUE;
        for (int i = 1; i <= tutorialGroup.getStudentList().getNumberOfEntries(); i++) {
            Student student = tutorialGroup.getStudentList().getEntry(i);
            if (student.getCgpa() < minCGPA) {
                minCGPA = student.getCgpa();
            }
        }

        return minCGPA;
    }

    public double getMaxCGPA(TutorialGroup tutorialGroup) {
        if (tutorialGroup.getStudentList().getNumberOfEntries() == 0) {
            return 0.00;
        }

        double maxCGPA = -1;
        for (int i = 1; i <= tutorialGroup.getStudentList().getNumberOfEntries(); i++) {
            Student student = tutorialGroup.getStudentList().getEntry(i);
            if (student.getCgpa() > maxCGPA) {
                maxCGPA = student.getCgpa();
            }
        }

        return maxCGPA;
    }

}
