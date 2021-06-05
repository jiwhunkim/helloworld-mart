package com.helloworld.domain.cart

import com.helloworld.ProjectConfig
import com.helloworld.config.redis.config.RedisConfig
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.comparables.shouldBeEqualComparingTo
import org.springframework.boot.test.autoconfigure.data.redis.DataRedisTest
import org.springframework.context.annotation.Import
import org.springframework.test.context.ActiveProfiles
import java.math.BigDecimal

@DataRedisTest
@Import(RedisConfig::class)
@ActiveProfiles("test")
class CartLineItemSpec : DescribeSpec() {
    init {
        describe("cartLineItem") {
            it("create cartLineItem test") {
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
                cartLineItem.amount.shouldBeEqualComparingTo(BigDecimal(1000))
                cartLineItem.quantity = 2
                cartLineItem.amount.shouldBeEqualComparingTo(BigDecimal(2000))
            }
        }
    }
}