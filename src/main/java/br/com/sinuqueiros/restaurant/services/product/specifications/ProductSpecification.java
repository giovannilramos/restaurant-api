package br.com.sinuqueiros.restaurant.services.product.specifications;

import br.com.sinuqueiros.restaurant.entities.ProductEntity;
import br.com.sinuqueiros.restaurant.services.product.dto.ProductDTO;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

import static java.util.Objects.nonNull;

@Component
public class ProductSpecification {
    public Specification<ProductEntity> toPredicate(final ProductDTO productDTO) {
        return (root, query, cb) -> {
            final var predicates = new ArrayList<Predicate>();

            if (nonNull(productDTO.getCategory())) {
                predicates.add(cb.equal(root.get("category"), productDTO.getCategory()));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
