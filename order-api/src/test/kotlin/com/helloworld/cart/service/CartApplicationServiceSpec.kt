package com.helloworld.cart.service

import com.helloworld.OrderApplication
import com.helloworld.cart.data.CreateCartDto
import com.helloworld.config.audit.AuditorAwareImpl
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
@Import(RdsConfig::class, AuditorAwareImpl::class)
@ActiveProfiles(profiles = ["test"])
class CartApplicationServiceSpec(
    productRepository: ProductRepository,
    productOptionRepository: ProductOptionRepository,
    sellerRepository: SellerRepository,
    sellerProductRepository: SellerProductRepository,
    skuRepository: SkuRepository,
    cartApplicationService: CartApplicationService
) : DescribeSpec() {

    init {
        describe(".create") {
            val product = productRepository.save(Product(code = "code", name = "name", description = "description"))
            val sku =
                skuRepository.save(Sku(code = "code", name = "sku", description = "description", BigDecimal(1000)))
            val seller = sellerRepository.save(Seller(name = "name"))
            val sellerProduct =
                SellerProduct(
                    code = "code",
                    name = "name",
                    description = "description",
                    seller = seller,
                    sku = sku,
                    salesAmount = BigDecimal(1000)
                )
            sellerProductRepository.save(sellerProduct)

            val productOption =
                ProductOption(
                    code = "code",
                    name = "name",
                    description = "description",
                    sellerProduct = sellerProduct,
                    salesAmount = BigDecimal(1000),
                    discountAmount = BigDecimal(0),
                    amount = BigDecimal(1000)
                )
            productOptionRepository.save(productOption)

            context("with properly data") {
                it("success") {
                    val createCartDto = CreateCartDto(
                        productId = product.id,
                        productOptionId = productOption.id,
                        sellerProductId = sellerProduct.id,
                        1
                    )
                    val result = cartApplicationService.create(1L, createCartDto)
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
                    val exception = shouldThrowExactly<IllegalArgumentException> {
                        cartApplicationService.create(0L, createCartDto)
                    }
                    exception.message.shouldBe("not accepted account id")

                }
            }
        }
    }
}