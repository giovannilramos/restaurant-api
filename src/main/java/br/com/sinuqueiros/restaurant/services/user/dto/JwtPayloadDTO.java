package br.com.sinuqueiros.restaurant.services.user.dto;

import br.com.sinuqueiros.restaurant.enums.RoleNameEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder(toBuilder = true)
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class JwtPayloadDTO {
    private String sub;
    private String iss;
    private Integer tableNumber;
    private List<RoleNameEnum> roles;
    private String exp;
}
