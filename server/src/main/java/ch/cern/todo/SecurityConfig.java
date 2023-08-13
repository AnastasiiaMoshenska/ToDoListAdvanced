package ch.cern.todo;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableConfigurationProperties(BasicAuthConfig.class)
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final BasicAuthConfig basicAuth;

    public SecurityConfig(@Qualifier("basicAuthConfig") BasicAuthConfig basicAuth) {
        this.basicAuth = basicAuth;
    }

    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors()
                    .and()
                .authorizeRequests()
                    .antMatchers("/login").permitAll()
                    .anyRequest().authenticated()
                    .and()
                .formLogin()
                    .loginPage("/login")
                    .permitAll()
                    .and()
                .logout()
                    .permitAll();
    }
}