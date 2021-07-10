package com.helloworld.order.domain.service

import com.helloworld.config.audit.AuditorAwareImpl
import com.helloworld.order.domain.repository.OrderRepository
import com.helloworld.product.domain.ProductOptionRepository
import com.helloworld.product.domain.ProductRepository
import com.helloworld.rds.config.RdsConfig
import com.helloworld.seller.domain.SellerProductRepository
import com.helloworld.seller.domain.SellerRepository
import com.helloworld.seller.domain.service.SellerProductSaleService
import com.helloworld.sku.domain.SkuRepository
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
@Import(RdsConfig::class, AuditorAwareImpl::class, SellerProductSaleService::class)
@ActiveProfiles("test")
class OrderPlaceServiceSpec(
    entityManager: EntityManager,
    productRepository: ProductRepository,
    productOptionRepository: ProductOptionRepository,
    skuRepository: SkuRepository,
    sellerRepository: SellerRepository,
    sellerProductRepository: SellerProductRepository,
    orderRepository: OrderRepository
) : DescribeSpec() {
    init {
    }
}
