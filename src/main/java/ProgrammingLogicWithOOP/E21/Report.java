package ProgrammingLogicWithOOP.E21;
import java.util.Random;

public class Report {
    private int randomNumber(){
        System.out.println("Random Number from 0 to 100:");
        Random gerador = new Random();
        return gerador.nextInt(101);
    }

    public void runReport(){
        System.out.println("Algorithm 21:");
        System.out.println(randomNumber());
    }
}