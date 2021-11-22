package com.example.retailer.adapter

import com.example.retailer.api.distributor.OrderInfo
import com.example.retailer.service.OrderService
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component("receiverConsumer")
class ReceiverConsumerImpl : ReceiverConsumer {
    @Autowired
    lateinit var orderService: OrderService

    @RabbitListener(queues = ["queue"])
    override fun receiveOrder(orderInfo: String) {
        orderService.updateOrderInfo(ObjectMapper().registerModule(KotlinModule()).readValue(orderInfo, OrderInfo::class.java))
    }
}