package br.com.sinuqueiros.restaurant.controllers.product;

import br.com.sinuqueiros.restaurant.controllers.product.requests.ProductRequest;
import br.com.sinuqueiros.restaurant.controllers.product.requests.UpdateProductRequest;
import br.com.sinuqueiros.restaurant.controllers.product.responses.ProductResponse;
import br.com.sinuqueiros.restaurant.enums.CategoryEnum;
import br.com.sinuqueiros.restaurant.services.product.CreateProductService;
import br.com.sinuqueiros.restaurant.services.product.DeleteProductService;
import br.com.sinuqueiros.restaurant.services.product.GetProductByIdService;
import br.com.sinuqueiros.restaurant.services.product.ListProductService;
import br.com.sinuqueiros.restaurant.services.product.UpdateProductService;
import br.com.sinuqueiros.restaurant.services.product.dto.ProductDTO;
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
    private final UpdateProductService updateProductService;

    @PostMapping
    public ResponseEntity<Void> createProduct(@RequestBody @Valid final ProductRequest productRequest) {
        createProductService.createProduct(convertFromProductRequestToProductDTO(productRequest));
        return ResponseEntity.status(CREATED).build();
    }

    @GetMapping
    public ResponseEntity<Page<ProductResponse>> listProduct(
            @PageableDefault(sort = "category", direction = Sort.Direction.ASC) final Pageable pageable,
            @RequestParam(name = "category", required = false) final CategoryEnum category
    ) {
        final var productDTO = ProductDTO.builder().category(category).build();
        final var productDtoList = listProductService.listProduct(pageable, productDTO);
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

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponse> updateProductById(@PathVariable(name = "id") final Long id, @RequestBody final UpdateProductRequest updateProductRequest) {
        final var productDto = updateProductService.update(id, convertFromUpdateProductRequestToProductDTO(updateProductRequest));
        return ResponseEntity.ok(convertFromProductDTOToProductResponse(productDto));
    }
}
