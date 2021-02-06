/*package com.newjerseysoftware.hederaDemo.security;

import com.newjerseysoftware.hederaDemo.service.UserDetailsServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.context.annotation.Bean;

import static com.newjerseysoftware.hederaDemo.security.SecurityConstants.SIGN_UP_URL;

@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {

    private UserDetailsServiceImpl userDetailsService;

    public WebSecurity(UserDetailsServiceImpl userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.cors()
                .and()
                .csrf()
                .disable()
                .authorizeRequests() // Add a new custom security filter
                .antMatchers(HttpMethod.POST, SIGN_UP_URL)
                .permitAll() // Only Allow Permission for create user endpoint
                .anyRequest()
                .authenticated()
                .and()
                .addFilter( this.getJWTAuthenticationFilter())
                .addFilter(new JWTAuthorizationFilter(authenticationManager()))
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS); // this disables session creation on Spring Security
    }

    public JWTAuthenticationFilter getJWTAuthenticationFilter() throws Exception {
        final JWTAuthenticationFilter filter = new JWTAuthenticationFilter(authenticationManager());
        filter.setFilterProcessesUrl("/api/v1.0/login");
        return filter;
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
        return source;
    }
}*/
