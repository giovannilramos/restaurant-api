package br.com.sinuqueiros.restaurant.services.product.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class ProductDTO {
    private Long id;
    private String name;
    private String image;
    private String description;
    private Boolean status;
    private BigDecimal price;
}
