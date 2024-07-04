package com.inventario.rasa.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

// import com.inventario.rasa.security.jwt.JwtAuthenticationEntryPoint;
import com.inventario.rasa.security.jwt.JwtAuthenticationFilter;
import com.inventario.rasa.security.service.UserDetailsServiceImpl;


// @RequiredArgsConstructor
@Configuration
@EnableMethodSecurity
public class SecurityConfig {
    
    @Autowired
    private UserDetailsServiceImpl userDetailsService;
    
    // @Autowired
    // private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    public JwtAuthenticationFilter authenticationJwtTokenFilter(){
        return new JwtAuthenticationFilter();
    }


    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
        // .addFilter(new JwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
        // .exceptionHandling(exception -> exception.authenticationEntryPoint(jwtAuthenticationEntryPoint))
        .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .authorizeHttpRequests(auth -> 
            auth.requestMatchers("/auth/**").permitAll()
                .requestMatchers("/user/**").permitAll()
                .requestMatchers("/rol/**").permitAll() 
                .requestMatchers("/categoria/**").permitAll() 
                .requestMatchers("/proveedor/**").permitAll() 
                .requestMatchers("/almacen/**").permitAll() 
                .requestMatchers("/producto/**").permitAll() 
                .requestMatchers("/orden/entrada/**").permitAll() 
                .requestMatchers("/orden/salida/**").permitAll() 
                .anyRequest().authenticated()
        );
        
        http.authenticationProvider(authenticationProvider());
        http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
