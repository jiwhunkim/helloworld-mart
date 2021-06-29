package com.helloworld.domain.cart

import org.springframework.data.annotation.Id
import java.math.BigDecimal
import java.util.*

class CartLineItem(
    id: String? = null,
    val cartProduct: CartProduct,
    val cartProductOption: CartProductOption,
    val cartSellerProduct: CartSellerProduct,
    quantity: Int = 1
) {
    @Id
    val id: String? = id
    var quantity: Int = quantity
    val salesAmount: BigDecimal get() = cartSellerProduct.salesAmount.multiply(BigDecimal(quantity))
    val discountAmount: BigDecimal get() = cartSellerProduct.discountAmount.multiply(BigDecimal(quantity))
    val amount: BigDecimal get() = cartSellerProduct.amount.multiply(BigDecimal(quantity))

    constructor(id: String, cartLineItem: CartLineItem) : this(
        id = id,
        cartProduct = cartLineItem.cartProduct,
        cartProductOption = cartLineItem.cartProductOption,
        cartSellerProduct = cartLineItem.cartSellerProduct,
        quantity = cartLineItem.quantity
    )

    override fun equals(other: Any?): Boolean {
        if (other is CartLineItem) {
            return this.cartProduct.productId == other.cartProduct.productId &&
                this.cartProductOption.productOptionId == other.cartProductOption.productOptionId &&
                this.cartSellerProduct.sellerProductId == other.cartSellerProduct.sellerProductId
        }
        return super.equals(other)
    }

    override fun hashCode(): Int {
        return Objects.hash(
            cartProduct.productId,
            cartProductOption.productOptionId,
            cartSellerProduct.sellerProductId
        )
    }

    companion object {
        fun createId(cartId: String, index: Int): String {
            return "${cartId}$index"
        }
    }
}
