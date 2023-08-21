package br.com.sinuqueiros.restaurant.services.product.providers;

import br.com.sinuqueiros.restaurant.repositories.ProductRepository;
import br.com.sinuqueiros.restaurant.services.product.converters.ProductServiceConverter;
import br.com.sinuqueiros.restaurant.services.product.dto.ProductDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ProductRepositoryProvider {
    private final ProductRepository productRepository;

    public Optional<ProductDTO> findById(final Long id) {
        final var productEntityOptional = productRepository.findById(id);
        return productEntityOptional.map(ProductServiceConverter::convertProductEntityToProductDTO);
    }

    public Boolean existsById(final Long id) {
        return productRepository.existsById(id);
    }
}
