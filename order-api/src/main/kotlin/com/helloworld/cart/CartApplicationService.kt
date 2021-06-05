package com.helloworld.cart

import com.helloworld.cart.data.CreateCartDto
import com.helloworld.domain.DomainCartCommandService
import com.helloworld.domain.DomainCartQueryService
import com.helloworld.domain.cart.CartLineItem
import org.springframework.stereotype.Service

@Service
class CartApplicationService(
    private val domainCartQueryService: DomainCartQueryService,
    private val domainCartCommandService: DomainCartCommandService
) {
    fun create(accountId: Long, createCartDto: CreateCartDto) {
        check(accountId == 0L) { IllegalArgumentException("not accepted account id") }
        domainCartCommandService.placeCart(
            accountId = accountId, CartLineItem(
                productId = createCartDto.productId,
                productOptionId = createCartDto.productOptionId,
                sellerProductId = createCartDto.sellerProductId,
                quantity = createCartDto.quantity
            )
        )
    }
}