package MethodReference.E4.Application;
import MethodReference.E4.Domain.MessagePrinter;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

public class Main {
    public static void main(String[] args) {
        MessagePrinter messagePrinter = new MessagePrinter();
        List<String> messages = List.of(
                "Hi", "Hello", "Have a good day",
                "Vai Corinthians", "Good Bye", "Good Night"
        );
        Consumer<String> consumer = messagePrinter::printMessage;

        for (String s: messages){
            consumer.accept(s);
        }
        System.out.println("-------------------------");

        Function<String, String> upper = String::toUpperCase;
        for (String s : messages) {
            consumer.accept(upper.apply(s));
        }
    }
}