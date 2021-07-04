package com.helloworld.domain.cart

import com.helloworld.order.domain.*
import com.helloworld.order.domain.converter.CartProductConverter
import com.helloworld.order.domain.converter.CartProductOptionConverter
import com.helloworld.order.domain.converter.CartSellerProductConverter
import com.helloworld.order.domain.service.DomainCartCommandService
import com.helloworld.product.domain.ProductFixture
import com.helloworld.product.domain.ProductOptionFixture
import com.helloworld.seller.domain.SellerFixture
import com.helloworld.seller.domain.SellerProductFixture
import com.helloworld.stock.domain.SkuFixture
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.comparables.shouldBeEqualComparingTo
import io.kotest.matchers.nulls.shouldNotBeNull
import io.mockk.mockk
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.context.annotation.Import
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.junit.jupiter.SpringExtension
import java.math.BigDecimal

@ExtendWith(SpringExtension::class)
@Import(CartProductConverter::class, CartProductOptionConverter::class, CartSellerProductConverter::class)
@ActiveProfiles("test")
class DomainCartCommandServiceSpec(
    cartProductConverter: CartProductConverter,
    cartProductOptionConverter: CartProductOptionConverter,
    cartSellerProductConverter: CartSellerProductConverter
) : DescribeSpec() {
    init {
        val cartRepository: CartRepository = mockk()
        val domainCartCommandService = DomainCartCommandService(
            cartProductConverter,
            cartProductOptionConverter,
            cartSellerProductConverter,
            cartRepository
        )

        describe(".createCartLineItem") {
            it("make object") {
                var seller = SellerFixture.of()
                var sku = SkuFixture.of(supplyPrice = BigDecimal(1000))
                var sellerProduct = SellerProductFixture.of(
                    seller = seller,
                    sku = sku,
                    salesAmount = BigDecimal(1000)
                )
                var productOption = ProductOptionFixture.of(
                    sellerProduct = sellerProduct,
                    salesAmount = BigDecimal.TEN,
                    discountAmount = BigDecimal.ZERO,
                    amount = BigDecimal.TEN
                )
                val cartLineItem = domainCartCommandService.createCartLineItem(
                    product = ProductFixture.of(),
                    productOption = productOption,
                    sellerProduct = sellerProduct,
                    quantity = 1
                )

                cartLineItem.shouldNotBeNull()
                cartLineItem.amount.shouldBeEqualComparingTo(sellerProduct.amount.multiply(BigDecimal(1)))
            }
        }
    }
}
