package Exceptions.E4.Controller;
import Exceptions.E4.Domain.InvalidAgeException;
import Exceptions.E4.Domain.PersonAge;
import java.util.Scanner;

public class CreatorPerson {
    public void run() {
        Scanner ageInput = null;
        try {
            System.out.println("Open Scanner");
            ageInput = new Scanner(System.in);
            System.out.print("Enter the person age: ");
            PersonAge p1 = new PersonAge(ageInput.nextInt());
            System.out.println("Age: " + p1.getAge());
        } catch (InvalidAgeException e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            System.out.println("Closing Scanner");
            ageInput.close();
        }
    }
}