package br.com.sinuqueiros.restaurant.controllers.user.converters;

import br.com.sinuqueiros.restaurant.controllers.user.requests.CreateUserRequest;
import br.com.sinuqueiros.restaurant.controllers.user.requests.LoginRequest;
import br.com.sinuqueiros.restaurant.controllers.user.responses.CreateUserResponse;
import br.com.sinuqueiros.restaurant.controllers.user.responses.LoginResponse;
import br.com.sinuqueiros.restaurant.services.user.dto.LoginDTO;
import br.com.sinuqueiros.restaurant.services.user.dto.UserDTO;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

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

    public static UserDTO convertFromCreateUserRequestToUserDTO(final CreateUserRequest createUserRequest) {
        return UserDTO.builder()
                .tableNumber(createUserRequest.tableNumber())
                .username(createUserRequest.username())
                .password(new BCryptPasswordEncoder().encode(createUserRequest.password()))
                .build();
    }

    public static CreateUserResponse convertFromUserDTOToCreateUserResponse(final UserDTO userDTO) {
        return new CreateUserResponse(userDTO.getUsername(), userDTO.getTableNumber(), userDTO.getCreatedAt());
    }
}
