package com.helloworld.order.service

import com.helloworld.common.domain.data.User
import com.helloworld.order.data.cart.CreateCartDto
import com.helloworld.order.domain.Cart
import com.helloworld.order.domain.CartLineItem
import com.helloworld.order.domain.dto.CartDto
import com.helloworld.order.domain.mapper.CartMapStructMapper
import com.helloworld.order.domain.service.DomainCartCommandService
import com.helloworld.order.domain.service.DomainCartQueryService
import com.helloworld.product.domain.service.DomainProductQueryService
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
        val cart = Cart(accountId = user.accountId, cartLineItems = listOf(cartLineItem))
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
