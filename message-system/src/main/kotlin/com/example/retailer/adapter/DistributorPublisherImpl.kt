package com.example.retailer.adapter

import com.example.retailer.api.distributor.Order
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import org.springframework.amqp.core.TopicExchange
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component("distributorPublisher")
class DistributorPublisherImpl : DistributorPublisher {
    @Autowired
    lateinit var rabbitTemplate: RabbitTemplate

    @Autowired
    lateinit var topicExchange: TopicExchange

    override fun placeOrder(order: Order): Boolean {
        val message = ObjectMapper().registerModule(KotlinModule()).writeValueAsString(order)
        var result = false

        if (order.id != null ) {
            rabbitTemplate.convertAndSend(topicExchange.name, "distributor.placeOrder.v-yakupov.${order.id}", message) { msg ->
                msg.messageProperties.headers["Notify-Exchange"] = "distributor_exchange"
                msg.messageProperties.headers["Notify-RoutingKey"] = "retailer.v-yakupov"
                msg
            }

            result = true
        }

        return result
    }
}