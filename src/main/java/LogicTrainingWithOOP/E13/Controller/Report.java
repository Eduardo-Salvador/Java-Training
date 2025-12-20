package LogicTrainingWithOOP.E13.Controller;
import java.util.Locale;
import java.util.Scanner;

public class Report {
    private int verifyAge(int age, Scanner input){
        while(age < 0 || age > 130){
            System.out.println("Invalid age, enter your age again.");
            age = input.nextInt();
        }
        return age;
    }

    private void personReport(String name, int age){
        System.out.println("Hi, " + name);
        if(age >= 18){
            System.out.println("You are of legal age");
        } else {
            System.out.println("You are a minor");
        }
        System.out.println("Age: " + age);
    }

    public void runReport(){
        Scanner input = new Scanner(System.in).useLocale(Locale.US);
        System.out.println("Algorithm 13:");
        System.out.print("What your name: ");
        String name = input.nextLine();
        System.out.print("How old are you? ");
        int age = verifyAge(input.nextInt(), input);
        personReport(name, age);
        input.close();
    }
}