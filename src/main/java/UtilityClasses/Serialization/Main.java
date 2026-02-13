package UtilityClasses.Serialization;
import java.io.*;

public class Main {
    public static void main(String[] args) {
        Player p1 = new Player("Eduardo", 25, 1932.23, "password123");

        System.out.println("=== SAVE PLAYER ===");
        System.out.println("Before Save: " + p1);

        savePlayer(p1, "player.ser");

        System.out.println("\n=== LOADING PLAYER ===");

        Player p2 = loadPlayer("player.ser");

        if (p2 != null) {
            System.out.println("After loading: " + p2);
        }
    }

    private static void savePlayer(Player player, String filename) {
        try (ObjectOutputStream out =
                     new ObjectOutputStream(new FileOutputStream(filename))) {

            out.writeObject(player);
            System.out.println("Player save with success: " + filename);

        } catch (FileNotFoundException e) {
            System.err.println("Error: No possible create de file");
            e.printStackTrace();
        } catch (IOException e) {
            System.err.println("Error to save a player: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static Player loadPlayer(String filename) {
        File file = new File(filename);

        if (!file.exists()) {
            System.err.println("Error: Archive " + filename + " not exists!");
            return null;
        }

        try (ObjectInputStream in =
                     new ObjectInputStream(new FileInputStream(file))) {

            Player player = (Player) in.readObject();
            System.out.println("Player loading with success!");
            return player;

        } catch (FileNotFoundException e) {
            System.err.println("Error: File not found!");
            e.printStackTrace();
        } catch (IOException e) {
            System.err.println("Error to read file: " + e.getMessage());
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.err.println("Error: Player class not found!");
            e.printStackTrace();
        }
        return null;
    }
}