package Exceptions.E5.Services;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class WriteText {
    public void run() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("error/test/ProblemQuestion.txt", true))){
            writer.newLine();
            writer.write("Text Line");
            System.out.println("Write Success!");
        } catch (IOException e) {
            System.out.println("Write error: " + e.getMessage());
        }
    }
}