package com.helloworld.order.domain.service

import com.helloworld.order.domain.OrderEntity
import com.helloworld.order.domain.OrderLineItem
import com.helloworld.order.domain.OrderRepository
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
