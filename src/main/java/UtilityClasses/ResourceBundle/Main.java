package UtilityClasses.ResourceBundle;
import java.util.Locale;
import java.util.ResourceBundle;

public class Main {
    public static void main(String[] args) {
        ResourceBundle rb = ResourceBundle.getBundle("UtilityClasses.ResourceBundle.messages", Locale.of("pt", "BR"));
        String key1 = "welcome";
        String key2 = "goodbye";
        if (rb.containsKey(key1) && rb.containsKey(key2)) {
            System.out.println(rb.getString(key1));
            System.out.println(rb.getString(key2));
            return;
        }
        System.out.println("Keys not found");
    }
}