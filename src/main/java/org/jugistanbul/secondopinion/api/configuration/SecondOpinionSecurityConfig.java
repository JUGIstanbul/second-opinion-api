package org.jugistanbul.secondopinion.api.configuration;

import org.jugistanbul.secondopinion.api.configuration.properties.SecondOpinionSecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecondOpinionSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    SecondOpinionSecurityProperties secondOpinionSecurityProperties;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .headers()
                .frameOptions()
                .disable()
                .and()
                .authorizeRequests()
                .antMatchers("/**")
                .authenticated()
                .and()
                .httpBasic();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser(secondOpinionSecurityProperties.getUsername())
                .password(secondOpinionSecurityProperties.getPassword())
                .roles("USER");
    }
}
