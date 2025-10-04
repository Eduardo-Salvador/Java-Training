package LogicTrainingWithOOP.E16.Controller;

import LogicTrainingWithOOP.CustomNumberDomain.CustomNumber;

import java.util.Locale;
import java.util.Scanner;

public class Report {

    private int verifyValidSide(int num, Scanner input){
        while(num < 0){
            System.out.println("Invalid value, input again");
            num = input.nextInt();
        }
        return num;
    }

    private void checkTriangle(int side1, int side2, int side3){
        if (side1 == side2 && side1 == side3){
            System.out.println("Triangle is equilateral.");
        } else if(side1 == side2 || side1 == side3 || side2 == side3){
            System.out.println("Triangle is isosceles.");
        } else {
            System.out.println("Triangle is scalene.");
        }
    }

    public void runReport(){
        Scanner input = new Scanner(System.in).useLocale(Locale.US);
        CustomNumber side1 = new CustomNumber();
        CustomNumber side2 = new CustomNumber();
        CustomNumber side3 = new CustomNumber();
        System.out.println("Algorithm 16:");
        System.out.print("What is the first side of the triangle?: ");
        side1.setValueInt(verifyValidSide(input.nextInt(), input));
        System.out.print("What is the second side of the triangle?: ");
        side2.setValueInt(verifyValidSide(input.nextInt(), input));
        System.out.print("What is the third side of the triangle?: ");
        side3.setValueInt(verifyValidSide(input.nextInt(), input));
        checkTriangle(side1.getValueInt(), side2.getValueInt(), side3.getValueInt());
        input.close();
    }
}