package LogicTrainingWithOOP.E3.Controller;
import LogicTrainingWithOOP.CustomNumberDomain.Value;
import LogicTrainingWithOOP.CustomNumberDomain.CustomNumber;
import java.util.Scanner;

public class Report {

    private int calculationWithCondition(int numberA, int numberB){
        if (numberA == numberB){
            return (numberA + numberB);
        }
        return (numberA * numberB);
    }

    public void runReport(){
        Scanner input = new Scanner(System.in);
        Value valueA = new CustomNumber();
        Value valueB = new CustomNumber();
        System.out.println("Algorithm 3:");
        System.out.print("Enter the (A) first number: ");
        valueA.setValue(input.nextInt());
        System.out.print("Enter the (B) second number: ");
        valueB.setValue(input.nextInt());
        input.close();
        int valueC = calculationWithCondition(valueA.getValue(), valueB.getValue());
        System.out.println("Result C = " + valueC);
    }
}
