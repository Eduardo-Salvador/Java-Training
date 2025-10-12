package UtilityClasses.Challenge1.E13;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    private static void menu(Scanner i){
        StringBuilder sb = new StringBuilder();
        int option;
        do {
            System.out.println("Menu: ");
            System.out.println("1. Add String");
            System.out.println("2. View String");
            System.out.println("3. Exit");
            option = i.nextInt();
            i.nextLine();
            switch (option) {
                case 1:
                    try {
                        sb = concatString(sb, i);
                    } catch (IOException e){
                        System.out.println(e.getMessage());
                        break;
                    }
                    System.out.println("Concatenation completed");
                    break;
                case 2:
                    System.out.println(sb);
                    break;
                case 3:
                    System.out.println("Exit...");
                    break;
                default:
                    System.out.println("Invalid input, try again.");
                    break;
            }
        }
            while (option != 3);
    }

    private static StringBuilder concatString(StringBuilder sb, Scanner i) throws IOException {
        System.out.print("Enter any word: ");
        String concatWord = i.nextLine();
        if (concatWord.isEmpty()){
            throw new IOException("Word is empty, please try again.");
        }
        return sb.append(concatWord);
    }

    public static void main(String[] args) {
        Scanner i = new Scanner(System.in);
        menu(i);
        i.close();
    }
}