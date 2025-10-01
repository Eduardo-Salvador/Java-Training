package SeminarSystem.Controller;
import SeminarSystem.Domain.Location;
import SeminarSystem.Domain.Seminar;
import SeminarSystem.Domain.Students;
import SeminarSystem.Domain.Teacher;
import java.util.Arrays;
import java.util.Scanner;

public class Menu {

    Students[] students = new Students[100];
    Seminar[] seminars = new Seminar[500];
    Teacher[] teachers = new Teacher[100];

    static void displayMenu(){
        System.out.println("\nSeminar System:");
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

    private void registerSeminar(Scanner inputDatas){
        System.out.println("Seminar Register:");

        System.out.print("Title: ");
        inputDatas.nextLine();
        String title = inputDatas.nextLine();

        System.out.print("Adress: ");
        String adress = inputDatas.nextLine();

        Seminar seminar = new Seminar(title, new Location(adress));
        System.out.println("\nSeminar successfully registered.\nTitle: " + seminar.getTitle() + "\nLocation: " + adress);
        for (int i = 0; i < students.length; i++) {
            if(seminars[i] == null){
                seminars[i] = seminar;
                break;
            }
        }
    }

    private void changeSeminar(Scanner inputDatas) {
        System.out.print("\nChoose a Seminar:");
        System.out.println();
        for (int i = 0; i < seminars.length; i++) {
            if (seminars[i] != null) {
                System.out.println("Seminar: " + (i + 1) + ": - Title: " + seminars[i].getTitle());
            }
        }
        System.out.println("\nPress 0 to Exit");
        int seminarOption = inputDatas.nextInt();
        if (seminarOption == 0) {
            displayMenu();
        }
        seminarOption--;

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
                inputDatas.nextLine();
                newTile = inputDatas.nextLine();
                seminars[seminarOption].setTitle(newTile);
                break;
            case 2:
                System.out.println("What a new Location?");
                inputDatas.nextLine();
                newLocation = inputDatas.nextLine();
                seminars[seminarOption].setLocation(new Location(newLocation));
                break;
            case 3:
                System.out.println("What a new title?");
                inputDatas.nextLine();
                newTile = inputDatas.nextLine();
                seminars[seminarOption].setTitle(newTile);
                System.out.println("What a new Location?");
                newLocation = inputDatas.nextLine();
                seminars[seminarOption].setLocation(new Location(newLocation));
                break;
            case 4:
                displayMenu();
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + changeOption);
        }
    }

    private void listSeminars(){
        System.out.println("\nSeminars: ");
        for (int i = 0; i < seminars.length; i++) {
            if (seminars[i] != null) {
                System.out.println((i+1) + ": Title: " + seminars[i].getTitle() + " - " + seminars[i].getLocation());
            }
        }
    }

    private void registerStudent(Scanner inputDatas){
        System.out.println("Student Register:");

        System.out.print("Name: ");
        inputDatas.nextLine();
        String name = inputDatas.nextLine();

        System.out.print("Age: ");
        int age = inputDatas.nextInt();

        System.out.println("Is the student in a seminar? (Y/N)");
        char option = inputDatas.next().charAt(0);

        Students student;
        if(option == 'N' || option == 'n'){
            student = new Students(name, age);
            System.out.println("Student successfully registered.\nName: " + student.getName() + "\nAge: " + student.getAge() + "\nSeminar: " + student.getSeminar());
            for (int i = 0; i < students.length; i++) {
                if(students[i] == null){
                    students[i] = student;
                    break;
                }
            }
            return;
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
                    break;
                }
            }
        }
        if(!emptySeminar){
            System.out.println("\nPress 0 to Exit");
            System.out.print("Choose a seminar: ");
            int seminarOption = inputDatas.nextInt();
            if(seminarOption == 0){
                displayMenu();
                return;
            }
            seminarOption--;
            student = new Students(name, age, seminars[seminarOption]);
            System.out.println("Student successfully registered.\nName: " + student.getName() + "\nAge: " + student.getAge() + "\nSeminar: " + student.getSeminar());
            for (int i = 0; i < students.length; i++) {
                if(students[i] == null){
                    students[i] = student;
                    break;
                }
            }
        }
    }

    private void changeStudent(Scanner inputDatas){
        System.out.println("\nChoose a Student:");

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
        studentOption--;

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
                inputDatas.nextLine();
                newName = inputDatas.nextLine();
                students[studentOption].setName(newName);
                break;
            case 2:
                System.out.println("What new age?");
                newAge = inputDatas.nextInt();
                students[studentOption].setAge(newAge);
                break;
            case 3:
                System.out.println("What new Seminar?");
                for (int i = 0; i < seminars.length; i++) {
                    if (seminars[i] != null) {
                        System.out.println("Seminar " + (i+1) + ": - " + seminars[i].getTitle());
                    }
                    if (seminars[0] == null){
                        System.out.println("No seminars registered, back to menu.");
                        displayMenu();
                        return;
                    }
                }
                newSeminar = inputDatas.nextInt();
                students[studentOption].setSeminar(seminars[newSeminar]);
                break;
            case 4:
                System.out.println("What new name?");
                inputDatas.nextLine();
                newName = inputDatas.nextLine();
                students[studentOption].setName(newName);
                System.out.println("What new age?");
                newAge = inputDatas.nextInt();
                students[studentOption].setAge(newAge);
                break;
            case 5:
                System.out.println("What new name?");
                inputDatas.nextLine();
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
                break;
            case 6:
                displayMenu();
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + changeOption);
        }
    }

    private void listStudents() {
        System.out.println("\nStudents: ");
        for (int i = 0; i < students.length; i++) {
            if (students[i] != null) {
                    System.out.println(i + ": Name: " + students[i].getName() + " - " + "Age: " + students[i].getAge() + " - " + "Seminar: " + students[i].getSeminar());
            }
        }
    }

    private void registerTeacher(Scanner inputDatas) {
        System.out.println("Teacher Register: ");

        System.out.print("Name: ");
        inputDatas.nextLine();
        String name = inputDatas.nextLine();

        System.out.print("Speciality: ");
        String specialty = inputDatas.nextLine();

        System.out.println("Is the Teacher in a seminar? (Y/N)");
        char option = inputDatas.next().charAt(0);

        Teacher teacher = null;
        if (option == 'N' || option == 'n') {
            teacher = new Teacher(name, specialty);
            System.out.println("Teacher successfully registered.\nName: " + teacher.getName() + "\nSpecialty: " + teacher.getSpecialty() + "\n" + Arrays.toString(teacher.getSeminars()));
            for (int i = 0; i < teachers.length; i++) {
                if (teachers[i] == null) {
                    teachers[i] = teacher;
                    break;
                }
            }
            return;
        }
        boolean emptySeminar = false;
        int quantitySeminars = 0;
        if (option == 'Y' || option == 'y') {
            System.out.print("How many seminars will the professor teach? ");
            System.out.println("\nPress 0 to Exit");
            quantitySeminars = inputDatas.nextInt();
            if (quantitySeminars == 0) {
                displayMenu();
                return;
            }
        }
        for (int i = 0; i < seminars.length; i++) {
            if (seminars[0] == null) {
                emptySeminar = true;
                break;
            }
        }

        if (emptySeminar) {
            teacher = new Teacher(name, specialty);
            System.out.println("There are no registered seminars, registered teacher without seminar.");
            System.out.println("Name: " + teacher.getName() + "\nAge: " + teacher.getSpecialty());
            for (int i = 0; i < teachers.length; i++) {
                if (teachers[i] == null) {
                    teachers[i] = teacher;
                    break;
                }
            }
        }

        if (!emptySeminar) {
            Seminar[] seminarsSelects = new Seminar[quantitySeminars];
            for (int i = 0; i < quantitySeminars; i++) {
                System.out.println("\nChoose the " + (i+1) + " seminar");
                for (int k = 0; k < seminars.length; k++) {
                    if (seminars[k] != null) {
                        System.out.println("Seminar " + (k + 1) + ": " + seminars[k].getTitle());
                    }
                }
                int seminarOption = inputDatas.nextInt();
                seminarOption--;
                teacher = new Teacher(name, specialty);
                seminarsSelects[i] = seminars[seminarOption];
                teacher.setSeminars(seminarsSelects);
            }
            if(teacher != null) {
                System.out.println("Teacher successfully registered.\nName: " + teacher.getName() + "\nSpecialty: " + teacher.getSpecialty() + "\n" + Arrays.toString(teacher.getSeminars()));
                for (int k = 0; k < teachers.length; k++) {
                    if (teachers[k] == null) {
                        teachers[k] = teacher;
                        break;
                    }
                }
            }
        }
    }

    private void changeTeacher(Scanner inputDatas){
        System.out.println("Choose a Teacher:");
        int optionTeacher = 0;

        if(teachers[0] != null){
            for (int i = 0; i < teachers.length; i++) {
                if(teachers[i] != null){
                    System.out.println((i+1) + " - Name: " + teachers[i].getName() + " - Specialty: " + teachers[i].getSpecialty());
                }
            }
            optionTeacher = inputDatas.nextInt();
            optionTeacher--;
        } else {
            System.out.println("Empty teacher list, back to menu!");
            displayMenu();
            return;
        }

        System.out.println("What would you like to change?");
        System.out.println("1. Name");
        System.out.println("2. Specialty");
        System.out.println("3. Seminars");
        System.out.println("4. Name and Specialty");
        System.out.println("5. All");
        System.out.println("6. Cancel and Exit to Menu");
        int optionChange = inputDatas.nextInt();

        String newName;
        String newSpecialty;
        int quantitySeminars;
        Seminar[] seminarsSelects;
        boolean emptySeminar = false;
        switch (optionChange){
            case 1:
                System.out.println("What new name?");
                inputDatas.nextLine();
                newName = inputDatas.nextLine();
                teachers[optionTeacher].setName(newName);
                break;
            case 2:
                System.out.println("What new specialty?");
                inputDatas.nextLine();
                newSpecialty = inputDatas.nextLine();
                teachers[optionTeacher].setSpecialty(newSpecialty);
                break;
            case 3:
                System.out.println("How many new seminars?");
                quantitySeminars = inputDatas.nextInt();
                seminarsSelects = new Seminar[quantitySeminars];
                System.out.println("\nPress 0 to Exit");
                if (quantitySeminars == 0) {
                    displayMenu();
                    return;
                }
                for (int i = 0; i < seminars.length; i++) {
                    if (seminars[0] == null) {
                        emptySeminar = true;
                        break;
                    }
                }

                if (emptySeminar) {
                    System.out.println("There are no registered seminars, registered teacher without seminar.");
                }

                if (!emptySeminar) {
                    for (int i = 0; i < quantitySeminars; i++) {
                        System.out.println("\nChoose the " + (i + 1) + "o seminar");
                        for (int k = 0; k < seminars.length; k++) {
                            if (seminars[k] != null) {
                                System.out.println("Seminar " + (k + 1) + ": " + seminars[k].getTitle());
                            }
                        }
                        int seminarOption = inputDatas.nextInt();
                        seminarOption--;
                        seminarsSelects[i] = seminars[seminarOption];
                        teachers[optionTeacher].setSeminars(seminarsSelects);
                    }
                }
                break;
            case 4:
                System.out.println("What new name?");
                inputDatas.nextLine();
                newName = inputDatas.nextLine();
                teachers[optionTeacher].setName(newName);
                System.out.println("What new specialty?");
                newSpecialty = inputDatas.nextLine();
                teachers[optionTeacher].setSpecialty(newSpecialty);
                break;
            case 5:
                System.out.println("What new name?");
                inputDatas.nextLine();
                newName = inputDatas.nextLine();
                teachers[optionTeacher].setName(newName);
                System.out.println("What new specialty?");
                newSpecialty = inputDatas.nextLine();
                teachers[optionTeacher].setSpecialty(newSpecialty);
                System.out.println("How many new seminars?");
                quantitySeminars = inputDatas.nextInt();
                seminarsSelects = new Seminar[quantitySeminars];
                System.out.println("\nPress 0 to Exit");
                if (quantitySeminars == 0) {
                    displayMenu();
                    return;
                }
                for (int i = 0; i < seminars.length; i++) {
                    if (seminars[0] == null) {
                        emptySeminar = true;
                        break;
                    }
                }

                if (emptySeminar) {
                    System.out.println("There are no registered seminars, registered teacher without seminar.");
                }

                if (!emptySeminar) {
                    for (int i = 0; i < quantitySeminars; i++) {
                        System.out.println("\nChoose the " + (i + 1) + "o seminar");
                        for (int k = 0; k < seminars.length; k++) {
                            if (seminars[k] != null) {
                                System.out.println("Seminar " + (k + 1) + ": " + seminars[k].getTitle());
                            }
                        }
                        int seminarOption = inputDatas.nextInt();
                        seminarOption--;
                        seminarsSelects[i] = seminars[seminarOption];
                        teachers[optionTeacher].setSeminars(seminarsSelects);
                    }
                }
                break;
            case 6:
                displayMenu();
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + optionChange);
        }
    }

    private void listTeachers(){
        System.out.println("Teachers: ");
        for (int i = 0; i < teachers.length; i++) {
            if (teachers[i] != null) {
                System.out.println((i+1) + ": Name: " + teachers[i].getName() + " - " + "Specialty: " + teachers[i].getSpecialty() + " - " + Arrays.toString(teachers[i].getSeminars()));
            }
        }
    }

    public void runApp(){
        Scanner newScanner = new Scanner(System.in);
        int option = 0;

        while (option != 10){
            if(option < 0 || option > 10){
                System.out.println("Invalid Option, try again.");
            }
            displayMenu();
            option = newScanner.nextInt();
            switch (option){
                case 1:
                    registerSeminar(newScanner);
                    break;
                case 2:
                    changeSeminar(newScanner);
                    break;
                case 3:
                    listSeminars();
                    break;
                case 4:
                    registerStudent(newScanner);
                    break;
                case 5:
                    changeStudent(newScanner);
                    break;
                case 6:
                    listStudents();
                    break;
                case 7:
                    registerTeacher(newScanner);
                    break;
                case 8:
                    changeTeacher(newScanner);
                    break;
                case 9:
                    listTeachers();
                    break;
                case 10:
                    System.out.println("Exit...");
                    newScanner.close();
                    break;
            }
        }
    }
}