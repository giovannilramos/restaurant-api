package br.com.sinuqueiros.restaurant.controllers.product.converters;

import br.com.sinuqueiros.restaurant.controllers.product.responses.ProductResponse;
import br.com.sinuqueiros.restaurant.services.product.dto.ProductDTO;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ProductControllerConverter {

    public static ProductResponse convertFromProductDTOToProductResponse(final ProductDTO productDTO) {
        return new ProductResponse(productDTO.getId(), productDTO.getDescription(), productDTO.getImage(), productDTO.getName(), productDTO.getPrice(), productDTO.getStatus());
    }
}
