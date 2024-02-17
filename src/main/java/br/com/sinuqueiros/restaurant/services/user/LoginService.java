package br.com.sinuqueiros.restaurant.services.user;

import br.com.sinuqueiros.restaurant.config.security.filters.AuthSuccessHandler;
import br.com.sinuqueiros.restaurant.exceptions.NotFoundException;
import br.com.sinuqueiros.restaurant.services.user.dto.LoginDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {
    private final AuthenticationManager authenticationManage;
    private final AuthSuccessHandler authSuccessHandler;

    public LoginDTO login(final LoginDTO loginDTO) {
        try {
            final var authenticationToken = new UsernamePasswordAuthenticationToken(loginDTO.getUsername(), loginDTO.getPassword());
            final var authentication = authenticationManage.authenticate(authenticationToken);
            final var tokenJwt = authSuccessHandler.generateToken((User) authentication.getPrincipal());
            loginDTO.setToken(tokenJwt);
            return loginDTO;
        } catch (final Exception e) {
            throw new NotFoundException("Login not found, review the information and try again");
        }
    }
}
