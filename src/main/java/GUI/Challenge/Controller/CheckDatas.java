package GUI.Challenge.Controller;

public class CheckDatas {
    public static String validate(String nameText, String emailText, String ageText, String passwordText){
        if (nameText.isEmpty() || emailText.isEmpty() || ageText.isEmpty() || passwordText.isEmpty()) {
            return "All fields must be completed.";
        }

        if (nameText.length() < 3) {
            return "Name must have at least 3 characters.";
        }

        if (!emailText.matches("^[^\\s@]+@[^\\s@]+\\.[^\\s@]+$")) {
            return "Invalid email format.";
        }

        if (!isAgeNumeric(ageText)) {
            return "Age should be numerical.";
        }

        int age = Integer.parseInt(ageText);
        if (age < 1 || age > 120) {
            return "Age must be between 1 and 120.";
        }

        if (passwordText.length() < 6) {
            return "Password must have at least 6 characters.";
        }

        return null;
    }

    private static boolean isAgeNumeric(String ageText){
        try {
            Integer.parseInt(ageText);
            return true;
        } catch (NumberFormatException e){
            return false;
        }
    }
}