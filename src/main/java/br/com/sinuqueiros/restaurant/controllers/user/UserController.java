package br.com.sinuqueiros.restaurant.controllers.user;

import br.com.sinuqueiros.restaurant.controllers.user.requests.LoginRequest;
import br.com.sinuqueiros.restaurant.controllers.user.responses.LoginResponse;
import br.com.sinuqueiros.restaurant.services.user.LoginService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static br.com.sinuqueiros.restaurant.controllers.user.converters.UserControllerConverter.convertFromLoginDTOToLoginResponse;
import static br.com.sinuqueiros.restaurant.controllers.user.converters.UserControllerConverter.convertFromLoginRequestToLoginDTO;

@RestController
@RequestMapping("/v1/user")
@RequiredArgsConstructor
public class UserController {
    private final LoginService loginService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody @Valid final LoginRequest loginRequest) {
        final var loginDTO = loginService.login(convertFromLoginRequestToLoginDTO(loginRequest));
        return ResponseEntity.ok(convertFromLoginDTOToLoginResponse(loginDTO));
    }
}
