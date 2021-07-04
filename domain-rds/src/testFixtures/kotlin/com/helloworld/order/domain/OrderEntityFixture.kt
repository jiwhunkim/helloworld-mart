package com.helloworld.order.domain

object OrderEntityFixture {
    fun of(
        accountId: Long = 1L,
        orderLineItems: MutableList<OrderLineItem> = mutableListOf()
    ) = OrderEntity(accountId = accountId, orderLineItems = orderLineItems)
}
