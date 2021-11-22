package com.example.retailer.api.distributor

import org.hibernate.annotations.GenericGenerator
import javax.persistence.*

/**
 * Описание заказа
 */
@Entity
@Table(name = "orders")
data class Order(
    /**
     * Уникальный идентификатор заказа на стороне ретейлера
     */
    @Id
    @GeneratedValue(generator="uuid2")
    @GenericGenerator(name="uuid2", strategy = "uuid2")
    val id: String?,

    /**
     * Произвольный адрес доставки
     */
    val address: String,

    /**
     * Произвольный получатель доставки
     */
    val recipient: String,

    /**
     * Список заказанных товаров
     */
    @OneToMany(cascade = [CascadeType.ALL])
    val items: List<Item>
)