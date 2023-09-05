package br.com.sinuqueiros.restaurant.services.order;

import br.com.sinuqueiros.restaurant.services.order.dto.OrderDTO;

public interface SendToWebsocket {
    void sendUpdatedListToWebsocket(final OrderDTO orderDTO);
}
