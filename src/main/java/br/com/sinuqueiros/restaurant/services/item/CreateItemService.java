package br.com.sinuqueiros.restaurant.services.item;

import br.com.sinuqueiros.restaurant.services.item.dto.ItemDTO;
import br.com.sinuqueiros.restaurant.services.item.providers.ItemRepositoryProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CreateItemService {
    private final ItemRepositoryProvider itemRepositoryProvider;

    public List<ItemDTO> save(final List<ItemDTO> itemDTOList) {
        return itemRepositoryProvider.save(itemDTOList);
    }
}
