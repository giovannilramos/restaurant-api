package br.com.sinuqueiros.restaurant.services.order;

import br.com.sinuqueiros.restaurant.exceptions.NotFoundException;
import br.com.sinuqueiros.restaurant.services.item.CreateItemService;
import br.com.sinuqueiros.restaurant.services.order.dto.OrderDTO;
import br.com.sinuqueiros.restaurant.services.order.providers.OrderRepositoryProvider;
import br.com.sinuqueiros.restaurant.services.product.providers.ProductRepositoryProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class CreateOrderService {
    private final OrderRepositoryProvider orderRepositoryProvider;
    private final CreateItemService createItemService;
    private final ProductRepositoryProvider productRepositoryProvider;

    public void createOrder(final OrderDTO orderDTO) {
        final var total = orderDTO.getItems().stream().map(itemDTO -> {
            final var productDTO = productRepositoryProvider.findById(itemDTO.getProduct().getId())
                    .orElseThrow(() -> new NotFoundException("Product not found"));
            final var subTotal = productDTO.getPrice().multiply(BigDecimal.valueOf(itemDTO.getQuantity()));
            itemDTO.setSubTotal(subTotal);
            return subTotal;
        }).reduce(BigDecimal.ZERO, BigDecimal::add);
        orderDTO.setTotal(total);
        final var itemDTOList = createItemService.save(orderDTO.getItems());
        orderDTO.setItems(itemDTOList);
        orderRepositoryProvider.save(orderDTO);
    }
}



