package DesignPatterns.Singleton;

public interface Config {
    String getAppName();
    String getVersion();
    String getEnvironment();
    void setProperty(String key, String value);
    String getProperty(String key);
    default String memoryRef() {
        return "Memory Ref: " + System.identityHashCode(this);
    };
}