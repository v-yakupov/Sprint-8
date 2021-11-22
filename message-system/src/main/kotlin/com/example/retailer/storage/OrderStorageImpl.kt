package com.example.retailer.storage

import com.example.retailer.api.distributor.Order
import com.example.retailer.api.distributor.OrderInfo
import com.example.retailer.api.distributor.OrderStatus
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component

@Component("orderStorage")
class OrderStorageImpl : OrderStorage {
    @Autowired
    lateinit var orderRepository: OrderRepository

    @Autowired
    lateinit var orderInfoRepository: OrderInfoRepository

    override fun createOrder(draftOrder: Order): PlaceOrderData {
        val order = orderRepository.save(draftOrder)
        val orderInfo = orderInfoRepository.save(OrderInfo(order.id!!, OrderStatus.SENT, toString()))
        return PlaceOrderData(order, orderInfo)
    }

    override fun updateOrder(order: OrderInfo): Boolean {
        var result = false

        if (orderRepository.existsById(order.orderId)) {
            orderInfoRepository.save(order)
            result = true
        }

        return result
    }

    override fun getOrderInfo(id: String): OrderInfo? {
        return orderInfoRepository.findByIdOrNull(id)
    }
}