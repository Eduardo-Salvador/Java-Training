package UtilityClasses.ZonedDateTime_ZoneID;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class Main {
    public static void main(String[] args) {
        ZonedDateTime saoPaulo = ZonedDateTime.of(
                LocalDateTime.of(2025, 12, 25, 10, 0),
                ZoneId.of("America/Sao_Paulo"));

        ZonedDateTime tokyo = saoPaulo.withZoneSameInstant(ZoneId.of("Asia/Tokyo"));
        ZonedDateTime novaYork = saoPaulo.withZoneSameInstant(ZoneId.of("America/New_York"));
        ZonedDateTime london = saoPaulo.withZoneSameInstant(ZoneId.of("Europe/London"));

        System.out.println("São Paulo: " + saoPaulo);
        System.out.println("Tokyo:     " + tokyo);
        System.out.println("Nova York: " + novaYork);
        System.out.println("Londres:   " + london);

        System.out.println("\nTime difference compared to São Paulo:");

        diff(saoPaulo, tokyo, "Tokyo");
        diff(saoPaulo, novaYork, "Nova York");
        diff(saoPaulo, london, "Londres");
    }

    private static void diff(ZonedDateTime base, ZonedDateTime other, String nome) {
        int baseOffset = base.getOffset().getTotalSeconds() / 3600;
        int otherOffset = other.getOffset().getTotalSeconds() / 3600;
        int diference = otherOffset - baseOffset;
        System.out.println(nome + ": " + diference + " hours");
    }
}