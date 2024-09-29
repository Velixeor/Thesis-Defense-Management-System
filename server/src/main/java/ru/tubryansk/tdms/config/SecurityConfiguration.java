package ru.tubryansk.tdms.config;


import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;
import org.springframework.security.config.annotation.web.configurers.SessionManagementConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.web.context.HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY;


@Configuration
public class SecurityConfiguration {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .authorizeHttpRequests(this::configureHttpAuthorization)
                .csrf(AbstractHttpConfigurer::disable)
                .cors(AbstractHttpConfigurer::disable)
                .authenticationManager(authenticationManager())
                .sessionManagement(this::configureSessionManagement)
                .build();
    }

    private void configureHttpAuthorization(AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry httpAuthorization) {
        /* API ROUTES */
        httpAuthorization.requestMatchers("/api/diploma-topic/**").permitAll();
        httpAuthorization.requestMatchers("/api/**").denyAll();
        /* STATIC ROUTES */
        httpAuthorization.requestMatchers("/**").permitAll();
        /* OTHER */
        httpAuthorization.anyRequest().denyAll();
    }

    @Bean
    public AuthenticationManager authenticationManager() {

        return new ProviderManager(authenticationProvider());
    }

    private AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider(passwordEncoder());
        provider.setUserDetailsService(userDetailsService());
        return provider;
    }

    private UserDetailsService userDetailsService() {
        return new InMemoryUserDetailsManager(User.builder()
                .username("admin")
                .password("{noop}admin")
                .authorities("ROLE_STUDENT", "ROLE_TEACHER", "ROLE_CURATOR")
                .build());
    }

    private PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    // todo: remove when login/logout is implemented
    public HttpSessionListener autoAuthenticateUnderAdmin() {
        return new HttpSessionListener() {
            @Override
            public void sessionCreated(HttpSessionEvent se) {
                SecurityContext context = SecurityContextHolder.createEmptyContext();
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken("admin", "admin");
                Authentication authenticated = authenticationManager().authenticate(authentication);
                context.setAuthentication(authenticated);
                SecurityContextHolder.setContext(context);
                se.getSession().setAttribute(SPRING_SECURITY_CONTEXT_KEY, context);
            }
        };
    }

    // todo: remove when login/logout is implemented, since we do not need automatically created session with no authentication
    private void configureSessionManagement(SessionManagementConfigurer<HttpSecurity> sessionManagement) {
        sessionManagement.sessionCreationPolicy(SessionCreationPolicy.ALWAYS);
    }
}
