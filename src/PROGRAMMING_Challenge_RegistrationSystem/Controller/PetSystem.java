package PROGRAMMING_Challenge_RegistrationSystem.Controller;
import PROGRAMMING_Challenge_RegistrationSystem.Domain.Pet;
import PROGRAMMING_Challenge_RegistrationSystem.Domain.PetType;
import java.io.*;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PetSystem {
    public static void menu() {
        Scanner input = new Scanner(System.in);
        int option = 0;
        do {
            System.out.println("Pet Adoption System");
            System.out.println("Choose a option:");
            System.out.println("""
                    1. Register a new pet
                    2. Search a pets
                    3. Change the registered pet's data
                    4. Delete a registered pet
                    5. List all registered pets
                    6. List pets by some criteria (age, name, breed)
                    7. Exit.""");
            try {
                option = input.nextInt();
                input.nextLine();
                switch (option){
                    case 1:
                        try {
                            Pet.createPet(input);
                        } catch (IOException e){
                            System.out.println(e.getMessage());
                        }
                        break;
                    case 2:
                        try {
                            searchPet(input, 1);
                        } catch (IOException e) {
                            System.out.println(e.getMessage());
                        }
                        break;
                    case 3:
                        try {
                            int optionChoseChange = 0;
                            String[] optionsChange = searchPet(input, 2);
                            System.out.println("Select one option to change:");
                            do {
                                if (optionsChange != null) {
                                    for (int i = 0; i < optionsChange.length; i++) {
                                        System.out.println((i + 1) + ". " + optionsChange[i]);
                                    }
                                    optionChoseChange = input.nextInt() - 1;
                                } else {
                                    throw new IOException("Option Null");
                                }
                            } while (optionChoseChange > optionsChange.length  || optionChoseChange < 0);
                            changePet(optionsChange[optionChoseChange], input);
                            break;
                        } catch (IOException e) {
                            System.out.println(e.getMessage());
                        }
                        break;
                    case 4:
                        break;
                    case 5:
                        break;
                    case 6:
                        break;
                    case 7:
                        System.out.println("Exit...");
                        break;
                    default:
                        System.out.println("Invalid option. Scope: 1-7");
                        break;
                }
            } catch (Exception e) {
                System.out.println("Only numbers");
            }

        } while (option != 7);
        input.close();
    }

    private static String[] searchPet(Scanner scanner, int typeSearch) throws IOException {
        int optionSearch;
        PetType searchType = null;
        String search;
        System.out.println("What type of animal?");
        System.out.println("1. Cat");
        System.out.println("2. Dog");
        try {
            optionSearch = scanner.nextInt();
            scanner.nextLine();
            if (optionSearch == 1){
                searchType = PetType.CAT;
            } else {
                searchType = PetType.DOG;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());;
        }
        System.out.println("Search Pet:");
        System.out.println("Select one option:");
        System.out.println("""
                    1. First or Last Name
                    2. Sex
                    3. Age
                    4. Weight
                    5. Race
                    6. Address
                    7. Registration Date
                    8. Combine criteria
                    9. Exit to menu""");
        optionSearch = scanner.nextInt();
        scanner.nextLine();
        switch (optionSearch) {
            case 1:
                System.out.println("Enter the First or Last Name");
                search = scanner.nextLine();
                if (typeSearch == 1) {
                    searching(search, searchType);
                    return null;
                } else {
                    return searchingArray(search, searchType);
                }
            case 2:
                System.out.println("Enter the sex");
                search = scanner.nextLine();
                if (typeSearch == 1) {
                    searching(search, searchType);
                    return null;
                } else {
                    return searchingArray(search, searchType);
                }
            case 3:
                System.out.println("Enter the age");
                search = scanner.nextLine();
                if (typeSearch == 1) {
                    searching(search, searchType);
                    return null;
                } else {
                    return searchingArray(search, searchType);
                }
            case 4:
                System.out.println("Enter the weight");
                search = scanner.nextLine();
                if (typeSearch == 1) {
                    searching(search, searchType);
                    return null;
                } else {
                    return searchingArray(search, searchType);
                }
            case 5:
                System.out.println("Enter the race");
                search = scanner.nextLine();
                if (typeSearch == 1) {
                    searching(search, searchType);
                    return null;
                } else {
                    return searchingArray(search, searchType);
                }
            case 6:
                System.out.println("Enter the Address:");
                System.out.println("i. Enter the street");
                search = scanner.nextLine();
                System.out.println("ii. Enter the houseNumber");
                search += ", " + scanner.nextLine();
                System.out.println("iii. Enter the city");
                search += ", " + scanner.nextLine();
                if (typeSearch == 1) {
                    searching(search, searchType);
                    return null;
                } else {
                    return searchingArray(search, searchType);
                }
            case 7:
                System.out.println("Enter the date: mm/dd/yyyy");
                search = scanner.nextLine();
                if (typeSearch == 1) {
                    searching(search, searchType);
                    return null;
                } else {
                    return searchingArray(search, searchType);
                }
            case 8:
                String[] criteria = {"Name", "Last Name", "Sex", "Age", "Weight", "Race", "Address", "Registration Date"};
                System.out.println("Choose the first criterion:");
                for (int i = 0; i < criteria.length; i++) {
                    System.out.println((i + 1) + ". " + criteria[i]);
                }
                int firstChoice = scanner.nextInt() - 1;
                System.out.println("You chose: " + criteria[firstChoice]);
                System.out.println("\nChoose the second criterion:");
                for (int i = 0; i < criteria.length; i++) {
                    if (i != firstChoice) {
                        System.out.println((i + 1) + ". " + criteria[i]);
                    }
                }
                int secondChoice = scanner.nextInt() - 1;
                scanner.nextLine();
                if (secondChoice == firstChoice) {
                    throw new IOException("Invalid choice, already selected!");
                } else {
                    System.out.println("You chose: " + criteria[secondChoice]);
                    System.out.println("Enter the: " + criteria[firstChoice] + " and " + criteria[secondChoice]);
                    search = scanner.nextLine();
                    if (typeSearch == 1) {
                        searching(search, searchType);
                        return null;
                    } else {
                        return searchingArray(search, searchType);
                    }
                }
            case 9:
                System.out.println("Back to menu...");
                return null;
            default:
                System.out.println("Invalid option..");
                return null;
        }
    }

    private static String[] searchingArray(String search, PetType petType){
        List<String> results = new ArrayList<>();
        StringBuilder dateFormatted = new StringBuilder();
        Path dir = Paths.get("C:\\Users\\edugo\\OneDrive\\Área de Trabalho\\Java-OOP-Training\\src\\PROGRAMMING_Challenge_RegistrationSystem\\RegisteredPets");
        try(DirectoryStream<Path> stream = Files.newDirectoryStream(dir)) {
            if (search.contains("/") || search.contains("-")){
                String dateFile = search.replaceAll("[/-]", " ");
                String[] date = dateFile.split(" ");
                dateFormatted.append(date[2]);
                dateFormatted.append(date[0]);
                dateFormatted.append(date[1]);
            }
            for (Path path : stream) {
                StringBuilder petData = new StringBuilder();
                try (BufferedReader br = new BufferedReader(new FileReader(String.valueOf(path)))) {
                    String line;

                    while ((line = br.readLine()) != null) {
                        petData.append(line.trim()).append(" - ");
                    }

                    String petText = Normalizer.normalize(petData.toString(), Normalizer.Form.NFD).replaceAll("[\\u0300-\\u036f]", "").replaceAll(" - $", "").replaceAll("\\d+\\s*-\\s*", "").toLowerCase();
                    String typeString = (petType == PetType.DOG) ? "dog" : "cat";
                    String[] wordsSearch = search.split(" ");
                    StringBuilder petData2 = new StringBuilder();
                    boolean dateMatched = !dateFormatted.isEmpty() && path.getFileName().toString().contains(dateFormatted.toString());

                    boolean anyWordFound = false;
                    for (String wSearch : wordsSearch) {
                        String cleanSearch = Normalizer.normalize(wSearch, Normalizer.Form.NFD).replaceAll("[\\u0300-\\u036f]", "").toLowerCase();
                        if (petText.contains(cleanSearch)) {
                            anyWordFound = true;
                            break;
                        }
                    }

                    if (petText.contains(typeString) && (anyWordFound || dateMatched)) {
                        String[] words = petText.split(" - ");
                        for (String word : words) {
                            boolean matched = false;
                            for (String wSearch : wordsSearch) {
                                String cleanSearch = Normalizer.normalize(wSearch, Normalizer.Form.NFD).replaceAll("[\\u0300-\\u036f]", "").toLowerCase();
                                if (word.contains(cleanSearch)) {
                                    matched = true;
                                    break;
                                }
                            }
                            if (dateMatched) {
                                matched = true;
                            }
                            word = word.toUpperCase();
                            if (matched) {
                                petData2.append("\033[1m").append(word).append("\033[0m");
                            } else {
                                petData2.append(word);
                            }
                            petData2.append(" - ");
                        }
                        String result = petData2.toString().replaceAll(" - $", "");
                        results.add(result);
                    }
                }
            }
        } catch (IOException e){
            System.out.println(e.getMessage());
        }
        String[] printResult = results.toArray(new String[0]);
        for (int i = 0; i < printResult.length; i++) {
            System.out.println((i + 1) + ". " + printResult[i]);
        }
        return results.toArray(new String[0]);
    }

    private static void searching(String search, PetType petType){
        String[] printResult = searchingArray(search, petType);
        for (int i = 0; i < printResult.length; i++) {
            System.out.println((i + 1) + ". " + printResult[i]);
        }
    }

    private static void changePet(String petValues, Scanner input){
        int option = 0;
        do {
            System.out.println("What do you want to change?");
            System.out.println("""
                    1. First and last name
                    2. Address
                    3. Age
                    4. Weight
                    5. Breed
                    6. Exit to Menu.
                    """);
            option = input.nextInt();
            input.nextLine();
            switch (option){
                case 1:
                    System.out.println("Enter the new name");
                    String newName = input.nextLine();
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
                    break;
                default:
                    System.out.println("Invalid option");
                    break;
            }
        } while (option != 6);
    }
}