package UtilityClasses.Challenge1.E14;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner i = new Scanner(System.in);
        System.out.print("Enter any value: ");
        int v1 = i.nextInt();
        Integer v2 = Integer.valueOf(v1);
        System.out.println("Using autoboxing: ");
        System.out.println("Value Integer: " + v2);
        System.out.print("\nEnter other value: ");
        Integer v3 = i.nextInt();
        int v4 = v3.intValue();
        System.out.println("Using unboxing: ");
        System.out.println("Value int: " + v4);
        if(v2.compareTo(v3) != 0){
            System.out.println("\nThe Integers are not equals (via method, objects)");
            System.out.println("Transforming String 123 into integer (method): " + v2.parseInt("123"));
        }
        if(v1 != v4){
            System.out.println("\nThe ints are not equal (direct comparison, primitive type)");
        }
    }
}