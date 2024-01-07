package br.com.sinuqueiros.restaurant.services.item;

import br.com.sinuqueiros.restaurant.services.item.dto.ItemDTO;
import br.com.sinuqueiros.restaurant.services.item.providers.ItemRepositoryProvider;
import br.com.sinuqueiros.restaurant.services.product.GetProductByIdService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CreateItemService {
    private final ItemRepositoryProvider itemRepositoryProvider;
    private final GetProductByIdService getProductByIdService;

    public List<ItemDTO> save(final List<ItemDTO> itemDTOList) {
        itemDTOList.forEach(itemDTO -> {
            final var productDTO = getProductByIdService.getById(itemDTO.getProduct().getId());
            final var subTotal = productDTO.getPrice().multiply(BigDecimal.valueOf(itemDTO.getQuantity()));
            itemDTO.setSubTotal(subTotal);
            itemDTO.setProduct(productDTO);
        });
        return itemRepositoryProvider.save(itemDTOList);
    }
}
