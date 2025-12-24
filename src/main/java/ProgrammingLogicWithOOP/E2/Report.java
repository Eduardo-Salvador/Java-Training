package ProgrammingLogicWithOOP.E2;
import ProgrammingLogicWithOOP.CustomNumberDomain.Value;
import ProgrammingLogicWithOOP.CustomNumberDomain.CustomNumber;
import java.util.Scanner;

public class Report {
    private String verifierPairAndOdd(int number) {
        if (number % 2 == 0) {
            return "The number is Pair";
        }
        return "The number is Odd";
    }

    private void verifierNumber(int number){
        if(number > 0){
            System.out.println("The number is Positive and " + verifierPairAndOdd(number));
            return;
        }
        System.out.println("The number is Negative and " + verifierPairAndOdd(number));
    }

    public void runReport(){
        Scanner input = new Scanner(System.in);
        Value value = new CustomNumber();
        System.out.println("Algorithm 2:");
        System.out.print("Enter the any number: ");
        value.setValueInt(input.nextInt());
        verifierNumber(value.getValueInt());
        input.close();
    }
}