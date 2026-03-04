package DesignPatterns.Singleton;
import java.util.Map;
import java.util.HashMap;   

public class EagerConfig {
    private static final EagerConfig instance = new EagerConfig();
    private String appName;
    private String version;
    private String environment;
    private Map<String, String> settings;

    private EagerConfig() {}

    public static EagerConfig getInstance() {
        return instance;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getEnvironment() {
        return environment;
    }

    public void setEnvironment(String environment) {
        this.environment = environment;
    }

    public String getSetting(String key) {
        return settings.get(key);
    }

    public void setSetting(String key, String value) {
        if (settings == null) {
            settings = new HashMap<>();
        }
        settings.put(key, value);
    }
}
