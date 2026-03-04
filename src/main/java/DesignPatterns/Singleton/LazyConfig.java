package DesignPatterns.Singleton;

import java.util.Map;

public class LazyConfig {
        private static final EagerConfig instance = new EagerConfig();
    private String appName;
    private String version;
    private String environment;
    private Map<String, String> settings;

    private EagerConfig() {}
}
