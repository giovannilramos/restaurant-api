package br.com.sinuqueiros.restaurant.config.security.filters;

import br.com.sinuqueiros.restaurant.config.security.service.UserDetailsServiceImpl;
import br.com.sinuqueiros.restaurant.repositories.UserRepository;
import br.com.sinuqueiros.restaurant.services.user.dto.LoginDTO;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.HashMap;

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

    public LoginDTO generateToken(final User userDetails) {
        final var user = userRepository.findByUsername(userDetails.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("Username not found"));
        final var hashMap = new HashMap<String, Object>();
        hashMap.put("tableNumber", user.getTableNumber());
        user.getRoles().stream().findFirst()
                .ifPresent(rolesEntity -> hashMap.put("roles", rolesEntity.getRoleName().name()));

        final var expiresIn = Instant.ofEpochMilli(ZonedDateTime.now(ZoneId.of("America/Sao_Paulo")).toInstant().toEpochMilli() + expTime);
        final var token = JWT.create()
                .withIssuer("API Restaurant")
                .withSubject(user.getUsername())
                .withPayload(hashMap)
                .withClaim("roles", user.getRoles().stream().map(rolesEntity -> rolesEntity.getRoleName().name()).toList())
                .withExpiresAt(expiresIn)
                .sign(Algorithm.HMAC256(secret));

        return LoginDTO.builder()
                .token(token)
                .expiresIn(expiresIn.toEpochMilli())
                .build();
    }

    public UsernamePasswordAuthenticationToken getAuthentication(final HttpServletRequest request) {
        final var token = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (isNull(token) || !token.startsWith(TOKEN_PREFIX)) {
            return null;
        }
        final var username = JWT.require(Algorithm.HMAC256(secret))
                .withIssuer("API Restaurant")
                .build()
                .verify(token.replace(TOKEN_PREFIX, ""))
                .getSubject();
        if (isNull(username) || username.isEmpty()) {
            return null;
        }
        final var userDetails = userDetailsService.loadUserByUsername(username);

        return new UsernamePasswordAuthenticationToken(userDetails.getUsername(), null, userDetails.getAuthorities());
    }
}