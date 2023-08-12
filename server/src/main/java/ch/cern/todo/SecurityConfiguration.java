package ch.cern.todo;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableConfigurationProperties(BasicAuthConfigProperties.class)
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final BasicAuthConfigProperties basicAuth;

    public SecurityConfiguration(@Qualifier("basicAuthConfigProperties") BasicAuthConfigProperties basicAuth) {
        this.basicAuth = basicAuth;
    }

    protected void configure(HttpSecurity http) throws Exception {
        http.cors();
    }
}