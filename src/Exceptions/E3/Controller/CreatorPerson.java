package Exceptions.E3.Controller;
import Exceptions.E3.Domain.InvalidAgeException;
import Exceptions.E3.Domain.PersonAge;
import java.util.Scanner;

public class CreatorPerson {
    public void run(){
        try(Scanner ageInput = new Scanner(System.in)) {
            System.out.print("Enter the person age: ");
            PersonAge p1 = new PersonAge(ageInput.nextInt());
            System.out.println("Age: " + p1.getAge());
        } catch (InvalidAgeException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
