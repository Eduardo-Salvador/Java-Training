package Exceptions.E5.Controller;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class WriteText {
    public void run() {
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter("error/src/Exceptions/E5/ProblemQuestion.txt", true));
            writer.newLine();
            writer.write("Text Line");
            writer.newLine();
            writer.write("TextLine2");
            System.out.println("Write Success!");
        } catch (IOException e) {
            System.out.println("Write error: " + e.getMessage());
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    System.err.println("Error closing the writer: " + e.getMessage());
                }
            }
        }
    }
}