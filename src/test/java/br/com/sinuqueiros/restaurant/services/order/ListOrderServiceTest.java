package br.com.sinuqueiros.restaurant.services.order;

import br.com.sinuqueiros.restaurant.services.order.providers.OrderRepositoryProvider;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static br.com.sinuqueiros.restaurant.mocks.OrderMock.orderDTOMock;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ListOrderServiceTest {
    @Mock
    private OrderRepositoryProvider orderRepositoryProvider;

    @InjectMocks
    private ListOrderService listOrderService;

    @Test
    void shouldListOrders() {
        final var orderDTOMock = orderDTOMock();
        when(orderRepositoryProvider.findAll()).thenReturn(List.of(orderDTOMock));
        final var orderDTOList = listOrderService.listOrder();
        final var orderDTO = orderDTOList.getFirst();
        assertEquals(orderDTOMock.getId(), orderDTO.getId());
    }
}