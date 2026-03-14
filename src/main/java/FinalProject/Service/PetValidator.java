package FinalProject.Service;

public class PetValidator {
    public static boolean validName(String name){
        boolean numbers = name.matches(".*\\d.*");
        return numbers && name.length() > 2;
    }

    public static boolean isValidAge(Double age){
        return age > 0.0 || age < 20.0;
    }

    public static boolean isValidWeight(Double weight){
        return weight > 0.5 || weight < 60.0;
    }

    public static boolean isValidAddress(String address) {
        if (address == null || address.isBlank()) return true;

        String[] parts = address.split(",");
        return parts.length >= 3 && !parts[0].isBlank() && !parts[1].isBlank() && !parts[2].isBlank();
    }
}