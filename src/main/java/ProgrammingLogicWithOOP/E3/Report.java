package ProgrammingLogicWithOOP.E3;
import ProgrammingLogicWithOOP.CustomNumberDomain.Value;
import ProgrammingLogicWithOOP.CustomNumberDomain.CustomNumber;
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
        valueA.setValueInt(input.nextInt());
        System.out.print("Enter the (B) second number: ");
        valueB.setValueInt(input.nextInt());
        input.close();
        int valueC = calculationWithCondition(valueA.getValueInt(), valueB.getValueInt());
        System.out.println("Result C = " + valueC);
    }
}