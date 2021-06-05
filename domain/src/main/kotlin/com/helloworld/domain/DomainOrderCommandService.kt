package com.helloworld.domain

import com.helloworld.domain.order.OrderLineItem
import org.springframework.stereotype.Service

@Service
class DomainOrderCommandService(
    val orderPlaceService: OrderPlaceService,
    val productSaleService: ProductSaleService
) {

    fun place(accountId: Long, items: List<OrderLineItem>) {
        items.forEach { productSaleService.sale(it.sellerProduct.id, it.quantity) }
        orderPlaceService.place(accountId = accountId, orderLineItems = items)
    }
}