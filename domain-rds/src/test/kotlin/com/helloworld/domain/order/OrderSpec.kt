package com.helloworld.domain.order

import com.helloworld.config.DataSourceConfig
import com.helloworld.config.audit.AuditorAwareImpl
import com.helloworld.domain.product.*
import com.helloworld.rds.config.RdsConfig
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.comparables.shouldBeEqualComparingTo
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.context.annotation.Import
import org.springframework.test.context.ActiveProfiles
import java.math.BigDecimal
import javax.persistence.EntityManager

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(RdsConfig::class, DataSourceConfig::class, AuditorAwareImpl::class)
@ActiveProfiles("test")
class OrderSpec(
    entityManager: EntityManager,
    productRepository: ProductRepository,
    productOptionRepository: ProductOptionRepository,
    skuRepository: SkuRepository,
    sellerRepository: SellerRepository,
    sellerProductRepository: SellerProductRepository,
    orderRepository: OrderRepository
) : DescribeSpec() {
    init {
        describe("create order test") {
            it("order") {
                val order = OrderEntity(accountId = 1L, orderLineItems = mutableListOf())
                order.shouldNotBeNull()
                var result = orderRepository.save(order)
                entityManager.clear()
                var found = orderRepository.findById(result.id)
                println(found.get().id)
            }

            it("order with orderLineItems") {
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
                        sellerProduct = sellerProduct
                    )
                productOptionRepository.save(productOption)

                val orderLineItem = OrderLineItem(
                    product = product,
                    productOption = productOption,
                    sellerProduct = sellerProduct,
                    quantity = 1
                )
                val order = OrderEntity(accountId = 1L, orderLineItems = listOf(orderLineItem))
                var save = orderRepository.saveAndFlush(order)
                entityManager.clear()
                val result = orderRepository.findById(save.id)
                result.get().totalAmount.shouldBeEqualComparingTo(BigDecimal(1000))
                result.get().orderLineItems.size.shouldBe(1)
            }
        }
    }
}