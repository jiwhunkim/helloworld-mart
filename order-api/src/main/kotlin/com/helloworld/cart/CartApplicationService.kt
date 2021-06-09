package com.helloworld.cart

import com.helloworld.cart.data.CreateCartDto
import com.helloworld.data.cart.CartDto
import com.helloworld.data.cart.mapper.CartMapStructMapper
import com.helloworld.domain.cart.*
import com.helloworld.domain.product.DomainProductQueryService
import org.springframework.stereotype.Service
import java.lang.RuntimeException

@Service
class CartApplicationService(
    private val cartMapStructMapper: CartMapStructMapper,
    private val cartProductConverter: CartProductConverter,
    private val cartProductOptionConverter: CartProductOptionConverter,
    private val cartSellerProductConverter: CartSellerProductConverter,
    private val domainCartQueryService: DomainCartQueryService,
    private val domainCartCommandService: DomainCartCommandService,
    private val domainProductQueryService: DomainProductQueryService
) {
    fun create(accountId: Long, createCartDto: CreateCartDto): CartDto {
        require(accountId != 0L) { "not accepted account id" }

        val cart = domainCartQueryService.queryByAccountId(accountId)
        require(cart.isEmpty) { "already exist cart" }

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
        val result = domainCartCommandService.placeCart(accountId = accountId, cartLineItem = cartLineItem)
        return cartMapStructMapper.convert(result)!!
    }
}