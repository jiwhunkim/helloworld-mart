package com.helloworld.order.domain.service

import com.helloworld.order.domain.OrderLineItem
import com.helloworld.seller.domain.service.SellerProductSaleService
import org.springframework.stereotype.Service

@Service
class DomainOrderCommandService(
    val orderPlaceService: OrderPlaceService,
    val sellerProductSaleService: SellerProductSaleService
) {

    fun place(accountId: Long, items: List<OrderLineItem>) {
        items.forEach { sellerProductSaleService.sale(it.sellerProduct.id, it.quantity) }
        orderPlaceService.place(accountId = accountId, orderLineItems = items)
    }
}
