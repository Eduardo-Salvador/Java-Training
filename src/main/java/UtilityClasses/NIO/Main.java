package UtilityClasses.NIO;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.*;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {
            Path docsDir = Paths.get("src/main/java/UtilityClasses/NIO/docs");
            Files.createDirectories(docsDir);

            Path p1 = docsDir.resolve("doc1.txt");
            Path p2 = docsDir.resolve("doc2.txt");
            Path p3 = docsDir.resolve("doc3.txt");

            Files.write(p1, List.of("Content of document 1", "Line 2 of doc1"));
            Files.write(p2, List.of("Content of document 2", "Line 2 of doc2"));
            Files.write(p3, List.of("Content of document 3", "Line 2 of doc3"));

            System.out.println("Files created successfully!\n");

            System.out.println("=== LISTING FILES ===");
            Files.list(docsDir).forEach(path -> {
                if (Files.isDirectory(path)) {
                    System.out.println("[DIR]  " + path.getFileName());
                } else {
                    System.out.println("[FILE] " + path.getFileName());
                }
            });

            System.out.println("\n=== READING FILE CONTENTS ===");
            Files.list(docsDir)
                    .filter(path -> path.toString().endsWith(".txt"))
                    .forEach(path -> {
                        try {
                            System.out.println("\n" + path.getFileName() + ":");
                            List<String> lines = Files.readAllLines(path);
                            lines.forEach(line -> System.out.println("  " + line));
                        } catch (IOException e) {
                            System.err.println("Error reading " + path.getFileName());
                        }
                    });

            Path backupDir = docsDir.resolve("backup");
            Files.createDirectories(backupDir);
            System.out.println("\n=== BACKUP DIRECTORY CREATED ===");

            System.out.println("\n=== COPYING FILES TO BACKUP ===");
            Files.list(docsDir)
                    .filter(path -> path.toString().endsWith(".txt"))
                    .forEach(path -> {
                        try {
                            Path destination = backupDir.resolve(path.getFileName());
                            Files.copy(path, destination, StandardCopyOption.REPLACE_EXISTING);
                            System.out.println("Copied: " + path.getFileName());
                        } catch (IOException e) {
                            System.err.println("Error copying " + path.getFileName());
                        }
                    });

            System.out.println("\n=== FILES IN BACKUP ===");
            Files.list(backupDir).forEach(path ->
                    System.out.println("[FILE] " + path.getFileName())
            );

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}