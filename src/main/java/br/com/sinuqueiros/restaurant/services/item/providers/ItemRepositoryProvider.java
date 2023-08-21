package br.com.sinuqueiros.restaurant.services.item.providers;

import br.com.sinuqueiros.restaurant.repositories.ItemRepository;
import br.com.sinuqueiros.restaurant.services.item.converters.ItemServiceConverter;
import br.com.sinuqueiros.restaurant.services.item.dto.ItemDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ItemRepositoryProvider {
    private final ItemRepository itemRepository;

    public List<ItemDTO> save(final List<ItemDTO> itemDTOList) {
        var itemEntityList = itemDTOList.stream().map(ItemServiceConverter::convertItemDTOToItemEntity).toList();
        itemEntityList = this.itemRepository.saveAll(itemEntityList);
        return itemEntityList.stream().map(ItemServiceConverter::convertItemEntityToItemDTO).toList();

    }

    public Optional<ItemDTO> findById(final Long id) {
        final var itemEntity = itemRepository.findById(id);
        return itemEntity.map(ItemServiceConverter::convertItemEntityToItemDTO);
    }
}
