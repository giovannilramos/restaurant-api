package br.com.sinuqueiros.restaurant.config.security.filters;

import br.com.sinuqueiros.restaurant.repositories.UserRepository;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import br.com.sinuqueiros.restaurant.config.security.service.UserDetailsServiceImpl;


import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import static java.util.Objects.isNull;

@Component
@RequiredArgsConstructor
public class AuthSuccessHandler {
    private static final String TOKEN_PREFIX = "Bearer ";
    private final UserRepository userRepository;
    private final UserDetailsServiceImpl userDetailsService;
    @Value("${jwt.expiration}")
    private Integer expTime;
    @Value("${jwt.secret}")
    private String secret;

    @SneakyThrows
    public String generateToken(final User userDetails) {
        final var user = userRepository.findByUsername(userDetails.getUsername()).orElseThrow(() -> new UsernameNotFoundException("Username not found"));
        return JWT.create()
                .withIssuer("API Restaurant")
                .withSubject(user.getUsername())
                .withExpiresAt(Instant.ofEpochMilli(ZonedDateTime.now(ZoneId.of("America/Sao_Paulo")).toInstant().toEpochMilli() + expTime))
                .sign(Algorithm.HMAC256(secret));
    }

    public UsernamePasswordAuthenticationToken getAuthentication(final HttpServletRequest request) {
        final var token = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (isNull(token) || !token.startsWith(TOKEN_PREFIX)) {
            return null;
        }
        final var email = JWT.require(Algorithm.HMAC256(secret))
                .withIssuer("API Restaurant")
                .build()
                .verify(token.replace(TOKEN_PREFIX, ""))
                .getSubject();
        if (isNull(email) || email.isEmpty()) {
            return null;
        }
        final var userDetails = userDetailsService.loadUserByUsername(email);

        return new UsernamePasswordAuthenticationToken(userDetails.getUsername(), null, userDetails.getAuthorities());
    }
}