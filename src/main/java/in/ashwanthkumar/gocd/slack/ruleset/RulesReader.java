package in.ashwanthkumar.gocd.slack.ruleset;

import java.io.File;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

public class RulesReader {

    public static Rules read() {
        return new RulesReader().load();
    }

    public static Rules read(File file) {
        return new RulesReader().load(file);
    }

    public static Rules read(String file) {
        return new RulesReader().load(ConfigFactory.parseResources(file));
    }

    protected Rules load(Config config) {
        Config envThenSystem = ConfigFactory.systemEnvironment().withFallback(ConfigFactory.systemProperties());
        Config configWithFallback = config.withFallback(ConfigFactory.load(getClass().getClassLoader())).resolveWith(envThenSystem);
        return Rules.fromConfig(configWithFallback.getConfig("gocd.slack"));
    }

    public Rules load() {
        return load(ConfigFactory.load());
    }

    public Rules load(File file) {
        return load(ConfigFactory.parseFile(file));
    }
}
