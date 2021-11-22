package com.example.retailer.storage

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import com.example.retailer.api.distributor.Order

@Repository
interface OrderRepository : CrudRepository<Order, String> {
}