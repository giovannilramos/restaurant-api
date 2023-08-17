package br.com.sinuqueiros.restaurant.config.security.filters;

import br.com.sinuqueiros.restaurant.handlers.responses.ExceptionResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.micrometer.common.lang.NonNullApi;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.time.ZoneId;
import java.time.ZonedDateTime;

import static br.com.sinuqueiros.restaurant.handlers.RestExceptionHandler.AMERICA_SAO_PAULO;
import static java.util.Objects.isNull;

@Component
@RequiredArgsConstructor
@NonNullApi
public class JwtAuthorizationFilter extends OncePerRequestFilter {
    private final AuthSuccessHandler authSuccessHandler;

    @Override
    @SneakyThrows
    protected void doFilterInternal(final HttpServletRequest request, final HttpServletResponse response, final FilterChain chain) {
        try {
            final var auth = authSuccessHandler.getAuthentication(request);
            if (isNull(auth)) {
                chain.doFilter(request, response);
                return;
            }
            SecurityContextHolder.getContext().setAuthentication(auth);
            chain.doFilter(request, response);
        } catch (final Exception e) {
            final var responseStream = response.getOutputStream();
            final var mapper = new ObjectMapper();
            final var exception = new ExceptionResponse("Token expired, log in again", ZonedDateTime.now(ZoneId.of(AMERICA_SAO_PAULO)));

            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            mapper.writeValue(responseStream, exception);
            responseStream.flush();
        }
    }
}