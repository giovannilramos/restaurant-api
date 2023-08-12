package br.com.sinuqueiros.restaurant.services.order;

import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CreateOrderService {
    private final SimpMessagingTemplate simpMessagingTemplate;
    public void createOrder() {
        simpMessagingTemplate.convertAndSend("/topic/list-updates", Optional.empty());
    }
}



