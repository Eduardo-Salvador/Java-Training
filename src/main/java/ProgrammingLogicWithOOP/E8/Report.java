package ProgrammingLogicWithOOP.E8;
import ProgrammingLogicWithOOP.CustomNumberDomain.CustomNumber;
import ProgrammingLogicWithOOP.CustomNumberDomain.Value;
import java.util.Locale;
import java.util.Scanner;

public class Report {
    private int[] descendingAlgorithm(int valueA, int valueB, int valueC){
        int[] arrayInt = new int[]{valueA, valueB, valueC};
        int valueMin = arrayInt[0];
        int valueMax = arrayInt[0];
        for(int i = 0; i < arrayInt.length; i++){
            if(arrayInt[i] < valueMin){
                valueMin = arrayInt[i];
                continue;
            }
            if(arrayInt[i] > valueMax){
                valueMax = arrayInt[i];
            }
        }
        int valueMiddle = valueA + valueB + valueC - valueMax - valueMin;
        int[] finalArray = new int[]{valueMax, valueMiddle, valueMin};
        return finalArray;
    }

    public void runReport(){
        Scanner input = new Scanner(System.in).useLocale(Locale.US);
        Value valueA = new CustomNumber();
        Value valueB = new CustomNumber();
        Value valueC = new CustomNumber();

        System.out.println("Algorithm 8:");
        System.out.print("Enter the (A) first number: ");
        valueA.setValueInt(input.nextInt());
        System.out.print("Enter the (B) second number: ");
        valueB.setValueInt(input.nextInt());
        System.out.print("Enter the (C) third number: ");
        valueC.setValueInt(input.nextInt());
        input.close();

        System.out.println("Descending numbers:");
        int[] numbers = descendingAlgorithm(valueA.getValueInt(), valueB.getValueInt(), valueC.getValueInt());
        for(int n: numbers){
            System.out.println(n);
        }
    }
}