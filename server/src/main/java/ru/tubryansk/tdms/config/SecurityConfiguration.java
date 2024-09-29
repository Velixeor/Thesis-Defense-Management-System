package ru.tubryansk.tdms.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;
import org.springframework.security.config.annotation.web.configurers.SessionManagementConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;


@Configuration
public class SecurityConfiguration {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity,
                                                   AuthenticationManager authenticationManager) throws Exception {
        return httpSecurity
                .authorizeHttpRequests(this::configureHttpAuthorization)
                .csrf(AbstractHttpConfigurer::disable)
                .cors(a -> a.configurationSource(corsConfiguration()))
                .authenticationManager(authenticationManager)
                .sessionManagement(this::configureSessionManagement)
                .build();
    }

    @Bean
    public CorsConfigurationSource corsConfiguration() {
        return request -> {
            CorsConfiguration corsConfiguration = new CorsConfiguration();
            corsConfiguration.applyPermitDefaultValues();
            corsConfiguration.addAllowedMethod("DELETE");
            corsConfiguration.addAllowedMethod("PUT");
            corsConfiguration.addAllowedMethod("PATCH");
            return corsConfiguration;
        };
    }

    private void configureHttpAuthorization(AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry httpAuthorization) {
        /* API ROUTES */
        httpAuthorization.requestMatchers("/api/v1/diploma-topic/**").permitAll();
        httpAuthorization.requestMatchers("/api/v1/user/login").permitAll();
        httpAuthorization.requestMatchers("/api/v1/user/registration").permitAll();
        httpAuthorization.requestMatchers("/api/v1/user/current").authenticated();
        httpAuthorization.requestMatchers("/api/**").denyAll();
        /* STATIC ROUTES */
        httpAuthorization.requestMatchers("/**").permitAll();
        /* OTHER */
        httpAuthorization.anyRequest().denyAll();

    }

    @Bean
    public AuthenticationManager authenticationManager(UserDetailsService userDetailsService) {
        return new ProviderManager(authenticationProvider(userDetailsService));
    }

    private AuthenticationProvider authenticationProvider(UserDetailsService userDetailsService) {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider(passwordEncoder());
        provider.setUserDetailsService(userDetailsService);
        return provider;
    }

    private PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    // todo: remove when login/logout is implemented, since we do not need automatically created session with no authentication
    private void configureSessionManagement(SessionManagementConfigurer<HttpSecurity> sessionManagement) {
        sessionManagement.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED);
    }
}
