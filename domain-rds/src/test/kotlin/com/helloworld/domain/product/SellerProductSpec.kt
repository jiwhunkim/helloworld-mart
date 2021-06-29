package com.helloworld.domain.product

import com.helloworld.config.audit.AuditorAwareImpl
import com.helloworld.domain.ProductSaleService
import com.helloworld.rds.config.RdsConfig
import io.kotest.core.spec.style.DescribeSpec
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.context.annotation.Import
import org.springframework.test.context.ActiveProfiles
import java.math.BigDecimal

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(RdsConfig::class, AuditorAwareImpl::class, ProductSaleService::class)
@ActiveProfiles("test")
class SellerProductSpec(
    productSaleService: ProductSaleService,
    skuRepository: SkuRepository,
    sellerRepository: SellerRepository,
    sellerProductRepository: SellerProductRepository
) : DescribeSpec() {
    init {
        describe("create seller product") {
            it("create") {
                var seller = SellerFixture.of()
                sellerRepository.save(seller)
                var sku = SkuFixture.of(supplyPrice = BigDecimal(1000))
                skuRepository.save(sku)
                var sellerProduct = SellerProductFixture.of(
                    seller = seller,
                    sku = sku,
                    salesAmount = BigDecimal(1000)
                )
                var result = sellerProductRepository.save(sellerProduct)
                println(result.id)
            }
        }
    }
}
