package br.com.sinuqueiros.restaurant.services.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class LoginDTO {
    private String username;
    private String password;
    private String token;
    private Long expiresIn;
}
