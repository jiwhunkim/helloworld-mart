package com.helloworld.domain

import com.helloworld.domain.order.OrderEntity
import com.helloworld.domain.order.OrderLineItem
import com.helloworld.domain.order.OrderRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class OrderPlaceService(
    val orderRepository: OrderRepository
) {

    @Transactional
    fun place(accountId: Long, orderLineItems: List<OrderLineItem>) {
        val order = OrderEntity(accountId, orderLineItems)
        orderRepository.save(order)
    }
}