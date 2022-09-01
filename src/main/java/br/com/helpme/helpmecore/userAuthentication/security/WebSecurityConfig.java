/*
package br.com.helpme.helpmecore.authentication.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.web.SecurityFilterChain;

import javax.servlet.Filter;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@EnableWebSecurity
public class SecurityConfigurations implements SecurityFilterChain, WebSecurityCustomizer {


    @Override
    public void customize(WebSecurity web) {

    }

    @Override
    public boolean matches(HttpServletRequest request) {
        return false;
    }

    @Override
    public List<Filter> getFilters() {
        return null;
    }
}
*/
package br.com.helpme.helpmecore.userAuthentication.security;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
public class WebSecurityConfig {


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        httpSecurity.authorizeRequests( (requests) -> {
                        requests
                            .anyRequest().authenticated();
                    })
                    .formLogin((form) -> {
                        form.loginPage("/login")
                        .permitAll();
                    })
                    .logout( (logout) -> logout.permitAll());

        return httpSecurity.build();
    }

  /*  @Bean
    public UserDetailsService userDetailsService() throws Exception {
        // ensure the passwords are encoded properly
        User.UserBuilder users = User.withDefaultPasswordEncoder();
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(users.username("user").password("password").roles("USER").build());
        manager.createUser(users.username("admin").password("password").roles("USER","ADMIN").build());
        return manager;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
           .authorizeRequests(authorize ->
                   authorize.anyRequest()
                            .authenticated()
           )
           .formLogin(Customizer.withDefaults())
           .httpBasic(Customizer.withDefaults());

           return http.build();
    }*/
}
