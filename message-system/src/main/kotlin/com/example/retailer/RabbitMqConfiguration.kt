package com.example.retailer

import org.springframework.amqp.core.Binding
import org.springframework.amqp.core.BindingBuilder
import org.springframework.amqp.core.Queue
import org.springframework.amqp.core.TopicExchange
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class RabbitMqConfiguration {
    @Bean
    fun queue(): Queue {
        return Queue("queue")
    }

    @Bean
    fun exchange(): TopicExchange {
        return TopicExchange("distributor_exchange")
    }

    @Bean
    fun binding(queue: Queue, exchange: TopicExchange): Binding? {
        return BindingBuilder.bind(queue).to(exchange).with("retailer.v-yakupov.#")
    }
}