package br.com.sinuqueiros.restaurant.services.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder(toBuilder = true)
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class JwtPayloadDTO {
    private String sub;
    private String iss;
    private Integer tableNumber;
    private String roles;
    private String exp;
}
