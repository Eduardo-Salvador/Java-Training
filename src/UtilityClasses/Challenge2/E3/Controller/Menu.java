package UtilityClasses.Challenge2.E3.Controller;
import UtilityClasses.Challenge2.E3.Domain.Category;
import UtilityClasses.Challenge2.E3.Domain.Client;
import UtilityClasses.Challenge2.E3.Domain.Consumption;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Menu{
    Client[] client = new Client[100];
    Client c1;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");
    private static LocalDate date;

    private static boolean isValid(String dateString) throws IOException {
        int[] dateVerification = new int[8];
        int index = 0;
        for (int i = 0; i < dateString.length(); i++) {
            char c = dateString.charAt(i);
            if (Character.isDigit(c)) {
                dateVerification[index++] = Character.getNumericValue(c);
            }
        }
        if (dateVerification[0] > 1) {
            throw new IOException("Invalid Input Date, try again");
        }
        if (dateVerification[2] > 3) {
            throw new IOException("Invalid Input Date, try again");
        }
        return true;
    }

    public void menu(){
        Scanner inp = new Scanner(System.in);
        int option;
        int optionClient;
        do {
            System.out.println("Customer Registration with Consumption");
            System.out.println("1. Register client");
            System.out.println("2. Add Consumption");
            System.out.println("3. View Client");
            System.out.println("4. Expense Report");
            System.out.println("5. Exit");

            option = inp.nextInt();
            switch (option){
                case 1:
                    System.out.print("Name: ");
                    inp.nextLine();
                    String name = inp.nextLine();
                    System.out.print("CPF: ");
                    String cpf = inp.nextLine();
                    try {
                        c1 = new Client(name, cpf);
                    } catch (IOException e) {
                        System.out.println(e.getMessage());
                    }
                    for (int i = 0; i < client.length; i++) {
                        if (client[i] == null){
                            if (c1 != null){
                                client[i] = c1;
                                break;
                            }
                        }
                    }
                    break;
                case 2:
                    System.out.println("Choose the client:");
                    int k = 1;
                    for (Client n1: client) {
                        if (client[0] == null){
                            System.out.println("No clients in data base, try again.");
                            break;
                        }
                        if(n1 != null) {
                            System.out.println(k + ": " + n1.getName() + " - CPF: " + n1.getCpf());
                            k++;
                        }
                    }
                    optionClient = inp.nextInt()-1;
                    System.out.println("What type of consumption?");
                    System.out.println("1. Food");
                    System.out.println("2. Transportation");
                    System.out.println("3. Health");
                    System.out.println("4. Leisure");
                    int optionC = inp.nextInt();
                    if (optionC < 1 || optionC > 4){
                        System.out.println("Invalid Option");
                        break;
                    }
                    System.out.println("What is value?");
                    int value = inp.nextInt();
                    inp.nextLine();
                    System.out.println("What is the consumption date?\nFormat: mm-dd-yyyy");
                    String dateHourString = inp.nextLine();
                    try {
                        if (isValid(dateHourString)) {
                            date = LocalDate.parse(dateHourString, formatter);
                        }
                    } catch (IOException e) {
                        System.out.println(e.getMessage());
                    } catch (DateTimeParseException e) {
                        System.out.println("Invalid format. Please use mm-dd-yyyy.");
                    }
                    Category category;
                    if (optionC == 1){
                        category = Category.FOOD;
                    } else if (optionC == 2) {
                        category = Category.TRANSPORTATION;
                    } else if (optionC == 3) {
                        category = Category.HEALTH;
                    } else {
                        category = Category.LEISURE;
                    }
                    Consumption c1 = new Consumption(value, date, category);
                    client[optionClient].inputConsumption(c1);
                    break;
                case 3:
                    System.out.println("Choose the client:");
                    int i = 1;
                    for (Client n1: client) {
                        if (client[0] == null){
                            System.out.println("No clients in data base, try again.");
                            break;
                        }
                        if(n1 != null) {
                            System.out.println(i + ": " + n1.getName() + " - CPF: " + n1.getCpf());
                            i++;
                        }
                    }
                    optionClient = inp.nextInt()-1;
                    System.out.println("Report:");
                    System.out.println("Name: " + client[optionClient].getName());
                    System.out.println("CPF: " + client[optionClient].getCpf());
                    System.out.println("Registration Date: " + client[optionClient].getRegistrationDate());
                    System.out.println("-------------------------");
                    System.out.println("Consumption:");
                    for (Consumption c: client[optionClient].getConsumptions()){
                        if (c != null){
                            System.out.println("Category: " + c.getCategory());
                            System.out.println("Value: $" + c.getValue());
                            System.out.println("Date of Consumption: " + c.getDate());
                            System.out.println("-------------------------");
                        }
                    }
                    break;
                case 4:
                    double[] valueFood = new double[12];
                    double[] valueTransportation = new double[12];
                    double[] valueHealth = new double[12];
                    double[] valueLeisure = new double[12];

                    System.out.println("Total consumption per month, by category in 2025:");

                    for (Client item : client) {
                        if (client[0] == null){
                            System.out.println("No clients in data base, try again.");
                            break;
                        }
                        if (item != null) {
                            for (Consumption c2 : item.getConsumptions()) {
                                if (c2 != null && c2.getDate().getYear() == 2025) {
                                    int monthIndex = c2.getDate().getMonth().getValue() - 1;
                                    switch (c2.getCategory()) {
                                        case FOOD:
                                            valueFood[monthIndex] += c2.getValue();
                                            break;
                                        case TRANSPORTATION:
                                            valueTransportation[monthIndex] += c2.getValue();
                                            break;
                                        case HEALTH:
                                            valueHealth[monthIndex] += c2.getValue();
                                            break;
                                        case LEISURE:
                                            valueLeisure[monthIndex] += c2.getValue();
                                            break;
                                    }
                                }
                            }
                        }
                    }
                    for (int m = 0; m < 12; m++) {
                        System.out.println("Month " + (m + 1) + ":");
                        System.out.println("FOOD: " + valueFood[m]);
                        System.out.println("TRANSPORTATION: " + valueTransportation[m]);
                        System.out.println("HEALTH: " + valueHealth[m]);
                        System.out.println("LEISURE: " + valueLeisure[m]);
                        System.out.println("----------------------------");
                    }
                    break;
                case 5:
                    System.out.println("Exit...");
                    break;
                default:
                    System.out.println("Invalid option, exit.");
                    break;
            }
        } while(option != 5);
    }
}