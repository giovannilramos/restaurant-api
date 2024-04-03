package br.com.sinuqueiros.restaurant.services.user.provider;

import br.com.sinuqueiros.restaurant.entities.RolesEntity;
import br.com.sinuqueiros.restaurant.exceptions.NotFoundException;
import br.com.sinuqueiros.restaurant.repositories.RolesRepository;
import br.com.sinuqueiros.restaurant.repositories.UserRepository;
import br.com.sinuqueiros.restaurant.services.user.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashSet;

import static br.com.sinuqueiros.restaurant.services.user.converters.UserServiceConverter.convertUserDTOToUserEntity;
import static br.com.sinuqueiros.restaurant.services.user.converters.UserServiceConverter.convertUserEntityToUserDTO;

@RequiredArgsConstructor
@Component
public class UserRepositoryProvider {

    private final UserRepository userRepository;
    private final RolesRepository rolesRepository;

    public UserDTO save(final UserDTO userDTO) {
        final var rolesEntity = rolesRepository.findByRoleName(userDTO.getRoles())
                .orElseThrow(() -> new NotFoundException("Role not found"));
        final var rolesEntityHashSet = new HashSet<RolesEntity>();
        rolesEntityHashSet.add(rolesEntity);
        final var userEntity = userRepository.save(convertUserDTOToUserEntity(userDTO, rolesEntityHashSet));

        return convertUserEntityToUserDTO(userEntity);
    }
}
