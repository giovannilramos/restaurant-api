package br.com.sinuqueiros.restaurant.services.user;

import br.com.sinuqueiros.restaurant.services.user.dto.UserDTO;
import br.com.sinuqueiros.restaurant.services.user.provider.UserRepositoryProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateUserService {

    private final UserRepositoryProvider userRepositoryProvider;

    public UserDTO createUser(final UserDTO userDTO) {
        final var save = userRepositoryProvider.save(userDTO);
        return UserDTO.builder()
                .username(save.getUsername())
                .tableNumber(save.getTableNumber())
                .createdAt(save.getCreatedAt())
                .build();
    }
}
