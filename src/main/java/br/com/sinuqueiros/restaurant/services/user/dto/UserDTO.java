package br.com.sinuqueiros.restaurant.services.user.dto;

import br.com.sinuqueiros.restaurant.entities.RolesEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class UserDTO {
    private String username;
    private String password;
    private Integer tableNumber;
    @Setter
    private Set<RolesEntity> roles;
    private LocalDateTime createdAt;
}
