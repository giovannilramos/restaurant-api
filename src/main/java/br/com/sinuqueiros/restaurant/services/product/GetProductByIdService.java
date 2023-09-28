package br.com.sinuqueiros.restaurant.services.product;

import br.com.sinuqueiros.restaurant.exceptions.NotFoundException;
import br.com.sinuqueiros.restaurant.services.product.dto.ProductDTO;
import br.com.sinuqueiros.restaurant.services.product.providers.ProductRepositoryProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetProductByIdService {
    private final ProductRepositoryProvider productRepositoryProvider;

    public ProductDTO getById(final Long id) {
        return productRepositoryProvider.findById(id).orElseThrow(() -> new NotFoundException("Product not found"));
    }
}
