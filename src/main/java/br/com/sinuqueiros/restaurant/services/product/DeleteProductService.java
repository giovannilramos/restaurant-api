package br.com.sinuqueiros.restaurant.services.product;

import br.com.sinuqueiros.restaurant.services.product.dto.ProductDTO;
import br.com.sinuqueiros.restaurant.services.product.providers.ProductRepositoryProvider;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteProductService {
    private final ProductRepositoryProvider productRepositoryProvider;
    private final GetProductByIdService getProductByIdService;

    @Transactional
    public ProductDTO update(final Long id) {
        final var entity = getProductByIdService.getById(id);

        entity.setStatus(!entity.getStatus());

        productRepositoryProvider.save(entity);

        return entity;
    }
}
