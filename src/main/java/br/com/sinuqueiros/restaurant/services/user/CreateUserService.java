package br.com.sinuqueiros.restaurant.services.user;

import br.com.sinuqueiros.restaurant.services.user.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateUserService {

    public UserDTO createUser(final UserDTO userDTO) {
        return UserDTO.builder().build();
    }
}
