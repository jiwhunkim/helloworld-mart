package com.helloworld.domain.cart

import com.helloworld.DomainApplication
import com.helloworld.config.audit.AuditorAwareImpl
import com.helloworld.rds.config.RdsConfig
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.nulls.shouldBeNull
import io.kotest.matchers.nulls.shouldNotBeNull
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import
import org.springframework.test.context.ActiveProfiles
import java.math.BigDecimal

@SpringBootTest(classes = [DomainApplication::class])
@Import(RdsConfig::class, AuditorAwareImpl::class)
@ActiveProfiles("test")
class DomainCartCommandServiceSpec(domainCartCommandService: DomainCartCommandService) : DescribeSpec() {
    init {
        describe(".placeCart") {
            it("properly save") {
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
                val result = domainCartCommandService.placeCart(1L, cartLineItem)
                result.id.shouldNotBeNull()
            }
        }
    }
}