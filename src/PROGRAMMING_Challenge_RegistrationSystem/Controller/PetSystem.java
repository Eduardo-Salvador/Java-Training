package PROGRAMMING_Challenge_RegistrationSystem.Controller;
import PROGRAMMING_Challenge_RegistrationSystem.Domain.Address;
import PROGRAMMING_Challenge_RegistrationSystem.Domain.Pet;
import PROGRAMMING_Challenge_RegistrationSystem.Domain.PetSex;
import PROGRAMMING_Challenge_RegistrationSystem.Domain.PetType;
import java.io.*;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
                    "5. List pets by some criteria (age, name, breed)\n" +
                    "6. Exit.");
            try {
                option = input.nextInt();
                input.nextLine();
            } catch (Exception e) {
                System.out.println("Only numbers");
            }
            switch (option){
                case 1:
                    try {
                        createPet(input);
                    } catch (IOException e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 2:
                    try {
                        searchPet(input);
                    } catch (IOException e) {
                        System.out.println(e.getMessage());
                    }
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
        input.close();
    }

    public static Pet createPet(Scanner input) throws IOException {
        Pet pet = null;
        String[] answers = new String[7];
        String line;
        String aux;
        String regex;
        Pattern pattern;
        Matcher matcher;
        Address address = new Address();
        try (BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\edugo\\OneDrive\\Área de Trabalho\\Java-OOP-Training\\src\\PROGRAMMING_Challenge_RegistrationSystem\\Forms.txt"))) {
            int i = 0;
            int counter = 0;
            while ((line = br.readLine()) != null) {
                counter++;
                System.out.println(line);
                if (i < counter) {
                    if (i == 0){
                        answers[i] = input.nextLine();
                    }
                    regex = "^[A-Za-z]+ [A-Za-z]+$";
                    pattern = Pattern.compile(regex);
                    matcher = pattern.matcher(answers[0]);
                    if (!matcher.matches()){
                        throw new IOException("Invalid Name: Required name + surname without special characters");
                    }
                    if (i == 3) {
                        System.out.println("What house number?");
                        address.setHouseNumber(input.nextLine());
                        System.out.println("What a city");
                        address.setCity(input.nextLine());
                        System.out.println("Which street?");
                        address.setStreet(input.nextLine());
                        i++;
                        continue;
                    }
                    if (i == 4 || i == 5) {
                        aux = input.nextLine();
                        regex = "\\d+([,.]\\d+)?$";
                        pattern = Pattern.compile(regex);
                        matcher = pattern.matcher(aux);
                        if (matcher.matches()) {
                            double value = Double.parseDouble(aux.replace(',', '.'));
                            if (i == 4 && value > 20) {
                                throw new IOException("Invalid Age: Required < 20 years");
                            }
                            if (i == 5 && (value > 60 || value < 0.5)) {
                                throw new IOException("Invalid Weight: Required < 60Kgs and > 0.6Kgs");
                            }
                            answers[i] = String.valueOf(value);
                            i++;
                            continue;
                        } else {
                            throw new IOException("Invalid Input: Required format like 09, 09.30, 09,21, etc.");
                        }
                    }

                    if (i == 6){
                        aux = input.nextLine();
                        regex = "^[A-Za-zÀ-ÿ]+(?: [A-Za-zÀ-ÿ]+)*$";
                        pattern = Pattern.compile(regex);
                        matcher = pattern.matcher(aux);
                        if(matcher.matches()){
                            answers[i] = aux;
                            i++;
                            continue;
                        } else {
                            throw new IOException("Invalid Breed: Required text, numbers are invalid.");
                        }
                    }
                    answers[i] = input.nextLine();
                    i++;
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        try {
            pet = new Pet(answers[0]);
            for (int i = 0; i < answers.length; i++){
                fillPet(pet, i, answers[i], address);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        savePet(pet.getName(), pet, address);
        return pet;
    }

    private static void fillPet(Pet pet, int index, String value, Address address) {
        if (value == null || value.trim().isEmpty()) {
            switch (index) {
                case 1: {
                    pet.setType(PetType.NO_INFORMED);
                    break;
                }
                case 2: {
                    pet.setSex(PetSex.NO_INFORMED);
                    break;
                }
                case 3: {
                    if (address != null) pet.setAddressWasFound(address);
                    break;
                }
                case 4: {
                    pet.setAgeApproximate(null);
                    break;
                }
                case 5: {
                    pet.setWeightApproximate(null);
                    break;
                }
                case 6: {
                    pet.setRace(pet.getNO_INFORMED());
                    break;
                }
            }
            return;
        }

        switch (index) {
            case 1: {
                if (value.equalsIgnoreCase("D")) {
                    pet.setType(PetType.DOG);
                } else {
                    pet.setType(PetType.CAT);
                }
                break;
            }
            case 2: {
                if (value.equalsIgnoreCase("F")) {
                    pet.setSex(PetSex.FEMALE);
                } else {
                    pet.setSex(PetSex.MALE);
                }
                break;
            }
            case 3: {
                if (address != null) pet.setAddressWasFound(address);
                break;
            }
            case 4: {
                pet.setAgeApproximate(Double.parseDouble(value));
                break;
            }
            case 5: {
                pet.setWeightApproximate(Double.parseDouble(value));
                break;
            }
            case 6: {
                pet.setRace(value);
                break;
            }
        }
    }

    private static void savePet(String animalName, Pet pet, Address address) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd'T'HHmm");
        String dateFormatted = LocalDateTime.now().format(formatter);
        Path folder = Paths.get("C:\\Users\\edugo\\OneDrive\\Área de Trabalho\\Java-OOP-Training\\src\\PROGRAMMING_Challenge_RegistrationSystem\\RegisteredPets");
        Path file = Paths.get("C:\\Users\\edugo\\OneDrive\\Área de Trabalho\\Java-OOP-Training\\src\\PROGRAMMING_Challenge_RegistrationSystem\\RegisteredPets\\" + dateFormatted + "-" + animalName.toUpperCase() + ".txt");
        try (FileWriter fw = new FileWriter(String.valueOf(file), true)) {
            Files.createDirectories(folder);
            Files.createFile(file);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write("1 - " + pet.getName());
            bw.newLine();
            String type = pet.getType() == PetType.DOG ? "Dog" : "Cat";
            bw.write("2 - " + type);
            bw.newLine();
            String sex = pet.getSex() == PetSex.FEMALE ? "Female" : "Male";
            bw.write("3 - " + sex);
            bw.newLine();
            String fullAddress = address.getStreet() + " " + address.getHouseNumber() + " " + address.getCity();
            bw.write("4 - " + fullAddress);
            bw.newLine();
            bw.write("5 - " + pet.getAgeApproximate() + " years");
            bw.newLine();
            bw.write("6 - " + pet.getWeightApproximate() + " kgs");
            bw.newLine();
            bw.write("7 - " + pet.getRace());
            bw.newLine();
            bw.flush();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void searchPet(Scanner scanner) throws IOException {
        int option;
        PetType searchType;
        String search;

        System.out.println("What type of animal?");
        System.out.println("1. Cat");
        System.out.println("2. Dog");
        try {
            option = scanner.nextInt();
            scanner.nextLine();
            if (option == 1){
                searchType = PetType.CAT;
            } else {
                searchType = PetType.DOG;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());;
        }
        do {
            System.out.println("Search Pet:");
            System.out.println("Select one option:");
            System.out.println("1. First or Last Name\n" +
                    "2. Sex\n" +
                    "3. Age\n" +
                    "4. Weight\n" +
                    "5. Race\n" +
                    "6. Address\n +" +
                    "7. Combine criteria" +
                    "8. Exit to menu");
            option = scanner.nextInt();
            scanner.nextLine();
            switch (option){
                case 1:
                    System.out.println("Enter the First or Last Name");
                    search = scanner.nextLine();
                    break;
                case 2:
                    System.out.println("Enter the sex");
                    search = scanner.nextLine();
                    break;
                case 3:
                    System.out.println("Enter the weight");
                    search = scanner.nextLine();
                    break;
                case 4:
                    System.out.println("Enter the race");
                    search = scanner.nextLine();
                    break;
                case 5:
                    System.out.println("Enter the Address:");
                    System.out.println("i. Enter the street");
                    search = scanner.nextLine();
                    System.out.println("ii. Enter the houseNumber");
                    search += ", " + scanner.nextLine();
                    System.out.println("iii. Enter the city");
                    search += ", " + scanner.nextLine();
                    break;
                case 6:
                    String[] criteria = {"Name", "Last Name", "Sex", "Age", "Weight", "Race", "Address"};
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
                        System.out.println("Enter the: " + criteria[firstChoice] + " and " + criteria[firstChoice]);
                        search = scanner.nextLine();
                    }
                    break;
                case 8:
                    menu();
                    break;
                default:
                    System.out.println("Invalid option..");
                    break;
            }
        } while (option != 8);
        /* Path dir = Paths.get("C:\\Users\\edugo\\OneDrive\\Área de Trabalho\\Java-OOP-Training\\src\\PROGRAMMING_Challenge_RegistrationSystem\\RegisteredPets");
        try(DirectoryStream<Path> stream = Files.newDirectoryStream(dir)){
            for(Path path: stream){
                System.out.println(path.getFileName());
                if ()
            }
        } catch (IOException e){
            System.out.println(e.getMessage());
        } */
    }
}