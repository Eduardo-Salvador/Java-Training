package LogicTrainingWithOOP.E7.Controller;
import LogicTrainingWithOOP.CustomNumberDomain.CustomNumber;
import LogicTrainingWithOOP.CustomNumberDomain.Value;
import java.util.Locale;
import java.util.Scanner;

public class Report {
    private void verifyBool(char value){
        Value valueBool = new CustomNumber();
        if (Character.toLowerCase(value) == 'f'){
            valueBool.setValueBool(false);
        } else {
            valueBool.setValueBool(true);
        }
        System.out.println("Value is: " + valueBool.isValueBool());
    }

    public void runReport(){
        Scanner input = new Scanner(System.in).useLocale(Locale.US);
        Value value = new CustomNumber();
        System.out.println("Algorithm 7:");
        System.out.print("Press T for True or F for False: ");
        value.setValueChar(input.nextLine().charAt(0));
        while (Character.toLowerCase(value.getValueChar()) != 't' && Character.toLowerCase(value.getValueChar()) != 'f'){
            System.out.println("Incorrect option, try again!");
            System.out.print("Press T for True or F for False: ");
            value.setValueChar(input.nextLine().charAt(0));
        }
        verifyBool(value.getValueChar());
        input.close();
    }
}