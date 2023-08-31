package br.com.sinuqueiros.restaurant.config.websocket.handler;

import br.com.sinuqueiros.restaurant.controllers.order.responses.OrderResponse;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class WebSocketOrderController {
    @MessageMapping("/message")
    @SendTo("/topic/order")
    public List<OrderResponse> websocketOrderController(@Payload List<OrderResponse> orderResponseList) {
        return orderResponseList;
    }
}
