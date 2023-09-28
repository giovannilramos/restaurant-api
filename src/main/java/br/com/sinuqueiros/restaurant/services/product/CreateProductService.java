package br.com.sinuqueiros.restaurant.services.product;

import br.com.sinuqueiros.restaurant.services.product.dto.ProductDTO;
import br.com.sinuqueiros.restaurant.services.product.providers.ProductRepositoryProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateProductService {
    private final ProductRepositoryProvider productRepositoryProvider;

    public void createProduct(final ProductDTO productDTO) {
        productRepositoryProvider.save(productDTO);
    }
}
