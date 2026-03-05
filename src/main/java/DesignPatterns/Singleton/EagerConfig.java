package DesignPatterns.Singleton;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class EagerConfig implements Config {
    private static final EagerConfig INSTANCE = new EagerConfig();
    private String appName;
    private String version;
    private String environment;
    private Map<String, String> property = new ConcurrentHashMap<>();

    private EagerConfig() {}

    public static EagerConfig getInstance() {
        return INSTANCE;
    }

    @Override
    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    @Override
    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    @Override
    public String getEnvironment() {
        return environment;
    }

    public void setEnvironment(String environment) {
        this.environment = environment;
    }

    @Override
    public String getProperty(String key) {
        return property.get(key);
    }

    @Override
    public void setProperty(String key, String value) {
        property.put(key, value);
    }

    @Override
    public String toString() {
        return "App Name: " + getAppName() +
                "\n  Version: " + getVersion() +
                "\n  Environment: " + getEnvironment() +
                "\n  Database URL: " + getProperty("database_url");
    }
}