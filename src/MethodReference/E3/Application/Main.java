package MethodReference.E3.Application;
import MethodReference.E3.Domain.Utility;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<String> cities = new ArrayList<>(List.of("Sao Paulo", "New York", "Florida", "Amsterdam"));
        cities.forEach(System.out::println);
        System.out.println("-------------------");
        cities.forEach(Utility::printDecorated);
    }
}