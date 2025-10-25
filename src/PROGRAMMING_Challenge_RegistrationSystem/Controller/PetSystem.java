package PROGRAMMING_Challenge_RegistrationSystem.Controller;

import PROGRAMMING_Challenge_RegistrationSystem.Domain.Pet;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PetSystem {


    public static void menu() {
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
                    try {
                        createPet(input);
                    } catch (Exception e){
                        System.out.println(e.getMessage());
                    }
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

    public static Pet createPet(Scanner input) throws IOException {
        Pet pet = null;
        String[] answers = new String[7];
        String line;
        //Lendo e inputando dados.
        try (BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\edugo\\OneDrive\\Área de Trabalho\\Java-OOP-Training\\src\\PROGRAMMING_Challenge_RegistrationSystem\\Forms.txt"))) {
            int i = 0;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
                if (i < 7) {
                    answers[i] = input.nextLine();
                    String regexName = "^[A-Za-z]+ [A-Za-z]+$";
                    Pattern pattern = Pattern.compile(regexName);
                    Matcher matcher = pattern.matcher(answers[0]);
                    if (!matcher.matches()){
                        throw new IOException("Invalid Name: Required name + surname without special characters");
                    }
                    if (i == 3) {
                        System.out.println("What house number?");
                        String addressComplet = input.nextLine();
                        System.out.println("What a city");
                        addressComplet += ", " + input.nextLine();
                        System.out.println("Which street?");
                        addressComplet += ", " + input.nextLine();
                        answers[i] = addressComplet;
                        i++;
                        continue;
                    }
                    answers[i] = input.nextLine();
                    i++;
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return pet;
    }
}
