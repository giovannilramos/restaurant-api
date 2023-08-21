package br.com.sinuqueiros.restaurant.services.product.converters;

import br.com.sinuqueiros.restaurant.entities.ProductEntity;
import br.com.sinuqueiros.restaurant.services.product.dto.ProductDTO;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ProductServiceConverter {
    public static ProductEntity convertProductDTOToProductEntity(final ProductDTO productDTO) {
        return ProductEntity.builder()
                .id(productDTO.getId())
                .name(productDTO.getName())
                .image(productDTO.getImage())
                .description(productDTO.getDescription())
                .status(productDTO.getStatus())
                .price(productDTO.getPrice())
                .build();
    }

    public static ProductDTO convertProductEntityToProductDTO(final ProductEntity productEntity) {
        return ProductDTO.builder()
                .id(productEntity.getId())
                .name(productEntity.getName())
                .image(productEntity.getImage())
                .description(productEntity.getDescription())
                .status(productEntity.getStatus())
                .price(productEntity.getPrice())
                .build();
    }
}
