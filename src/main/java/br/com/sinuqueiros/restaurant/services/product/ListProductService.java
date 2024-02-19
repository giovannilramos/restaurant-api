package br.com.sinuqueiros.restaurant.services.product;

import br.com.sinuqueiros.restaurant.services.product.dto.ProductDTO;
import br.com.sinuqueiros.restaurant.services.product.providers.ProductRepositoryProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ListProductService {
    private final ProductRepositoryProvider productRepositoryProvider;

    public Page<ProductDTO> listProduct(final Pageable pageable, final ProductDTO productDTO) {
        return productRepositoryProvider.findAll(pageable, productDTO);
    }
}
