package ProgrammingLogicWithOOP.E11;
import java.util.Locale;
import java.util.Scanner;

public class Report {
    private void approvedReproved(double[] x){
        double average = 0;
        for(double n: x){
            average += n;
        }
        average /= 4;
        if(average >= 7){
            System.out.println("You were approved!\nAverage: " + average);
        } else {
            System.out.println("You failed!\nAverage: " + average);
        }
    }

    private double verifyValid(double num, Scanner input){
        while(num < 0 || num > 10){
            System.out.println("Invalid number, try insert number again.");
            num = input.nextDouble();
        }
        return num;
    }

    public void runReport(){
        Scanner input = new Scanner(System.in).useLocale(Locale.US);
        double[] notes = new double[4];
        System.out.println("Algorithm 11:");
        System.out.print("Enter the first note: ");
        notes[0] = verifyValid(input.nextDouble(), input);
        System.out.print("Enter the second note: ");
        notes[1] = verifyValid(input.nextDouble(), input);
        System.out.print("Enter the third note: ");
        notes[2] = verifyValid(input.nextDouble(), input);
        System.out.print("Enter the four note: ");
        notes[3] = verifyValid(input.nextDouble(), input);
        input.close();
        approvedReproved(notes);
    }
}