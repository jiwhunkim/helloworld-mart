package com.helloworld.order.domain.service

import com.helloworld.order.domain.entity.OrderEntity
import com.helloworld.order.domain.entity.OrderLineItem
import com.helloworld.order.domain.repository.OrderRepository
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
