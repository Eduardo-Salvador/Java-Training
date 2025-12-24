package StudyChallenges.RegistrationSystem.Controller;
import StudyChallenges.RegistrationSystem.Domain.Pet;
import StudyChallenges.RegistrationSystem.Domain.PetType;
import java.io.*;
import java.nio.file.*;
import java.text.Normalizer;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PetSystem {

    public static void menu() {
        Scanner input = new Scanner(System.in);
        int option = 0;
        do {
            System.out.println("What do you want to do?");
            System.out.println("""
                    1 - Start the system to register pets
                    2 - Start the system to modify the form
                    3 - Exit
                    """);
            try {
                option = input.nextInt();
            } catch (Exception e) {
                System.out.println("Invalid input, please try numbers");
            }
            switch (option) {
                case 1:
                    option = menuPet(input);
                    break;
                case 2:
                    option = menuForms(input);
                    break;
                case 3:
                    System.out.println("Thank you for using our services...");
                    break;
                default:
                    System.out.println("Invalid Option, try again.");
                    break;
            }
        } while (option != 3);
        input.close();
    }

    private static int menuForms(Scanner input){
        int option = 0;
        do {
            System.out.println("Forms Pet System");
            System.out.println("Choose one option:");
            System.out.print("""
                       1 - Create a new question
                       2 - Edit an existing question
                       3 - Delete an existing question
                       4 - Return to the main menu
                       5 - Exit
                       """);
            try {
                option = input.nextInt();
            } catch (Exception e) {
                System.out.println("Invalid input, please try numbers");
            }
            switch (option) {
                case 1:
                    createQuestionForms(input);
                    break;
                case 2:
                    changeQuestionForms(input);
                    break;
                case 3:
                    deleteQuestionForms(input);
                    break;
                case 4:
                    System.out.println("Exit...");
                    return 0;
                case 5:
                    System.out.println("Thank you for using our services...");
                    return 3;
            default:
                System.out.println("Invalid Option, try again.");
                break;
            }
        } while (option != 5);
        return 0;
    }

    private static void createQuestionForms(Scanner input){
        String file = "src/PROGRAMMING_Challenge_RegistrationSystem/Forms.txt";
        try(BufferedReader br = new BufferedReader(new FileReader(file));
        BufferedWriter bw = new BufferedWriter(new FileWriter(file, true)))  {
            String line;
            char number = '0';
            while((line = br.readLine()) != null){
                if(!line.isEmpty()){
                    number = line.charAt(0);
                }
            }
            int numberQuestion = Character.getNumericValue(number);
            if (numberQuestion == 19){
                System.out.println("Forms is full, delete one question for continue");
            } else {
                System.out.println("Enter the new Question");
                input.nextLine();
                String newQuestion = input.nextLine();
                bw.newLine();
                bw.write((numberQuestion + 1) + " - [EXTRA - " + newQuestion + "]");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void changeQuestionForms(Scanner input) {
        String file = "src/PROGRAMMING_Challenge_RegistrationSystem/Forms.txt";
        List<String> lines = new ArrayList<>();
        boolean found = false;
        boolean updated = false;
        int choice = 0;
        String newQuestion = "";
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (!line.isEmpty()) {
                    lines.add(line);
                }
            }
            for (String l : lines) {
                try {
                    int number = Character.getNumericValue(l.charAt(0));
                    if (number >= 8) {
                        System.out.println(l);
                        found = true;
                    }
                } catch (Exception ignored) {}
            }
            if (found) {
                System.out.print("\nChoose one question number to change: ");
                choice = input.nextInt();
                input.nextLine();
                System.out.print("Enter the new text for question " + choice + ": ");
                newQuestion = input.nextLine().trim();
            }
            if (!newQuestion.isEmpty()) {
                for (int i = 0; i < lines.size(); i++) {
                    try {
                        int number = Character.getNumericValue(lines.get(i).charAt(0));
                        if (number == choice) {
                            lines.set(i, choice + " - " + newQuestion);
                            updated = true;
                            break;
                        }
                    } catch (Exception ignored) {}
                }
            }
            if (updated) {
                try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
                    for (String l : lines) {
                        bw.write(l);
                        bw.newLine();
                    }
                }
                System.out.println("Question " + choice + " updated successfully!");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void deleteQuestionForms(Scanner input) {
        String file = "src/PROGRAMMING_Challenge_RegistrationSystem/Forms.txt";
        List<String> lines = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (!line.isEmpty()) {
                    lines.add(line);
                }
            }
            for (String l : lines) {
                try {
                    int number = Character.getNumericValue(l.charAt(0));
                    if (number >= 8) {
                        System.out.println(l);
                    }
                } catch (Exception ignored) {}
            }
            System.out.print("\nChoose one question number to delete: ");
            int choice = input.nextInt();
            input.nextLine();

            boolean confirmation = false;
            for (String l : lines) {
                int number = Character.getNumericValue(l.charAt(0));
                if (choice == number){
                    System.out.println("Are you sure you want to remove the question: " + l);
                    System.out.println("""
                            1. YES
                            2. NO
                            """);
                    if (input.nextInt() == 1) {
                        confirmation =  true;
                    }
                }
            }

            boolean removed = false;
            for (int i = 0; i < lines.size(); i++) {
                int number = Character.getNumericValue(lines.get(i).charAt(0));
                if (number == choice && number >= 8) {
                    lines.remove(i);
                    removed = true;
                    break;
                }
            }
            if (removed && confirmation) {
                try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
                    int newNumberQuestion = 8;
                    for (String l : lines) {
                        int number = Character.getNumericValue(l.charAt(0));
                        String text = l.substring(4);
                        if (number >= 8) {
                            bw.write(newNumberQuestion + " - " + text);
                            newNumberQuestion++;
                        } else {
                            bw.write(number + " - " + text);
                        }
                        bw.newLine();
                    }
                }
                System.out.println("Question deleted successfully!");
            } else {
                System.out.println("Exit...");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static int menuPet(Scanner input) {
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
                    6. Return to main menu
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
                            System.out.println("First search the pet!");
                            String[] pets = searchPet(input, 2);
                            if (pets != null && pets[0] != null) {
                                System.out.println("Which pet do you want to change?");
                                int optionChange = input.nextInt();
                                String[] petIndividual = pets[optionChange-1].split(" - ");
                                String namePet = petIndividual[0];
                                changePet(namePet, input);
                            }
                            break;
                        } catch (IOException e) {
                            System.out.println("ERROR: No pets in the search");
                        }
                        break;
                    case 4:
                        try {
                            System.out.println("First search the pet!");
                            String[] pets = searchPet(input, 2);
                            if (pets != null && pets[0] != null) {
                                System.out.println("Which pet do you want to delete?");
                                int optionChange = input.nextInt();
                                String[] petIndividual = pets[optionChange-1].split(" - ");
                                String namePet = petIndividual[0];
                                deletePet(namePet, input);
                            }
                            break;
                        } catch (IOException e) {
                            System.out.println("ERROR: No pets in the search");
                        }
                        break;
                    case 5:
                        int i = 1;
                        Path dir = Paths.get("src/PROGRAMMING_Challenge_RegistrationSystem/RegisteredPets");
                        try (DirectoryStream<Path> stream = Files.newDirectoryStream(dir)){
                            System.out.println("Registered Pets:");
                            for (Path path : stream){
                                try (FileReader fr = new FileReader(path.toFile())){
                                    BufferedReader br = new BufferedReader(fr);
                                    String namePet = br.readLine().replaceAll("1 - ", (i + ". "));
                                    System.out.println(namePet);
                                    i++;
                                }
                            }
                        }
                        break;
                    case 6:
                        System.out.println("Exit...");
                        return 0;
                    case 7:
                        System.out.println("Thank you for using our services...");
                        return 3;
                    default:
                        System.out.println("Invalid option.\nOptions: 1 to 7");
                        break;
                }
            } catch (Exception e) {
                System.out.println("Only numbers");
                break;
            }
        } while (option != 7);
        return 0;
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
                    searchingArray(search, searchType);
                    return null;
                } else {
                    return searchingArray(search, searchType);
                }
            case 2:
                System.out.println("Enter the sex");
                search = scanner.nextLine();
                if (typeSearch == 1) {
                    searchingArray(search, searchType);
                    return null;
                } else {
                    return searchingArray(search, searchType);
                }
            case 3:
                System.out.println("Enter the age");
                search = scanner.nextLine();
                if (typeSearch == 1) {
                    searchingArray(search, searchType);
                    return null;
                } else {
                    return searchingArray(search, searchType);
                }
            case 4:
                System.out.println("Enter the weight");
                search = scanner.nextLine();
                if (typeSearch == 1) {
                    searchingArray(search, searchType);
                    return null;
                } else {
                    return searchingArray(search, searchType);
                }
            case 5:
                System.out.println("Enter the race");
                search = scanner.nextLine();
                if (typeSearch == 1) {
                    searchingArray(search, searchType);
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
                    searchingArray(search, searchType);
                    return null;
                } else {
                    return searchingArray(search, searchType);
                }
            case 7:
                System.out.println("Enter the date: mm/dd/yyyy");
                search = scanner.nextLine();
                if (typeSearch == 1) {
                    searchingArray(search, searchType);
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
                        searchingArray(search, searchType);
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
        Path dir = Paths.get("src/PROGRAMMING_Challenge_RegistrationSystem/RegisteredPets");
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

    private static void changePet(String name, Scanner input){
        int option = 0;
        String newName = name;
        Path petArchive = null;
        List<String> lines = null;
        List<String> newLines = new ArrayList<>();
        Path dir = Paths.get("src", "StudyChallenges/RegistrationSystem", "RegisteredPets");
        try(DirectoryStream<Path> stream = Files.newDirectoryStream(dir)) {
            for(Path path : stream) {
                String fileName = path.getFileName().toString().toLowerCase();
                String cleanName = name.replaceAll("\u001B\\[[;\\d]*m", "").trim().toLowerCase();
                if (fileName.contains(cleanName)){
                    petArchive = path;
                }
            }
            if(petArchive != null){
                lines = Files.readAllLines(petArchive);
            }

        } catch (IOException e){
            System.out.println("Error reading file: " + e.getMessage());
            return;
        }
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
            if (lines == null) break;
            newLines.clear();
            switch (option){
                case 1:
                    System.out.println("Enter the new name:");
                    newName = input.nextLine();
                    for (String line : lines) {
                        newLines.add(line.startsWith("1 - ") ? "1 - " + newName : line);
                    }
                    break;
                case 2:
                    System.out.println("Enter the new address:");
                    String newAddress = input.nextLine();
                    for (String line : lines) {
                        newLines.add(line.startsWith("4 - ") ? "4 - " + newAddress : line);
                    }
                    break;
                case 3:
                    System.out.println("Enter the new age:");
                    String newAge = input.nextLine();
                    for (String line : lines) {
                        newLines.add(line.startsWith("5 - ") ? "5 - " + newAge : line);
                    }
                    break;
                case 4:
                    System.out.println("Enter the new weight:");
                    String newWeight = input.nextLine();
                    for (String line : lines) {
                        newLines.add(line.startsWith("6 - ") ? "6 - " + newWeight : line);
                    }
                    break;
                case 5:
                    System.out.println("Enter the new breed:");
                    String newBreed = input.nextLine();
                    for (String line : lines) {
                        newLines.add(line.startsWith("7 - ") ? "7 - " + newBreed : line);
                    }
                    break;
                case 6:
                    System.out.println("Returning to menu...");
                    break;
                default:
                    System.out.println("Invalid option");
                    break;
            }

            if (option >= 1 && option <= 5) {
                lines = new ArrayList<>(newLines);
                try {
                    String cleanName = newName.replaceAll("\u001B\\[[;\\d]*m", "").toLowerCase();
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd'T'HHmm");
                    String dateFormatted = LocalDateTime.now().format(formatter);
                    Path newFile = Paths.get("src/PROGRAMMING_Challenge_RegistrationSystem/RegisteredPets/" + dateFormatted + "-" + cleanName.toUpperCase() + ".txt");
                    Files.createFile(newFile);
                    Files.setAttribute(newFile, "dos:readonly", false);

                    try (BufferedWriter writer = new BufferedWriter(new FileWriter(newFile.toFile(), false)))    {
                        for (String line : lines) {
                            writer.write(line);
                            writer.newLine();
                        }

                        File deleteOldFile = new File(petArchive.toString());
                        if(deleteOldFile.delete()){
                            System.out.println("Pet changed successfully");
                            Files.setAttribute(newFile, "dos:readonly", true);
                        }
                        break;
                    }
                } catch (IOException e) {
                    System.out.println("Error writing file: " + e.getMessage());
                }
            }
        } while (option != 6);
    }

    private static void deletePet(String name, Scanner input){
        int option = 0;
        File petArchive = null;
        Path dir = Paths.get("src", "StudyChallenges/RegistrationSystem", "RegisteredPets");
        try(DirectoryStream<Path> stream = Files.newDirectoryStream(dir)) {
            for(Path path : stream) {
                String fileName = path.getFileName().toString().toLowerCase();
                String cleanName = name.replaceAll("\u001B\\[[;\\d]*m", "").trim().toLowerCase();
                if (fileName.contains(cleanName)){
                    petArchive = new File(path.toString());
                    System.out.println("Are you sure to delete: " + petArchive.getName());
                    while (option != 2) {
                        System.out.println("""
                            1. YES
                            2. NO
                            """);
                        option = input.nextInt();
                        if (option == 1){
                            if(petArchive.delete()) {
                                System.out.println("File deleted successfully: " + petArchive.getName());
                                break;
                            }
                        } else {
                            System.out.println("Operation canceled");
                            break;
                        }
                    }
                }
            }
        } catch (IOException e){
            System.out.println("Error reading file: " + e.getMessage());
        }
    }
}