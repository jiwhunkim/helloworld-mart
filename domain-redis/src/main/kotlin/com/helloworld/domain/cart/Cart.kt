package com.helloworld.domain.cart

import org.apache.commons.lang3.RandomStringUtils
import org.springframework.data.annotation.Id
import org.springframework.data.redis.core.RedisHash
import org.springframework.data.redis.core.index.Indexed
import java.io.Serializable
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*


@RedisHash(timeToLive = (60 * 60 * 24) as Long) // 24 hour
open class Cart(
    accountId: Long,
    cartLineItems: List<CartLineItem> = emptyList()
) : Serializable {
    @Id
    var id: String = getId(accountId)
        protected set

    @Indexed
    var accountId: Long = accountId
        protected set

    var cartLineItems: MutableList<CartLineItem> = mutableListOf()
        protected set

    val salesAmount get() = cartLineItems.sumOf { it.salesAmount }
    val discountAmount get() = cartLineItems.sumOf { it.discountAmount }
    val amount get() = cartLineItems.sumOf { it.amount }

    init {
        cartLineItems.distinct().forEachIndexed { index, item ->
            this.cartLineItems.add(
                CartLineItem(
                    id = CartLineItem.createId(cartId = this.id, index = index),
                    cartLineItem = item
                )
            )
        }
    }

    fun addCartLineItem(cartLineItem: CartLineItem) {
        cartLineItems.firstOrNull { it == cartLineItem }
            ?.let {
                it.quantity = cartLineItem.quantity
            }
            ?: run {
                cartLineItems.add(
                    CartLineItem(
                        id = CartLineItem.createId(cartId = this.id, index = cartLineItems.size),
                        cartLineItem = cartLineItem
                    )
                )
            }
    }

    fun removeCartLineItem(cartLineItem: CartLineItem) {
        cartLineItems.firstOrNull { it == cartLineItem }?.let { cartLineItems.remove(it) }
    }

    companion object {
        fun getId(accountId: Long): String {
            val sb: StringBuilder = StringBuilder()
                .append(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSSSSS")))
                .append(RandomStringUtils.randomAlphabetic(4).uppercase(Locale.getDefault()))
                .append(accountId)
            return sb.toString()
        }
    }
}