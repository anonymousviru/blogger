package com.blog.Blogger.config;
import com.blog.Blogger.security.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.DefaultSecurityFilterChain;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)//to use preauth
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
    @Autowired
    private CustomUserDetailsService userDetailsService;
    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception{
        return super.authenticationManagerBean();
    }
//@Autowired
//    private PasswordEncoder passwordEncoder;
    @Override
    public void configure(HttpSecurity http) throws  Exception{
    http
            .csrf().disable()
            .authorizeRequests()
            .antMatchers(HttpMethod.GET,"/api/**").permitAll()
            .antMatchers((HttpMethod.POST),"/api/auth/**").permitAll()
            .anyRequest()
            .authenticated()
            .and()
            .httpBasic();
}
//in memory authentication for testing

//@Bean
//    @Override
//    protected UserDetailsService userDetailsService() {
//        //object's consisting of un pw
//    UserDetails user = User.builder().username("ram").
//            password(passwordEncoder.encode("ram")).roles("USER").build();
//    UserDetails admin = User.builder().username("admin").
//            password(passwordEncoder.encode("admin")).roles("ADMIN").build();
//
//    return new InMemoryUserDetailsManager(user,admin);
//    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(getEncodedPassword());
    }


    @Bean
    public PasswordEncoder getEncodedPassword(){
        return new BCryptPasswordEncoder();
    }
}




//User.builder() is a convenient way to create instances of the User class using
// the Builder pattern.
// The User class represents a user in the context of authentication and authorization.
//antmacher we configure  which url who can access
//in memory authentication is teachnique where we store un pwand roles in it
// and perform testing if succeed then store in db
//implimentation of spring security
//1 add spring security librabry
//2 sign in metho with payload dto which has authentication manager.authenticate method
//3 loadByUsername takes data from db
//4 then expected value from UsernamePasswordAuthenticationToken and original value from loadbyusername
////both are compared by authenticate and if the record is found then SecurityContextHolder will set the
// value and can use the application
//if invalid then it tell unauth user