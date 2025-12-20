package Exceptions.E6.Domain;

public class MockConnection implements AutoCloseable {
    public MockConnection(){
        System.out.println("Connection Success!");
    }

    public void close(){
        System.out.println("Closing connection!");
    }
}