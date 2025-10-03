package LogicTrainingWithOOP.E2.Controller;
import LogicTrainingWithOOP.CustomNumberDomain.Value;
import LogicTrainingWithOOP.CustomNumberDomain.CustomNumber;
import java.util.Scanner;

public class Report {

    private String verifierPairAndOdd(int number) {
        if (number % 2 == 0) {
            return "The number is Pair";
        } else {
            return "The number is Odd";
        }
    }

    private void verifierNumber(int number){
        if(number > 0){
            System.out.println("The number is Positive and " + verifierPairAndOdd(number));
        } else {
            System.out.println("The number is Negative and " + verifierPairAndOdd(number));
        }
    }

    public void runReport(){
        Scanner input = new Scanner(System.in);
        Value value = new CustomNumber();
        System.out.println("Algorithm 2:");
        System.out.print("Enter the any number: ");
        value.setValueInt(input.nextInt());
        input.close();
        verifierNumber(value.getValueInt());
    }
}