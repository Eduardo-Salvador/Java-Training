package UtilityClasses.ScannerWithDelimiters;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String csv = "João;25;São Paulo|Maria;30;Rio de Janeiro|Pedro;28;Belo Horizonte";
        Scanner sc = new Scanner(csv);
        sc.useDelimiter("\\|");
        int count = 1;
        while (sc.hasNext()) {
            String person = sc.next();
            String[] data = person.split(";");
            System.out.println("Pessoa " + count++ + ":");
            System.out.println("  Nome: " + data[0]);
            System.out.println("  Idade: " + data[1]);
            System.out.println("  Cidade: " + data[2]);
            System.out.println();
        }
        sc.close();
    }
}