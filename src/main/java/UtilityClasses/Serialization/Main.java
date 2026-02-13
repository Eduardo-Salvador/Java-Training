package UtilityClasses.Serialization;
import java.io.*;

public class Main {
    public static void main(String[] args) {
        Player p1 = new Player("Eduardo", 25, 1932.23, "123");
        try (ObjectOutputStream out =
                new ObjectOutputStream(new FileOutputStream("player.ser"))){
            out.writeObject(p1);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        try (ObjectInputStream in =
                     new ObjectInputStream(new FileInputStream("player.ser"))){
            Player p = (Player) in.readObject();
            System.out.println(p.getName() + " - " + p.getLevel() + " - " + p.getPoints() + " - " + p.getPassword());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}