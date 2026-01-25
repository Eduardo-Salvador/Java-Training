package Exceptions.E2.Services;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class MyFileReader {
    public void readArchive(String archiveName) {
        try (FileReader fileReader = new java.io.FileReader(archiveName);
             BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Archive not found: " + archiveName);
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        } catch (NullPointerException e){
            System.out.println("NullPointerException: " + e.getMessage());
        }
    }
}