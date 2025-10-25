package PROGRAMMING_Challenge_RegistrationSystem.Controller;

import PROGRAMMING_Challenge_RegistrationSystem.Domain.Pet;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;

public class PetSystem {


    public static void menu(){
        Scanner input = new Scanner(System.in);
        int option = 0;
        do {
            System.out.println("Pet Adoption System");
            System.out.println("Choose a option:");
            System.out.println("1. Register a new pet\n" +
                    "2. Change the registered pet's data\n" +
                    "3. Delete a registered pet\n" +
                    "4. List all registered pets\n" +
                    "5. List pets by some criteria (age, name, breed)\n +" +
                    "6. Exit.");
            try {
                option = input.nextInt();
            } catch (Exception e) {
                System.out.println("Only numbers");
            }
            switch (option){
                case 1:
                    readForm(input);

                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 6:
                    System.out.println("Exit...");
                    break;
                default:
                    System.out.println("Invalid option. Scope: 1-6");
                    break;
            }
        } while (option != 6);
    }

    public static Pet readForm(Scanner input){
        try(FileReader fr = new FileReader("C:\\Users\\edugo\\OneDrive\\Área de Trabalho\\Java-OOP-Training\\src\\PROGRAMMING_Challenge_RegistrationSystem\\Forms.txt")){
            BufferedReader br = new BufferedReader(fr);
            String[] questions = new String[7];
            Pet pet = new Pet();
            String line;
            int i = 0;
            while ((line = br.readLine()) != null){
                if (i != 7) {
                    questions[i] = line;
                    i++;
                }
            }
            for (String question : questions) {
                System.out.println(question);
                input.nextLine();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
