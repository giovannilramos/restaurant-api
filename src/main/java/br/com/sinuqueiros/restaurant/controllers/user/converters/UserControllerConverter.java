package br.com.sinuqueiros.restaurant.controllers.user.converters;

import br.com.sinuqueiros.restaurant.controllers.user.requests.LoginRequest;
import br.com.sinuqueiros.restaurant.controllers.user.responses.LoginResponse;
import br.com.sinuqueiros.restaurant.services.user.dto.LoginDTO;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserControllerConverter {
    public static LoginResponse convertFromLoginDTOToLoginResponse(final LoginDTO loginDTO) {
        return new LoginResponse(loginDTO.getToken());
    }

    public static LoginDTO convertFromLoginRequestToLoginDTO(final LoginRequest loginRequest) {
        return LoginDTO.builder()
                .username(loginRequest.username())
                .password(loginRequest.password())
                .build();
    }
}
