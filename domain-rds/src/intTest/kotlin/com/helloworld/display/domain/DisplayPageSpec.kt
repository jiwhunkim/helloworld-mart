package com.helloworld.display.domain

import com.helloworld.config.audit.AuditorAwareImpl
import com.helloworld.display.domain.page.DisplayPageRepository
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
class DisplayPageSpec(
    entityManager: EntityManager,
    displayPageRepository: DisplayPageRepository
) : DescribeSpec() {
    init {
        describe("create display page test") {
            it("page") {
                val displayPage = DisplayPageFixture.of()
                displayPage.shouldNotBeNull()
                var result = displayPageRepository.save(displayPage)
                entityManager.clear()
                var found = displayPageRepository.findById(result.id)
                println(found.get().id)
            }
        }
    }
}
