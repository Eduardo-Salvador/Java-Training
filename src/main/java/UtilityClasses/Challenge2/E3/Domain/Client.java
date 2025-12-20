package UtilityClasses.Challenge2.E3.Domain;
import java.io.IOException;
import java.time.LocalDate;

public class Client {
    private String name;
    private String cpf;
    private LocalDate registrationDate = LocalDate.now();
    private Consumption[] consumptions = new Consumption[100];

    public Client(String name, String cpf) throws IOException {
        this.name = name;
        if (cpf.length() != 14){
            throw new IOException("Invalid CPF, value format: 000.000.000-00");
        }
        this.cpf = cpf;
    }

    public void inputConsumption(Consumption c1){
        for (int i = 0; i < consumptions.length; i++) {
            if (consumptions[i] == null){
                consumptions[i] = c1;
                System.out.println("Input consumption is successfully");
                break;
            }
        }
    }

    public String getName() {
        return name;
    }

    public String getCpf() {
        return cpf;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public Consumption[] getConsumptions() {
        return consumptions;
    }
}