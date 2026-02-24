package OOP_ProgrammingLogicWithOOP.E4;
import OOP_ProgrammingLogicWithOOP.CustomNumberDomain.CustomNumber;
import OOP_ProgrammingLogicWithOOP.CustomNumberDomain.Value;
import java.util.Scanner;

public class Report {
    private void generatePredecessorAndSuccessor(int number){
        System.out.println("Successor = " + (number+1) + "\nPredecessor = " + (number-1));
    }

    public void runReport(){
        Scanner input = new Scanner(System.in);
        Value value = new CustomNumber();
        System.out.println("Algorithm 4:");
        System.out.print("Enter the any number: ");
        value.setValueInt(input.nextInt());
        input.close();
        generatePredecessorAndSuccessor(value.getValueInt());
    }
}