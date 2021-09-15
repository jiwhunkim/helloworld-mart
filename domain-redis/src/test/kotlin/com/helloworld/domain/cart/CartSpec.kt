package com.helloworld.domain.cart

import com.helloworld.order.domain.*
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import org.springframework.test.context.ActiveProfiles
import java.math.BigDecimal

@ActiveProfiles("test")
class CartSpec() : DescribeSpec() {
    init {
        describe("cart") {
            it("create cart no list") {
                val cart = Cart(1L)
                cart.id.shouldNotBe(null)
            }
            it("create cart with empty list") {
                val cart = Cart(1L, emptyList())
                cart.id.shouldNotBe(null)
                cart.accountId.shouldBe(1L)
            }

            it("create cart with list") {
                val cartProduct = CartProduct(productId = 1L, productName = "productName")
                val cartProductOption = CartProductOption(
                    productOptionId = 1L,
                    productOptionName = "productOptionName",
                    salesAmount = BigDecimal(1100),
                    discountAmount = BigDecimal(100),
                    amount = BigDecimal(1000)
                )
                val cartSellerProduct = CartSellerProduct(
                    sellerProductId = 1L,
                    sellerProductName = "sellerProductName",
                    salesAmount = BigDecimal(1100),
                    discountAmount = BigDecimal(100),
                    amount = BigDecimal(1000)
                )
                val cartLineItem = CartLineItem(
                    cartProduct = cartProduct,
                    cartProductOption = cartProductOption,
                    cartSellerProduct = cartSellerProduct
                )
                val cartLineItem2 = CartLineItem(
                    cartProduct = cartProduct,
                    cartProductOption = cartProductOption,
                    cartSellerProduct = cartSellerProduct
                )

                val cart = Cart(1L, listOf(cartLineItem, cartLineItem2))
                cart.shouldNotBeNull()
            }
        }
    }
}
