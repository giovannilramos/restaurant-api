package br.com.sinuqueiros.restaurant.controllers.product.converters;

import br.com.sinuqueiros.restaurant.controllers.product.requests.ProductRequest;
import br.com.sinuqueiros.restaurant.controllers.product.requests.UpdateProductRequest;
import br.com.sinuqueiros.restaurant.controllers.product.responses.ProductResponse;
import br.com.sinuqueiros.restaurant.services.product.dto.ProductDTO;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ProductControllerConverter {

    public static ProductDTO convertFromProductRequestToProductDTO(final ProductRequest productRequest) {
        return ProductDTO.builder()
                .name(productRequest.name())
                .image(productRequest.image())
                .description(productRequest.description())
                .status(true)
                .price(productRequest.price())
                .build();
    }

    public static ProductResponse convertFromProductDTOToProductResponse(final ProductDTO productDTO) {
        return new ProductResponse(productDTO.getId(), productDTO.getDescription(), productDTO.getImage(), productDTO.getName(), productDTO.getPrice(), productDTO.getStatus());
    }

    public static Page<ProductResponse> convertFromProductDTOListToProductResponseList(final Page<ProductDTO> productDTOList) {
        return productDTOList.map(ProductControllerConverter::convertFromProductDTOToProductResponse);
    }

    public static ProductDTO convertFromUpdateProductRequestToProductDTO(final UpdateProductRequest updateProductRequest) {
        return ProductDTO.builder()
                .name(updateProductRequest.name())
                .image(updateProductRequest.image())
                .description(updateProductRequest.description())
                .price(updateProductRequest.price())
                .build();
    }
}
