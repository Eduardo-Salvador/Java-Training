package LogicTrainingWithOOP.E1.Controller;
import LogicTrainingWithOOP.CustomNumberDomain.Value;
import LogicTrainingWithOOP.CustomNumberDomain.CustomNumber;
import java.util.Scanner;

public class Report {

    private void compareValues(int A, int B, int C){
        int resultAB = A + B;
        if (resultAB > C){
            System.out.println("A + B is greater than C");
            System.out.println("A + B = " + resultAB);
            System.out.println("C = " + C);
        } else {
            System.out.println("A + B is less than C");
            System.out.println("A + B = " + resultAB);
            System.out.println("C = " + C);
        }
    }

    public void runReport(){
        Scanner input = new Scanner(System.in);
        Value valueA = new CustomNumber();
        Value valueB = new CustomNumber();
        Value valueC = new CustomNumber();

        System.out.println("Algorithm 1:");
        System.out.print("Enter the (A) first number: ");
        valueA.setValueInt(input.nextInt());
        System.out.print("Enter the (B) second number: ");
        valueB.setValueInt(input.nextInt());
        System.out.print("Enter the (C) third number: ");
        valueC.setValueInt(input.nextInt());
        input.close();

        compareValues(valueA.getValueInt(), valueB.getValueInt(), valueC.getValueInt());
    }
}