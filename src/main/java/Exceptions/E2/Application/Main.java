package Exceptions.E2.Application;
import Exceptions.E2.Services.MyFileReader;

public class Main {
    public static void main(String[] args){
        MyFileReader reader = new MyFileReader();
        reader.readArchive("src/main/java/Exceptions/E2/ProblemQuestion.txt");
    }
}