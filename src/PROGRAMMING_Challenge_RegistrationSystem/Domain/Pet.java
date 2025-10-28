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

    public static Pet createPet(Scanner input) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\edugo\\OneDrive\\Área de Trabalho\\Java-OOP-Training\\src\\PROGRAMMING_Challenge_RegistrationSystem\\Forms.txt"))) {
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
                    } else {
                        i++;
                    }
                }
                else if (i == 1 || i == 2){
                    String regex = "^(dog|cat|male|female)$";
                    Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
                    answers[i] = input.nextLine();
                    Matcher matcher = pattern.matcher(answers[i]);
                    if (!matcher.matches()) {
                        answers[i] = null;
                        throw new IOException("Invalid Type: Required Dog or Cat, Male or Female");
                    } else {
                        i++;
                    }
                }
                else if (i == 3) {
                    System.out.println("\nWhat house number?");
                    address.setHouseNumber(input.nextLine());
                    System.out.println("What a city");
                    address.setCity(input.nextLine());
                    System.out.println("Which street?");
                    address.setStreet(input.nextLine());
                    i++;
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
                        i++;
                    } else {
                        throw new IOException("Invalid Input: Required format like 09, 09.30, 09,21, etc.");
                    }
                }
                else if (i == 6) {
                    String aux = input.nextLine();
                    String regex = "^[A-Za-zÀ-ÿ]+(?: [A-Za-zÀ-ÿ]+)*$";
                    Pattern pattern = Pattern.compile(regex);
                    Matcher matcher = pattern.matcher(aux);
                    if (matcher.matches()) {
                        answers[i] = aux;
                        i++;
                    } else {
                        throw new IOException("Invalid Breed: Required text, numbers are invalid.");
                    }
                }
            }

        pet = new Pet(answers[0]);
        for (i = 0; i < answers.length; i++) {
            fillPet(pet, i, answers[i], address);
        }

        savePet(pet.getName(), pet, address);
        System.out.println("Pet successfully registered!");
        return pet;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
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
                    if (address != null) {
                        pet.setAddressWasFound(address);
                    } else {
                        pet.setAddressWasFound(null);
                    }
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
        Path folder = Paths.get("C:\\Users\\edugo\\OneDrive\\Área de Trabalho\\Java-OOP-Training\\src\\PROGRAMMING_Challenge_RegistrationSystem\\RegisteredPets");
        Path file = Paths.get("C:\\Users\\edugo\\OneDrive\\Área de Trabalho\\Java-OOP-Training\\src\\PROGRAMMING_Challenge_RegistrationSystem\\RegisteredPets\\" + dateFormatted + "-" + animalName.toUpperCase() + ".txt");

        try {
            Files.createDirectories(folder);
            if (!Files.exists(file)) {
                Files.createFile(file);
            }
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(file.toFile(), true))) {
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
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
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