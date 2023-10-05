package br.com.sinuqueiros.restaurant.controllers.product;

import br.com.sinuqueiros.restaurant.controllers.product.requests.ProductRequest;
import br.com.sinuqueiros.restaurant.controllers.product.responses.ProductResponse;
import br.com.sinuqueiros.restaurant.services.product.CreateProductService;
import br.com.sinuqueiros.restaurant.services.product.DeleteProductService;
import br.com.sinuqueiros.restaurant.services.product.GetProductByIdService;
import br.com.sinuqueiros.restaurant.services.product.ListProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static br.com.sinuqueiros.restaurant.controllers.product.converters.ProductControllerConverter.*;
import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/product")
public class ProductController {
    private final CreateProductService createProductService;
    private final ListProductService listProductService;
    private final GetProductByIdService getProductByIdService;
    private final DeleteProductService deleteProductService;

    @PostMapping
    public ResponseEntity<Void> createProduct(@RequestBody @Valid final ProductRequest productRequest) {
        createProductService.createProduct(convertFromProductRequestToProductDTO(productRequest));
        return ResponseEntity.status(CREATED).build();
    }

    @GetMapping
    public ResponseEntity<Page<ProductResponse>> listProduct(@PageableDefault(sort = "name", direction = Sort.Direction.DESC) final Pageable pageable) {
        final var productDtoList = listProductService.listProduct(pageable);
        return ResponseEntity.ok(convertFromProductDTOListToProductResponseList(productDtoList));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getProductById(@PathVariable(name = "id") final Long id) {
        final var productDto = getProductByIdService.getById(id);
        return ResponseEntity.ok(convertFromProductDTOToProductResponse(productDto));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ProductResponse> deleteProductById(@PathVariable(name = "id") final Long id) {
        final var productDto = deleteProductService.update(id);
        return ResponseEntity.ok(convertFromProductDTOToProductResponse(productDto));
    }
}
