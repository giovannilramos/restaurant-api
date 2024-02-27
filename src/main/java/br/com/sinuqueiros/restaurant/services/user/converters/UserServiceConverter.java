package br.com.sinuqueiros.restaurant.services.user.converters;

import br.com.sinuqueiros.restaurant.entities.RolesEntity;
import br.com.sinuqueiros.restaurant.entities.UserEntity;
import br.com.sinuqueiros.restaurant.services.user.dto.UserDTO;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Set;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserServiceConverter {

    public static UserEntity convertUserDTOToUserEntity(final UserDTO userDTO, final Set<RolesEntity> rolesEntityHashSet) {
        return UserEntity.builder()
                .username(userDTO.getUsername())
                .password(userDTO.getPassword())
                .tableNumber(userDTO.getTableNumber())
                .roles(rolesEntityHashSet)
                .build();
    }

    public static UserDTO convertUserEntityToUserDTO(final UserEntity userEntity) {
        return UserDTO.builder()
                .username(userEntity.getUsername())
                .password(userEntity.getPassword())
                .createdAt(userEntity.getCreatedAt())
                .tableNumber(userEntity.getTableNumber())
                .build();
    }
}
