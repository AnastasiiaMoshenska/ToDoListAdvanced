package ch.cern.todo;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "basic.auth")
public class BasicAuthConfig {
    private String username;
    private String password;
}
