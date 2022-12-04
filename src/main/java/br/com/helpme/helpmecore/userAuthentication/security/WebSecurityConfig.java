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

import br.com.helpme.helpmecore.userAuthentication.service.CustomUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private  CustomUserDetailService customUserDetailService;

    private  boolean securityDebug = true;


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic()
                .and()
                .authorizeHttpRequests()
                .antMatchers(HttpMethod.GET, "/login").permitAll()
                .antMatchers("/users/register").permitAll()
                .antMatchers("/resources/**").permitAll()
                .antMatchers(HttpMethod.GET, "/").hasRole("USER")
                .anyRequest().authenticated()
                .and()
                .csrf().disable()
                .formLogin(httpSecurityFormLoginConfigurer -> {
                    httpSecurityFormLoginConfigurer.loginPage("/login");
                    httpSecurityFormLoginConfigurer.defaultSuccessUrl("/index",true);
                    httpSecurityFormLoginConfigurer.usernameParameter("email");
                    httpSecurityFormLoginConfigurer.passwordParameter("password");
                    httpSecurityFormLoginConfigurer.failureUrl("/login?error=true");
                    })
                .logout()
                    .logoutUrl("/logout")
                    .logoutSuccessUrl("/login")
                    .invalidateHttpSession(true);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserDetailService)
                .passwordEncoder(passwordEncoder());

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder;
    }
}
