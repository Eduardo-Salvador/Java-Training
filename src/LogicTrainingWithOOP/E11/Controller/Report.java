package LogicTrainingWithOOP.E11.Controller;
import LogicTrainingWithOOP.CustomNumberDomain.CustomNumber;
import LogicTrainingWithOOP.CustomNumberDomain.Value;
import java.util.Locale;
import java.util.Scanner;

public class Report {

    private void aprovedReproved(double n1, double n2, double n3, double n4){
        double average = (n1 + n2 + n3 + n4) / 4;
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
        double numberAssist;
        Value n1 = new CustomNumber();
        Value n2 = new CustomNumber();
        Value n3 = new CustomNumber();
        Value n4 = new CustomNumber();
        System.out.println("Algorithm 11:");
        System.out.print("Enter the first note: ");
        numberAssist = input.nextDouble();
        n1.setValueDouble(verifyValid(numberAssist, input));
        System.out.print("Enter the second note: ");
        numberAssist = input.nextDouble();
        n2.setValueDouble(verifyValid(numberAssist, input));
        System.out.print("Enter the third note: ");
        numberAssist = input.nextDouble();
        n3.setValueDouble(verifyValid(numberAssist, input));
        System.out.print("Enter the four note: ");
        numberAssist = input.nextDouble();
        n4.setValueDouble(verifyValid(numberAssist, input));
        input.close();
        aprovedReproved(n1.getValueDouble(), n2.getValueDouble(), n3.getValueDouble(), n4.getValueDouble());
    }
}