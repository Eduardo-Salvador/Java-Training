package UtilityClasses.Challenge1.E11;

public class Main {
    public static void main(String[] args) {
        String test = "eeqquuaall";
        StringBuilder sb = new StringBuilder(test.length());
        for (int i = 0; i < test.length(); i++) {
            char c = test.charAt(i);
            if (sb.indexOf(String.valueOf(c)) == -1) {
                sb.append(c);
            }
        }
        System.out.println(sb);
    }
}