package com.helloworld.cart.service

import com.helloworld.OrderApplication
import com.helloworld.cart.data.CreateCartDto
import com.helloworld.domain.common.data.User
import com.helloworld.domain.product.*
import com.helloworld.rds.config.RdsConfig
import io.kotest.assertions.throwables.shouldThrowExactly
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import
import org.springframework.test.context.ActiveProfiles
import java.math.BigDecimal

@SpringBootTest(classes = [OrderApplication::class])
@Import(RdsConfig::class, DomainProductCommandService::class)
@ActiveProfiles(profiles = ["test"])
class CartApplicationServiceSpec(
    domainProductCommandService: DomainProductCommandService,
    cartApplicationService: CartApplicationService
) : DescribeSpec() {

    init {
        describe(".create") {
            val product = domainProductCommandService.save(ProductFixture.of())
            val sku = domainProductCommandService.save(SkuFixture.of(supplyPrice = BigDecimal(1000)))
            val seller = domainProductCommandService.save(SellerFixture.of())
            val sellerProduct =
                SellerProductFixture.of(
                    seller = seller,
                    sku = sku,
                    salesAmount = BigDecimal(1000)
                )
            domainProductCommandService.save(sellerProduct)

            val productOption =
                ProductOptionFixture.of(
                    sellerProduct = sellerProduct,
                    salesAmount = BigDecimal(1000),
                    amount = BigDecimal(1000)
                )
            domainProductCommandService.save(productOption)

            context("with properly data") {
                it("success") {
                    val createCartDto = CreateCartDto(
                        productId = product.id,
                        productOptionId = productOption.id,
                        sellerProductId = sellerProduct.id,
                        1
                    )

                    val user = User(
                        channelType = "HelloWorld",
                        authorization = "Bearer d02683e3-9b4e-4a4a-bc46-ff76e408ba09",
                        acceptLanguage = "ko-Kr",
                        accountId = 1L,
                        isMember = true,
                        deviceId = "testdeviceid",
                        osCode = "android",
                        osVersion = "1.0.0",
                        appVersion = "1.0.0"
                    )
                    val result = cartApplicationService.create(user, createCartDto)
                    result.shouldNotBeNull()
                }
            }

            context("with not accepted accountId") {
                it("throw exception") {
                    val createCartDto = CreateCartDto(
                        productId = product.id,
                        productOptionId = productOption.id,
                        sellerProductId = sellerProduct.id,
                        1
                    )

                    val user = User(
                        channelType = "HelloWorld",
                        authorization = "Bearer d02683e3-9b4e-4a4a-bc46-ff76e408ba09",
                        acceptLanguage = "ko-Kr",
                        accountId = 0L,
                        isMember = false,
                        deviceId = "testdeviceid",
                        osCode = "android",
                        osVersion = "1.0.0",
                        appVersion = "1.0.0"
                    )

                    val exception = shouldThrowExactly<IllegalArgumentException> {
                        cartApplicationService.create(user, createCartDto)
                    }
                    exception.message.shouldBe("not accepted account id")

                }
            }
        }
    }
}