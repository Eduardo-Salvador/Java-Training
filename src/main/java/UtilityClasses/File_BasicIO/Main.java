package UtilityClasses.File_BasicIO;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Main {
    public static void main(String[] args) {
        File file = new File("src/main/java/UtilityClasses/File_BasicIO/logs/app.log");
        if(!file.exists()){
            File parentDir = file.getParentFile();
            if (parentDir != null && !parentDir.exists()) {
                parentDir.mkdirs();
            }
            try {
                boolean created = file.createNewFile();
                if (created) {
                    System.out.println("Arquivo criado: " + file.getAbsolutePath());
                } else {
                    System.out.println("Arquivo já existe!");
                }
            } catch (IOException e) {
                System.err.println("Erro ao criar arquivo: " + e.getMessage());
            }
        } else {
            System.out.println("Arquivo já existe: " + file.getAbsolutePath());
        }

        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String timestamp = now.format(formatter);

        try(BufferedWriter bw = new BufferedWriter(new FileWriter("src/main/java/UtilityClasses/File_BasicIO/logs/app.log"))){
            int i = 0;
            while (i < 3) {
                bw.write(timestamp + " [INFO] Aplicação iniciada");
                bw.newLine();
                i++;
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());;
        }

        try (BufferedReader br = new BufferedReader(new FileReader("src/main/java/UtilityClasses/File_BasicIO/logs/app.log"))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("Archive size: " + file.length() + " bytes");
    }
}