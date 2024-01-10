package com.ensaj.configs;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MQConfig {

    // Existing queue and exchange definitions
    public static final String QUEUE = "2ite_micro_message_queue";
    public static final String EXCHANGE = "2ite_micro_message_exchange";
    public static final String ROUTING_KEY = "message_routingKey";

    // New queue and exchange definitions
    public static final String QUEUEREQUEST = "Reverse2ite_micro_message_queue";
    public static final String EXCHANGEREQUEST = "Reverse2ite_micro_message_exchange";
    public static final String ROUTING_KEY_REQUEST = "request_routingKey";

    // Existing beans for the original queue and exchange
    @Bean
    public Queue queue() {
        return new Queue(QUEUE);
    }

    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(EXCHANGE);
    }

    @Bean
    public Binding binding(Queue queue, TopicExchange exchange) {
        return BindingBuilder
                .bind(queue)
                .to(exchange)
                .with(ROUTING_KEY);
    }

    // New beans for the additional queue and exchange
    @Bean
    public Queue queueRequest() {
        return new Queue(QUEUEREQUEST);
    }

    @Bean
    public TopicExchange exchangeRequest() {
        return new TopicExchange(EXCHANGEREQUEST);
    }

    @Bean
    public Binding bindingRequest(Queue queueRequest, TopicExchange exchangeRequest) {
        return BindingBuilder
                .bind(queueRequest)
                .to(exchangeRequest)
                .with(ROUTING_KEY_REQUEST);
    }

    // Common beans for message conversion and template
    @Bean
    public MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public AmqpTemplate template(ConnectionFactory connectionFactory) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(messageConverter());
        return template;
    }
}
