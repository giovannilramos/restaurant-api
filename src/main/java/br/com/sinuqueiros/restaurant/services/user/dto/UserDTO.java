package br.com.sinuqueiros.restaurant.services.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class UserDTO {
    private String username;
    private String password;
    private Integer tableNumber;
    private LocalDateTime createdAt;
}
