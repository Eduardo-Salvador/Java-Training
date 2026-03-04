package DesignPatterns.Singleton;

public class Main {
    public static void main(String[] args) {
        EagerConfig config = EagerConfig.getInstance();
        config.setAppName("MyApp");
        config.setVersion("1.0.0");
        config.setEnvironment("production");
        config.setSetting("database_url", "jdbc:mysql://localhost:3306/mydb");

        System.out.println("App Name: " + config.getAppName());
        System.out.println("Version: " + config.getVersion());
        System.out.println("Environment: " + config.getEnvironment());
        System.out.println("Database URL: " + config.getSetting("database_url"));
        System.out.println("Instance: " + config);
    }
}