package UtilityClasses.Regex_Pattern_Matcher;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        String contact = "Contact: joao@email.com or maria@empresa.com.br";
        Pattern p = Pattern.compile("[\\w.-]+@[\\w.-]+\\.\\w+");
        Matcher m = p.matcher(contact);
        int i = 0;
        while (m.find()){
            System.out.println("Email found! " + m.group());
            i++;
        }
        System.out.println("Total emails: " + i);
    }
}