package br.com.sinuqueiros.restaurant.queue.publishers;

import br.com.sinuqueiros.restaurant.services.order.dto.OrderDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class RabbitMQProducer {
    @Value("${rabbitmq-config.exchange}")
    private String topicExchange;

    @Value("${rabbitmq-config.routing-key}")
    private String routingKey;

    private final RabbitTemplate rabbitTemplate;

    public void sendMessage(final OrderDTO orderDTO) {
       log.info("Sending message: {}", orderDTO.toString());
       rabbitTemplate.convertAndSend(topicExchange, routingKey, orderDTO);
    }
}
