package Generics.GenericMethods.Application;

import Generics.GenericMethods.Controller.GenericMethods;

public class Main {
    public static void main(String[] args) {
        GenericMethods.printArray(new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10});
        System.out.println("----------------");
        GenericMethods.printArray(new String[]{"Banana", "Apple", "Strawberry"});
    }
}
