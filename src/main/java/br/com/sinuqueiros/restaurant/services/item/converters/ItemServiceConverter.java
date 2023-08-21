package br.com.sinuqueiros.restaurant.services.item.converters;

import br.com.sinuqueiros.restaurant.entities.ItemEntity;
import br.com.sinuqueiros.restaurant.services.item.dto.ItemDTO;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import static br.com.sinuqueiros.restaurant.services.product.converters.ProductServiceConverter.convertProductDTOToProductEntity;
import static br.com.sinuqueiros.restaurant.services.product.converters.ProductServiceConverter.convertProductEntityToProductDTO;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ItemServiceConverter {
    public static ItemEntity convertItemDTOToItemEntity(final ItemDTO itemDTO) {
        return ItemEntity.builder()
                .id(itemDTO.getId())
                .quantity(itemDTO.getQuantity())
                .subTotal(itemDTO.getSubTotal())
                .product(convertProductDTOToProductEntity(itemDTO.getProduct()))
                .build();
    }

    public static ItemDTO convertItemEntityToItemDTO(final ItemEntity itemEntity) {
        return ItemDTO.builder()
                .id(itemEntity.getId())
                .quantity(itemEntity.getQuantity())
                .subTotal(itemEntity.getSubTotal())
                .product(convertProductEntityToProductDTO(itemEntity.getProduct()))
                .build();
    }
}
