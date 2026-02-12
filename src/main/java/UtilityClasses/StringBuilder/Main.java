package UtilityClasses.StringBuilder;

public class Main {
    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= 1000; i++) {
            sb.append(String.format("Item %03d: Product", i));
            if (i < 1000) {
                sb.append("\n");
            }
        }
        String report = sb.toString();
        String[] lines = report.split("\n");
        for (int i = 0; i < 5; i++) {
            System.out.println(lines[i]);
        }
    }
}