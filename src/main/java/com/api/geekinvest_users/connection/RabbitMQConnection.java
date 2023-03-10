package com.api.geekinvest_users.connection;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.stereotype.Component;

import com.api.geekinvest_users.constant.RabbitMQConstant;

import jakarta.annotation.PostConstruct;

@Component
public class RabbitMQConnection {
    private static final String NAME_EXCHANGE = "amq.direct";
    private AmqpAdmin amqpAdmin;

    public RabbitMQConnection(AmqpAdmin amqpAdmin) {
        this.amqpAdmin = amqpAdmin;
    }

    private Queue queue(String queueName) {
        return new Queue(queueName, true, false, false);
    }

    private DirectExchange userExchange() {
        return new DirectExchange(NAME_EXCHANGE);
    }

    private Binding exchangeQueue(Queue queue, DirectExchange exchange) {
        return new Binding(queue.getName(), Binding.DestinationType.QUEUE, exchange.getName(), queue.getName(),
                null);
    }

    @PostConstruct
    private void addQueue() {
        Queue queueCountrySave = this.queue(RabbitMQConstant.QUEUE_COUNTRY_SAVE);
        Queue queueCountryDelete = this.queue(RabbitMQConstant.QUEUE_COUNTRY_DELETE);

        DirectExchange exchange = this.userExchange();

        Binding relationCountrySave = this.exchangeQueue(queueCountrySave, exchange);
        Binding relationCountryDelete = this.exchangeQueue(queueCountryDelete, exchange);

        this.amqpAdmin.declareQueue(queueCountrySave);
        this.amqpAdmin.declareQueue(queueCountryDelete);

        this.amqpAdmin.declareExchange(exchange);

        this.amqpAdmin.declareBinding(relationCountrySave);
        this.amqpAdmin.declareBinding(relationCountryDelete);

    }
}
