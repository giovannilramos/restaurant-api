package br.com.sinuqueiros.restaurant.services.item;

import br.com.sinuqueiros.restaurant.exceptions.NotFoundException;
import br.com.sinuqueiros.restaurant.services.item.dto.ItemDTO;
import br.com.sinuqueiros.restaurant.services.item.providers.ItemRepositoryProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetItemService {
    private final ItemRepositoryProvider itemRepositoryProvider;

    public ItemDTO findById(final Long id) {
        return itemRepositoryProvider.findById(id).orElseThrow(() -> new NotFoundException("Item not found"));
    }
}
