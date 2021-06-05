package com.helloworld.domain

import com.helloworld.config.DataSourceConfig
import com.helloworld.config.audit.AuditorAwareImpl
import com.helloworld.domain.order.OrderRepository
import com.helloworld.domain.product.*
import com.helloworld.rds.config.RdsConfig
import io.kotest.core.spec.style.DescribeSpec
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.context.annotation.Import
import org.springframework.test.context.ActiveProfiles
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional
import javax.persistence.EntityManager

@DataJpaTest
@Transactional(propagation = Propagation.NOT_SUPPORTED)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(RdsConfig::class, DataSourceConfig::class, AuditorAwareImpl::class, ProductSaleService::class)
@ActiveProfiles("test")
class OrderPlaceServiceSpec(
    entityManager: EntityManager,
    productRepository: ProductRepository,
    productOptionRepository: ProductOptionRepository,
    skuRepository: SkuRepository,
    sellerRepository: SellerRepository,
    sellerProductRepository: SellerProductRepository,
    orderRepository: OrderRepository
): DescribeSpec() {
    init {

    }
}