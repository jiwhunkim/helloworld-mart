package com.helloworld.domain.product

import com.helloworld.domain.order.OrderEntity
import com.helloworld.domain.order.OrderLineItem

object OrderEntityFixture {
    fun of(
        accountId: Long = 1L,
        orderLineItems: MutableList<OrderLineItem> = mutableListOf()
    ) = OrderEntity(accountId = accountId, orderLineItems = orderLineItems)
}