package MethodReference.E2.Application;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class Main {
    public static void main(String[] args) {
        List<String> stringListNumber = new ArrayList<>(List.of("10", "25" ,"50"));

        Function<String, Integer> numStringToInteger = Integer::parseInt;
        Function<Integer, String> stringNumToString = String::valueOf;

        List<Integer> integerList = new ArrayList<>();

        for (String s : stringListNumber) {
            integerList.add(numStringToInteger.apply(s));
        }
        integerList.forEach(System.out::println);

        System.out.println("-------------------------------");

        for (Integer s : integerList) {
            stringListNumber.add(stringNumToString.apply(s));
        }
        stringListNumber.forEach(System.out::println);
    }
}