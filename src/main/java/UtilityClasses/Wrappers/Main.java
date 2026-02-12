package UtilityClasses.Wrappers;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        String comma = "10,20,30,40";
        String[] commas = comma.split(",");
        List<Integer> nums = new ArrayList<>();
        for(String s : commas) {
            int value = Integer.parseInt(s);
            nums.add(value);
        }

        int sum = 0;
        for (Integer i : nums) {
            sum += i;
        }
        System.out.println("Sum = " + sum);

        int max = nums.getFirst();
        for (Integer i : nums) {
            max = Integer.compare(max, i) > 0 ? max : i;
        }

        System.out.println("Bigger = " + max);

        //PS: It's possible to solve it with Streams.
    }
}