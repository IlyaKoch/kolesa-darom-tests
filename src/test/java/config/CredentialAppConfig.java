package config;

import org.aeonbits.owner.Config;

@Config.Sources({"classpath:config/app.properties"})
public interface CredentialAppConfig extends Config {

    String userPhone();

    String userPassword();
}