package com.helloworld.order.domain

import com.helloworld.config.audit.AuditorAwareImpl
import com.helloworld.order.domain.entity.OrderEntity
import com.helloworld.order.domain.entity.OrderLineItem
import com.helloworld.order.domain.repository.OrderRepository
import com.helloworld.product.domain.ProductFixture
import com.helloworld.product.domain.ProductOptionFixture
import com.helloworld.product.domain.ProductOptionRepository
import com.helloworld.product.domain.ProductRepository
import com.helloworld.rds.config.RdsConfig
import com.helloworld.seller.domain.Seller
import com.helloworld.seller.domain.SellerProductFixture
import com.helloworld.seller.domain.SellerProductRepository
import com.helloworld.seller.domain.SellerRepository
import com.helloworld.sku.domain.SkuRepository
import com.helloworld.stock.domain.SkuFixture
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
@Import(RdsConfig::class, AuditorAwareImpl::class)
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
                val order = OrderEntityFixture.of()
                order.shouldNotBeNull()
                var result = orderRepository.save(order)
                entityManager.clear()
                var found = orderRepository.findById(result.id)
                println(found.get().id)
            }

            it("order with orderLineItems") {
                val product = productRepository.save(ProductFixture.of())
                val sku =
                    skuRepository.save(SkuFixture.of(supplyPrice = BigDecimal(1000)))
                val seller = sellerRepository.save(Seller(name = "name"))

                val sellerProduct = SellerProductFixture.of(seller = seller, sku = sku, salesAmount = BigDecimal(1000))

                sellerProductRepository.save(sellerProduct)

                val productOption = ProductOptionFixture.of(
                    sellerProduct = sellerProduct,
                    salesAmount = BigDecimal(1000),
                    discountAmount = BigDecimal(0),
                    amount = BigDecimal(1000)
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
