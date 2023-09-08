package br.com.sinuqueiros.restaurant.services.item;

import br.com.sinuqueiros.restaurant.services.item.dto.ItemDTO;
import br.com.sinuqueiros.restaurant.services.item.providers.ItemRepositoryProvider;
import br.com.sinuqueiros.restaurant.services.product.providers.ProductRepositoryProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CreateItemService {
    private final ItemRepositoryProvider itemRepositoryProvider;
    private final ProductRepositoryProvider productRepositoryProvider;

    public List<ItemDTO> save(final List<ItemDTO> itemDTOList) {
        itemDTOList.forEach(itemDTO -> {
            /*TODO: Call product service findById*/
            final var productDTO = productRepositoryProvider.findById(itemDTO.getProduct().getId()).orElseThrow();
            final var subTotal = productDTO.getPrice().multiply(BigDecimal.valueOf(itemDTO.getQuantity()));
            itemDTO.setSubTotal(subTotal);
            itemDTO.setProduct(productDTO);
        });
        return itemRepositoryProvider.save(itemDTOList);
    }
}
