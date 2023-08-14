package br.com.sinuqueiros.restaurant.config.rabbitmq;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    @Value("${rabbitmq-config.exchange}")
    private String topicExchange;

    @Value("${rabbitmq-config.queue}")
    private String queueName;

    @Value("${rabbitmq-config.routing-key}")
    private String routingKey;

    @Bean
    public Queue queue() {
        return new Queue(queueName, false);
    }

    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(topicExchange);
    }

    @Bean
    public Binding binding(final Queue queue, final TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(routingKey);
    }

    @Bean
    public MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public AmqpTemplate amqpTemplate(final ConnectionFactory connectionFactory) {
        final var rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter());
        return rabbitTemplate;
    }
}
