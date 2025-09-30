package Seminar.System.Controller;
import Seminar.System.Domain.Location;
import Seminar.System.Domain.Seminar;
import Seminar.System.Domain.Students;
import Seminar.System.Domain.Teacher;

import java.util.Arrays;
import java.util.Scanner;

public class Menu {

    Students[] students = new Students[100];
    Seminar[] seminars = new Seminar[500];
    Teacher[] teachers = new Teacher[100];

    public void displayMenu(){
        System.out.println("Seminar System:");
        System.out.println("1. Register Seminar");
        System.out.println("2. Change Seminar");
        System.out.println("3. List Seminars");
        System.out.println("4. Register Student");
        System.out.println("5. Change Student");
        System.out.println("6. List Students");
        System.out.println("7. Register Teacher");
        System.out.println("8. Change Teacher");
        System.out.println("9. List Teachers");
        System.out.println("10. Exit");
    }

    public void registerSeminar(Scanner inputDatas){
        System.out.println("Seminar Register: ");

        System.out.print("Title: ");
        String title = inputDatas.nextLine();

        System.out.print("\nAdress: ");
        String adress = inputDatas.nextLine();

        Seminar seminar = new Seminar(title, new Location(adress));
        System.out.println("\nSeminar successfully registered.\nTitle: " + seminar.getTitle() + "\nLocation: " + adress);
        for (int i = 0; i < students.length; i++) {
            if(seminars[i] == null){
                seminars[i] = seminar;
            }
        }
    }

    public void changeSeminar(Scanner inputDatas) {
        System.out.print("Choose a Seminar:");
        System.out.println();
        for (int i = 0; i < seminars.length; i++) {
            if (seminars[i] != null) {
                System.out.println("Seminar: " + (i + 1) + ": - Title: " + seminars[i].getTitle());
            }
        }
        System.out.println("Press 0 to Exit");
        int seminarOption = inputDatas.nextInt();
        if (seminarOption == 0) {
            displayMenu();
        }

        System.out.println("What would you like to change?");
        System.out.println("1. Title");
        System.out.println("2. Location");
        System.out.println("3. All");
        System.out.println("4. Cancel and Exit to Menu");
        int changeOption = inputDatas.nextInt();

        String newLocation;
        String newTile;
        switch (changeOption) {
            case 1:
                System.out.println("What a new title?");
                newTile = inputDatas.nextLine();
                seminars[seminarOption].setTitle(newTile);
            case 2:
                System.out.println("What a new Location?");
                newLocation = inputDatas.nextLine();
                seminars[seminarOption].setLocation(new Location(newLocation));
            case 3:
                System.out.println("What a new title?");
                newTile = inputDatas.nextLine();
                seminars[seminarOption].setTitle(newTile);
                System.out.println("What a new Location?");
                newLocation = inputDatas.nextLine();
                seminars[seminarOption].setLocation(new Location(newLocation));
            case 4:
                displayMenu();
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + changeOption);
        }
    }

    public void listSeminars(){
        System.out.println("Seminars: ");
        for (int i = 0; i < seminars.length; i++) {
            if (students[i] != null) {
                System.out.println(i + ": Title: " + seminars[i].getTitle() + " - " + "Adress: " + seminars[i].getLocation());
            }
        }
    }

    public void registerStudent(Scanner inputDatas){
        System.out.println("Student Register:");

        System.out.print("Name: ");
        String name = inputDatas.nextLine();

        System.out.print("\nAge: ");
        int age = inputDatas.nextInt();

        System.out.println("\nIs the student in a seminar? (Y/N)");
        char option = inputDatas.next().charAt(0);

        Students student;
        if(option == 'N' || option == 'n'){
            student = new Students(name, age);
            System.out.println("Student successfully registered.\nName: " + student.getName() + "\nAge: " + student.getAge() + "\nSeminar: " + student.getSeminar());
            for (int i = 0; i < students.length; i++) {
                if(students[i] == null){
                    students[i] = student;
                }
            }
        }
        boolean emptySeminar = false;
        for (int i = 0; i < seminars.length; i++) {
            if (seminars[0] == null) {
                emptySeminar = true;
            } else {
                if (seminars[i] != null) {
                    System.out.println("Seminar " + (i+1) + ": - " + seminars[i].getTitle());
                }
            }
        }
        if(emptySeminar){
            student = new Students(name, age);
            System.out.println("There are no registered seminars, registered student without seminar.");
            System.out.println("Name: " + student.getName() + "\nAge: " + student.getAge() + "\nSeminar: " + student.getSeminar());
            for (int i = 0; i < students.length; i++) {
                if(students[i] == null){
                    students[i] = student;
                }
            }
        }
        if(!emptySeminar){
            System.out.println("Press 0 to Exit");
            System.out.print("Choose a seminar: ");
            int seminarOption = inputDatas.nextInt();
            if(seminarOption == 0){
                displayMenu();
            }
            student = new Students(name, age, seminars[seminarOption]);
            System.out.println("Student successfully registered.\nName: " + student.getName() + "\nAge: " + student.getAge() + "\nSeminar: " + student.getSeminar());
            for (int i = 0; i < students.length; i++) {
                if(students[i] == null){
                    students[i] = student;
                }
            }
        }
    }

    public void changeStudent(Scanner inputDatas){
        System.out.println("Choose a Student:");

        for (int i = 0; i < students.length; i++) {
            if(students[i] != null){
                System.out.println("Student: " + (i+1) + ": - " + students[i].getName());
            }
        }
        System.out.println("Press 0 to Exit");
        int studentOption = inputDatas.nextInt();
        if(studentOption == 0){
            displayMenu();
        }

        System.out.println("What would you like to change?");
        System.out.println("1. Name");
        System.out.println("2. Age");
        System.out.println("3. Seminar");
        System.out.println("4. Name and Age");
        System.out.println("5. All");
        System.out.println("6. Cancel and Exit to Menu");
        int changeOption = inputDatas.nextInt();

        String newName;
        int newAge;
        int newSeminar;
        switch (changeOption){
            case 1:
                System.out.println("What new name?");
                newName = inputDatas.nextLine();
                students[studentOption].setName(newName);
            case 2:
                System.out.println("What new age?");
                newAge = inputDatas.nextInt();
                students[studentOption].setAge(newAge);
            case 3:
                System.out.println("What new Seminar?");
                for (int i = 0; i < seminars.length; i++) {
                    if (seminars[i] != null) {
                        System.out.println("Seminar " + (i+1) + ": - " + seminars[i].getTitle());
                    }
                    if (seminars[0] == null){
                        System.out.println("No seminars registered, back to menu.");
                        displayMenu();
                    }
                }
                newSeminar = inputDatas.nextInt();
                students[studentOption].setSeminar(seminars[newSeminar]);
            case 4:
                System.out.println("What new name?");
                newName = inputDatas.nextLine();
                students[studentOption].setName(newName);
                System.out.println("What new age?");
                newAge = inputDatas.nextInt();
                students[studentOption].setAge(newAge);
            case 5:
                System.out.println("What new name?");
                newName = inputDatas.nextLine();
                students[studentOption].setName(newName);
                System.out.println("What new age?");
                newAge = inputDatas.nextInt();
                students[studentOption].setAge(newAge);
                System.out.println("What new Seminar?");
                for (int i = 0; i < seminars.length; i++) {
                    if (seminars[i] != null) {
                        System.out.println("Seminar " + (i+1) + ": - " + seminars[i].getTitle());
                    }
                    if (seminars[0] == null){
                        System.out.println("No seminars registered, back to menu.");
                        displayMenu();
                    }
                }
                newSeminar = inputDatas.nextInt();
                students[studentOption].setSeminar(seminars[newSeminar]);
            case 6:
                displayMenu();
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + changeOption);
        }
    }

    public void listStudents() {
        System.out.println("Students: ");
        for (int i = 0; i < students.length; i++) {
            if (students[i] != null) {
                    System.out.println(i + ": Name: " + students[i].getName() + " - " + "Age: " + students[i].getAge() + " - " + "Seminar: " + students[i].getSeminar());
            }
        }
    }

    public void registerTeacher(Scanner inputDatas) {
        System.out.println("Teacher Register: ");

        System.out.print("Name: ");
        String name = inputDatas.nextLine();

        System.out.print("\nSpeciality: ");
        String specialty = inputDatas.nextLine();

        System.out.println("\nIs the Teacher in a seminar? (Y/N)");
        char option = inputDatas.next().charAt(0);

        Teacher teacher = null;
        if (option == 'N' || option == 'n') {
            teacher = new Teacher(name, specialty);
            System.out.println("Teacher successfully registered.\nName: " + teacher.getName() + "\nSpecialty: " + teacher.getSpecialty() + "\nSeminars: " + Arrays.toString(teacher.getSeminars()));
            for (int i = 0; i < teachers.length; i++) {
                if (teachers[i] == null) {
                    teachers[i] = teacher;
                }
            }
        }
        boolean emptySeminar = false;
        int quantitySeminars = 0;
        if (option == 'S' || option == 's') {
            System.out.print("How many seminars will the professor teach? ");
            System.out.println("Press 0 to Exit");
            quantitySeminars = inputDatas.nextInt();
            if (quantitySeminars == 0) {
                displayMenu();
            }
        }
        for (int i = 0; i < seminars.length; i++) {
            if (seminars[0] == null) {
                emptySeminar = true;
            }

            if (emptySeminar) {
                teacher = new Teacher(name, specialty);
                System.out.println("There are no registered seminars, registered teacher without seminar.");
                System.out.println("Name: " + teacher.getName() + "\nAge: " + teacher.getSpecialty());
                for (int j = 0; j < teachers.length; j++) {
                    if (teachers[j] == null) {
                        teachers[j] = teacher;
                    }
                }
            }

            if (!emptySeminar) {
                Seminar[] seminarsSelects = new Seminar[quantitySeminars];
                for (int j = 0; j < quantitySeminars; j++) {
                    System.out.println("\nChoose the " + i + " seminar");
                    if (seminars[j] != null) {
                        System.out.println("Seminar " + (i + 1) + ": - " + seminars[i].getTitle());
                        int seminarOption = inputDatas.nextInt();
                        teacher = new Teacher(name, specialty);
                        seminarsSelects[i] = seminars[seminarOption];
                        teacher.setSeminars(seminarsSelects);
                    }
                }
                if(teacher != null) {
                    System.out.println("Teacher successfully registered.\nName: " + teacher.getName() + "\nSpecialty: " + teacher.getSpecialty() + "\nSeminars: " + Arrays.toString(teacher.getSeminars()));
                    for (int k = 0; k < teachers.length; k++) {
                        if (teachers[k] == null) {
                            teachers[k] = teacher;
                        }
                    }
                }
            }
        }
    }

    public void ChangeTeacher(Scanner inputDatas){

    }


}