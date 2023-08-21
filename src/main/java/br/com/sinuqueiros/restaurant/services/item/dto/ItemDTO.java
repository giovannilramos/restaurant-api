package br.com.sinuqueiros.restaurant.services.item.dto;

import br.com.sinuqueiros.restaurant.services.product.dto.ProductDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class ItemDTO {
    private Long id;
    private Integer quantity;
    @Setter
    private BigDecimal subTotal;
    private ProductDTO product;
}
