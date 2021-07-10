package com.helloworld.order.domain

import com.helloworld.order.domain.entity.OrderEntity
import com.helloworld.order.domain.entity.OrderLineItem

object OrderEntityFixture {
    fun of(
        accountId: Long = 1L,
        orderLineItems: MutableList<OrderLineItem> = mutableListOf()
    ) = OrderEntity(accountId = accountId, orderLineItems = orderLineItems)
}
