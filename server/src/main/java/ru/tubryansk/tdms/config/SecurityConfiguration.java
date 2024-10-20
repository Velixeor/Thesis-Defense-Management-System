package ru.tubryansk.tdms.config;


import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpMethod;
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
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import java.time.Duration;
import java.util.List;

import static org.springframework.security.web.context.HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY;


@Configuration
public class SecurityConfiguration {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity,
                                                   AuthenticationManager authenticationManager,
                                                   @Qualifier("corsConfig") CorsConfigurationSource cors) throws Exception {
        return httpSecurity
                .authorizeHttpRequests(this::configureHttpAuthorization)
                .csrf(AbstractHttpConfigurer::disable)
                .cors(a -> a.configurationSource(cors))
                .authenticationManager(authenticationManager)
                .sessionManagement(this::configureSessionManagement)
                .build();
    }

    @Bean
    @Profile("dev")
    @Qualifier("corsConfig")
    public CorsConfigurationSource corsConfigurationDev() {
        return request -> {
            CorsConfiguration corsConfiguration = new CorsConfiguration();
            corsConfiguration.applyPermitDefaultValues();
            corsConfiguration.addAllowedMethod("DELETE");
            corsConfiguration.addAllowedMethod("PUT");
            corsConfiguration.addAllowedMethod("PATCH");
            return corsConfiguration;
        };
    }

    @Bean
    @Profile("!dev")
    @Qualifier("corsConfig")
    public CorsConfigurationSource corsConfigurationProd(@Value("${application.domain}") String domain,
                                                         @Value("${application.port}") String port,
                                                         @Value("${application.protocol}") String protocol) {
        return request -> {
            String url = StringUtils.join(protocol, "://", domain, ":", port);
            CorsConfiguration corsConfiguration = new CorsConfiguration();
            corsConfiguration.setMaxAge(Duration.ofHours(1));
            corsConfiguration.addAllowedOrigin(url);
            corsConfiguration.setAllowedMethods(List.of(HttpMethod.GET.name(), HttpMethod.POST.name()));
            // corsConfiguration.setAllowedHeaders();
            return corsConfiguration;
        };
    }

    private void configureHttpAuthorization(AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry httpAuthorization) {
        /* API ROUTES */
        httpAuthorization.requestMatchers("/api/v1/user/logout").authenticated();
        httpAuthorization.requestMatchers("/api/v1/user/login").anonymous();
        httpAuthorization.requestMatchers("/api/v1/user/current").permitAll();

        httpAuthorization.requestMatchers("/api/v1/student/current").permitAll();

        httpAuthorization.requestMatchers("/api/**").denyAll();
        /* STATIC ROUTES */
        httpAuthorization.requestMatchers("/**").permitAll();
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

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    @Profile("dev")
    public HttpSessionListener autoAuthenticateUnderAdmin(AuthenticationManager authenticationManager) {
        return new HttpSessionListener() {
            @Override
            public void sessionCreated(HttpSessionEvent se) {
                String username = "akulenko_mikhail";
                LoggerFactory.getLogger(this.getClass()).info("Session created {}. Authenticated, as {}", se.getSession().getId(), username);
                SecurityContext context = SecurityContextHolder.createEmptyContext();
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(username, "1");
                Authentication authenticated = authenticationManager.authenticate(authentication);
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
