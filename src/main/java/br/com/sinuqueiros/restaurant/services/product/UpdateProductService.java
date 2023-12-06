package br.com.sinuqueiros.restaurant.services.product;

import br.com.sinuqueiros.restaurant.exceptions.NotFoundException;
import br.com.sinuqueiros.restaurant.services.product.dto.ProductDTO;
import br.com.sinuqueiros.restaurant.services.product.providers.ProductRepositoryProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdateProductService {

    private final ProductRepositoryProvider productRepositoryProvider;

    public ProductDTO update(final Long id, final ProductDTO productDTO) {
        final var productDTOById = productRepositoryProvider.findById(id).orElseThrow(() -> new NotFoundException("Product not found"));
        return productRepositoryProvider.save(buildProductDTO(productDTO, productDTOById));
    }

    private ProductDTO buildProductDTO(final ProductDTO productDTO, final ProductDTO productDTOById) {
        return productDTOById.toBuilder()
                .name(productDTO.getName())
                .image(productDTO.getImage())
                .description(productDTO.getDescription())
                .price(productDTO.getPrice())
                .build();
    }
}
