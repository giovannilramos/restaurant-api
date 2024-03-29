package br.com.sinuqueiros.restaurant.services.product.dto;

import br.com.sinuqueiros.restaurant.enums.CategoryEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder(toBuilder = true)
public class ProductDTO {
    private Long id;
    private String name;
    private String image;
    private String description;
    private CategoryEnum category;
    @Setter
    private Boolean status;
    private BigDecimal price;
}
