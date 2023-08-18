package br.com.sinuqueiros.restaurant.services.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class LoginDTO {
    private String username;
    private String password;
    @Setter
    private String token;
}
