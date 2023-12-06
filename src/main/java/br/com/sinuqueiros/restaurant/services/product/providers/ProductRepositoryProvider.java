package br.com.sinuqueiros.restaurant.services.product.providers;

import br.com.sinuqueiros.restaurant.repositories.ProductRepository;
import br.com.sinuqueiros.restaurant.services.product.converters.ProductServiceConverter;
import br.com.sinuqueiros.restaurant.services.product.dto.ProductDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static br.com.sinuqueiros.restaurant.services.product.converters.ProductServiceConverter.convertProductDTOToProductEntity;
import static br.com.sinuqueiros.restaurant.services.product.converters.ProductServiceConverter.convertProductEntityListToProductDTOList;
import static br.com.sinuqueiros.restaurant.services.product.converters.ProductServiceConverter.convertProductEntityToProductDTO;

@Component
@RequiredArgsConstructor
public class ProductRepositoryProvider {
    private final ProductRepository productRepository;

    public Optional<ProductDTO> findById(final Long id) {
        final var productEntityOptional = productRepository.findById(id);
        return productEntityOptional.map(ProductServiceConverter::convertProductEntityToProductDTO);
    }

    public ProductDTO save(final ProductDTO productDTO) {
        final var productEntity = convertProductDTOToProductEntity(productDTO);
        return convertProductEntityToProductDTO(productRepository.save(productEntity));
    }

    public Page<ProductDTO> findAll(final Pageable pageable) {
        final var productEntityList = productRepository.findAll(pageable);
        return convertProductEntityListToProductDTOList(productEntityList);
    }


    public Boolean existsById(final Long id) {
        return productRepository.existsById(id);
    }
}
