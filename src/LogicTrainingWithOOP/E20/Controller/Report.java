package LogicTrainingWithOOP.E20.Controller;
import LogicTrainingWithOOP.CustomNumberDomain.CustomNumber;
import java.util.Locale;
import java.util.Scanner;

public class Report {

    private void multiplicationTable(int num){
        System.out.println(num + " Multiplication Table:");
        for(int i = 0; i < 10; i++){
            System.out.println(num + " * " + (i+1) + " = " + (num * (i+1)));
        }
    }

    public void runReport(){
        Scanner input = new Scanner(System.in).useLocale(Locale.US);
        CustomNumber temperature = new CustomNumber();
        System.out.println("Algorithm 20:");
        System.out.print("Which multiplication table do you want to view??: ");
        temperature.setValueInt(input.nextInt());
        multiplicationTable(temperature.getValueInt());
        input.close();
    }
}