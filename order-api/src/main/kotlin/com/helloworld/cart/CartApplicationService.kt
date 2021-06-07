package com.helloworld.cart

import com.helloworld.cart.data.CreateCartDto
import com.helloworld.domain.cart.*
import com.helloworld.domain.product.DomainProductQueryService
import org.springframework.stereotype.Service

@Service
class CartApplicationService(
    private val cartProductConverter: CartProductConverter,
    private val cartProductOptionConverter: CartProductOptionConverter,
    private val cartSellerProductConverter: CartSellerProductConverter,
    private val domainCartQueryService: DomainCartQueryService,
    private val domainCartCommandService: DomainCartCommandService,
    private val domainProductQueryService: DomainProductQueryService
) {
    fun create(accountId: Long, createCartDto: CreateCartDto) {
        check(accountId != 0L) { IllegalArgumentException("not accepted account id") }
        domainCartQueryService.queryByAccountId(accountId).ifPresent { throw RuntimeException("already exist cart") }

        val product = domainProductQueryService.findProductById(createCartDto.productId)
        val productOption = domainProductQueryService.findProductOptionById(createCartDto.productOptionId)
        val sellerProduct = productOption.sellerProduct
        val cartLineItem = CartLineItem(
            id = null,
            cartProduct = cartProductConverter.convert(product),
            cartProductOption = cartProductOptionConverter.convert(productOption),
            cartSellerProduct = cartSellerProductConverter.convert(sellerProduct),
            quantity = createCartDto.quantity
        )
        domainCartCommandService.placeCart(accountId = accountId, cartLineItem = cartLineItem)
    }
}