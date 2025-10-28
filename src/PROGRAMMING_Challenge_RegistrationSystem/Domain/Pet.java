package PROGRAMMING_Challenge_RegistrationSystem.Domain;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Pet {
    private String name;
    private PetType type;
    private PetSex sex;
    private Address addressWasFound;
    private Double ageApproximate;
    private Double weightApproximate;
    private String race;
    private static final String NO_INFORMED = "No informed";

    public String getNO_INFORMED() {
        return NO_INFORMED;
    }

    public Pet(String name){
        this.name = name;
        type = PetType.NO_INFORMED;
        sex = PetSex.NO_INFORMED;
        race = NO_INFORMED;
        ageApproximate = null;
        weightApproximate = null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PetType getType() {
        return type;
    }

    public void setType(PetType type) {
        this.type = type;
    }

    public PetSex getSex() {
        return sex;
    }

    public void setSex(PetSex sex) {
        this.sex = sex;
    }

    public void setAddressWasFound(Address addressWasFound) {
        this.addressWasFound = addressWasFound;
    }

    public Double getAgeApproximate() {
        return ageApproximate;
    }

    public void setAgeApproximate(Double ageApproximate) {
        this.ageApproximate = ageApproximate;
    }

    public Double getWeightApproximate() {
        return weightApproximate;
    }

    public void setWeightApproximate(Double weightApproximate) {
        this.weightApproximate = weightApproximate;
    }

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public static void createPet(Scanner input) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader("src/PROGRAMMING_Challenge_RegistrationSystem/Forms.txt"))) {
            Pet pet;
            Address address = new Address();
            String[] answers = new String[7];
            String line;
            int i = 0;
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) {
                    continue;
                }
                System.out.println(line);
                if (i == 0) {
                    String regex = "^[A-Za-zÀ-ÿ]+(?: [A-Za-zÀ-ÿ]+)*$";
                    Pattern pattern = Pattern.compile(regex);
                    answers[i] = input.nextLine();
                    Matcher matcher = pattern.matcher(answers[0]);
                    if (!matcher.matches()) {
                        answers[i] = null;
                        throw new IOException("Invalid Name: Required name + surname without special characters");
                    }
                }
                else if (i == 1 || i == 2){
                    String regex = "^(dog|cat|male|female)$";
                    Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
                    answers[i] = input.nextLine();
                    Matcher matcher = pattern.matcher(answers[i]);
                }
                else if (i == 3) {
                    System.out.println("\nWhat house number?");
                    address.setHouseNumber(input.nextLine());
                    System.out.println("What a city");
                    address.setCity(input.nextLine());
                    System.out.println("Which street?");
                    address.setStreet(input.nextLine());
                }
                else if (i == 4 || i == 5) {
                    String aux = input.nextLine();
                    String regex = "\\d+([,.]\\d+)?$";
                    Pattern pattern = Pattern.compile(regex);
                    Matcher matcher = pattern.matcher(aux);
                    if (matcher.matches()) {
                        double value = Double.parseDouble(aux.replace(',', '.'));
                        if (i == 4 && value > 20) {
                            throw new IOException("Invalid Age: Required < 20 years");
                        }
                        if (i == 5 && (value > 60 || value < 0.5)) {
                            throw new IOException("Invalid Weight: Required < 60Kgs and > 0.6Kgs");
                        }
                        answers[i] = String.valueOf(value);
                    }
                }
                else if (i == 6) {
                    String aux = input.nextLine();
                    String regex = "^[A-Za-zÀ-ÿ]+(?: [A-Za-zÀ-ÿ]+)*$";
                    Pattern pattern = Pattern.compile(regex);
                    Matcher matcher = pattern.matcher(aux);
                    if (matcher.matches()) {
                        answers[i] = aux;
                    }
                }
                i++;
            }

        pet = new Pet(answers[0]);
        for (i = 0; i < answers.length; i++) {
            fillPet(pet, i, answers[i], address);
        }

        savePet(pet.getName(), pet, address);
        System.out.println("Pet successfully registered!");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
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
                    pet.setAddressWasFound(address);
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
                if (value.contains("Dog") || value.contains("dog") ) {
                    pet.setType(PetType.DOG);
                } else {
                    pet.setType(PetType.CAT);
                }
                break;
            }
            case 2: {
                if (value.contains("Female") || value.contains("female")) {
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
        Path folder = Paths.get("src/PROGRAMMING_Challenge_RegistrationSystem/RegisteredPets");
        Path file = Paths.get("src/PROGRAMMING_Challenge_RegistrationSystem/RegisteredPets/" + dateFormatted + "-" + animalName.toUpperCase() + ".txt");

        try {
            Files.createDirectories(folder);
            if (!Files.exists(file)) {
                Files.createFile(file);
            }
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(file.toFile(), true))) {
                bw.write("1 - " + pet.getName());
                bw.newLine();

                String type = pet.getType() == PetType.DOG ? "Dog" : pet.getType() == PetType.CAT ? "Cat" : NO_INFORMED;
                bw.write("2 - " + type);
                bw.newLine();

                String sex = pet.getSex() == PetSex.FEMALE ? "Female" : pet.getSex() == PetSex.MALE ? "Male" : NO_INFORMED;
                bw.write("3 - " + sex);
                bw.newLine();

                bw.write("4 - " + getAddressString(address));
                bw.newLine();

                if (pet.getAgeApproximate() == null){
                    bw.write("5 - " + NO_INFORMED + " years");
                } else {
                    bw.write("5 - " + pet.getAgeApproximate() + " years");
                }
                bw.newLine();

                if (pet.getAgeApproximate() == null){
                    bw.write("6 - " + NO_INFORMED + " kgs");
                } else {
                    bw.write("6 - " + pet.getWeightApproximate() + " kgs");
                }
                bw.newLine();

                bw.write("7 - " + pet.getRace());
                bw.newLine();

                bw.flush();
                Files.setAttribute(file, "dos:readonly", true);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private static String getAddressString(Address address) {
        String fullAddress;
        if (address.getStreet().isEmpty()){
            fullAddress = "Street: " + NO_INFORMED;
        } else {
            fullAddress = "Street: " + address.getStreet();
        }
        if (address.getHouseNumber().isEmpty()){
            fullAddress += " - Number: " + NO_INFORMED;
        } else {
            fullAddress += " - Number: " + address.getHouseNumber();
        }
        if (address.getCity().isEmpty()){
            fullAddress += " - City: " + NO_INFORMED;
        } else {
            fullAddress += " - City: " + address.getCity();
        }
        return fullAddress;
    }

    @Override
    public String toString() {
        return "Pet{" +
                "name='" + name + '\'' +
                ", type=" + type +
                ", sex=" + sex +
                ", addressWasFound=" + addressWasFound +
                ", ageApproximate=" + (ageApproximate == null ? NO_INFORMED : ageApproximate + " years") +
                ", weightApproximate=" + (weightApproximate == null ? NO_INFORMED : weightApproximate + " kg") +
                ", race='" + race + '\'' +
                '}';
    }
}