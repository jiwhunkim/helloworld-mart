package com.helloworld.cart.service

import com.helloworld.cart.data.CreateCartDto
import com.helloworld.data.cart.CartDto
import com.helloworld.data.cart.mapper.CartMapStructMapper
import com.helloworld.domain.cart.Cart
import com.helloworld.domain.cart.CartLineItem
import com.helloworld.domain.cart.DomainCartCommandService
import com.helloworld.domain.cart.DomainCartQueryService
import com.helloworld.domain.common.data.User
import com.helloworld.domain.product.DomainProductQueryService
import org.springframework.stereotype.Service

@Service
class CartApplicationService(
    private val cartMapStructMapper: CartMapStructMapper,
    private val domainCartQueryService: DomainCartQueryService,
    private val domainCartCommandService: DomainCartCommandService,
    private val domainProductQueryService: DomainProductQueryService
) {
    fun create(user: User, createCartDto: CreateCartDto): CartDto {
        require(user.isMember) { "not accepted account id" }

        val saved = domainCartQueryService.queryByAccountId(user.accountId)
        require(saved.isEmpty) { "already exist cart" }

        val cartLineItem = getCartLineItem(createCartDto)
        val cart = Cart(accountId = user.accountId, listOf(cartLineItem))
        val result = domainCartCommandService.save(cart)
        return cartMapStructMapper.convert(result)!!
    }

    fun update(accountId: Long, cartId: String, createCartDto: CreateCartDto): CartDto {
        require(accountId != 0L) { "not accepted account id" }

        val cart = domainCartQueryService.findByCartId(cartId)

        val cartLineItem = getCartLineItem(createCartDto)
        cart.addCartLineItem(cartLineItem)
        val result = domainCartCommandService.save(cart)
        return cartMapStructMapper.convert(result)!!
    }

    private fun getCartLineItem(createCartDto: CreateCartDto): CartLineItem {
        val product = domainProductQueryService.findProductById(createCartDto.productId)
        val productOption = domainProductQueryService.findProductOptionById(createCartDto.productOptionId)
        val sellerProduct = productOption.sellerProduct

        return domainCartCommandService.createCartLineItem(
            product = product,
            productOption = productOption,
            sellerProduct = sellerProduct,
            quantity = createCartDto.quantity
        )
    }
}