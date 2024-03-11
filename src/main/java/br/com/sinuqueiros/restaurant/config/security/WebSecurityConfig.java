package br.com.sinuqueiros.restaurant.config.security;

import br.com.sinuqueiros.restaurant.config.security.filters.JwtAuthorizationFilter;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {
    private final JwtAuthorizationFilter jwtAuthorizationFilter;

    @Bean
    @SneakyThrows
    public SecurityFilterChain securityWebFilterChain(final HttpSecurity httpSecurity) {
        httpSecurity
                .cors(AbstractHttpConfigurer::disable)
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorizationManagerRequestMatcherRegistry ->
                        {
                            final var productRoutePattern = "/v1/product/**";
                            final var orderRoutePattern = "/v1/order/**";
                            final var ROLE_KITCHEN = "KITCHEN";
                            final var ROLE_ADMIN = "ADMIN";
                            final var ROLE_USER = "USER";
                            authorizationManagerRequestMatcherRegistry
                                    .requestMatchers(
                                            antMatcher(HttpMethod.OPTIONS, "/**"),
                                            antMatcher("/v1/user/**")
                                    ).permitAll()
                                    .requestMatchers(
                                            antMatcher(HttpMethod.GET, "/v1/order")
                                    ).hasAnyRole(ROLE_USER, ROLE_KITCHEN)
                                    .requestMatchers(
                                            antMatcher(HttpMethod.GET, "/v1/product"),
                                            antMatcher(HttpMethod.GET, productRoutePattern),
                                            antMatcher(HttpMethod.GET, orderRoutePattern),
                                            antMatcher(HttpMethod.POST, "/v1/order")
                                    ).hasRole(ROLE_USER)
                                    .requestMatchers(
                                            antMatcher(HttpMethod.POST, "/v1/product"),
                                            antMatcher(HttpMethod.PATCH, productRoutePattern),
                                            antMatcher(HttpMethod.PUT, productRoutePattern),
                                            antMatcher("/v1/user/create")
                                    ).hasRole(ROLE_ADMIN)
                                    .requestMatchers(
                                            antMatcher(HttpMethod.PATCH, "/v1/order/*/*")
                                    ).hasRole(ROLE_KITCHEN)
                                    .anyRequest().authenticated();
                        }
                ).addFilterBefore(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class)
                .httpBasic(Customizer.withDefaults())
                .exceptionHandling(Customizer.withDefaults())
                .sessionManagement(httpSecuritySessionManagementConfigurer ->
                        httpSecuritySessionManagementConfigurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        return httpSecurity.build();
    }
}
