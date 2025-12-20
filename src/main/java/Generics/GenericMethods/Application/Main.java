package Generics.GenericMethods.Application;
import Generics.GenericMethods.Controller.GenericMethods;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        GenericMethods.printArray(new Integer[]{1, 2, 3, 4, 5, 6});
        System.out.println("----------------");
        GenericMethods.printArray(new String[]{"Banana", "Apple", "Strawberry"});
        System.out.println("----------------");
        GenericMethods.printArray(new Double[]{5D, 3.2, 3.1425, 12.32, 100D});
        System.out.println("----------------");

        System.out.println("Max value is: " + GenericMethods.maxValue(10, 20, 30));
        System.out.println("Max value is: " + GenericMethods.maxValue(10D, 45.2, 33D));
        System.out.println("Max value is: " + GenericMethods.maxValue("Ana", "Carlos", "Heitor"));
        System.out.println("----------------");

        List<String> list = new ArrayList<>(List.of("Eduardo", "Joao"));
        GenericMethods.containsElements(list, "Joao");
        System.out.println("----------------");
        List<Double> listD = new ArrayList<>(List.of(2D, 5.43, 3.1415, 82D));
        GenericMethods.containsElements(listD, 5D);
        System.out.println("----------------");
        List<Integer> listI = new ArrayList<>(List.of(3,4,5,12,32,23));
        GenericMethods.containsElements(listI, 12);
        System.out.println("----------------");

        GenericMethods.swap(new Integer[]{1, 2, 3, 4, 5, 6}, 2, 5);
        System.out.println("----------------");
        GenericMethods.swap(new Double[]{5D, 3.2, 3.1425, 12.32, 100D}, 5D, 12.32);
        System.out.println("----------------");
        GenericMethods.swap(new String[]{"Banana", "Apple", "Strawberry"}, "Banana", "Strawberry");
        System.out.println("----------------");

        List<Integer> listI2 = new ArrayList<>();
        GenericMethods.copyList(listI, listI2);
        System.out.println("----------------");
        List<Double> listD2 = new ArrayList<>();
        GenericMethods.copyList(listD, listD2);
        System.out.println("----------------");
        List<String> list2 = new ArrayList<>();
        GenericMethods.copyList(list, list2);
        System.out.println("----------------");
        List<Number> listN = new ArrayList<>();
        GenericMethods.copyList(listI2, listN);
        System.out.println("----------------");
    }
}