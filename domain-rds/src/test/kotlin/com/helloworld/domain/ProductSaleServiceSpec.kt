package com.helloworld.domain

import com.helloworld.config.DataSourceConfig
import com.helloworld.config.audit.AuditorAwareImpl
import com.helloworld.domain.product.*
import com.helloworld.rds.config.RdsConfig
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.context.annotation.Import
import org.springframework.test.context.ActiveProfiles
import org.springframework.transaction.annotation.Transactional
import java.math.BigDecimal
import javax.persistence.EntityManager

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(RdsConfig::class, DataSourceConfig::class, AuditorAwareImpl::class, ProductSaleService::class)
@ActiveProfiles("test")
@Transactional
class ProductSaleServiceSpec(
    val entityManager: EntityManager,
    val skuRepository: SkuRepository,
    val sellerRepository: SellerRepository,
    val sellerProductRepository: SellerProductRepository,
    val productSaleService: ProductSaleService
) : DescribeSpec() {
    init {
        describe(".sale") {
            describe("재고가 부족한 경우") {
                it("exception 이 발생한다") {
                    var seller = sellerRepository.save(Seller(name = "seller"))
                    var sku = Sku(code = "code", name = "sku", description = "description", BigDecimal(1000))
                    skuRepository.save(sku)

                    var sellerProduct = SellerProduct(
                        code = "code",
                        name = "name",
                        description = "description",
                        seller = seller,
                        sku = sku,
                        salesAmount = BigDecimal(1000)
                    )
                    val item = sellerProductRepository.save(sellerProduct)

                    shouldThrow<RuntimeException> {
                        productSaleService.sale(sellerProductId = item.id, quantity = 1)
                    }
                }
            }

            describe("재고가 남은 경우") {
                it("재고가 차감된다") {
                    var seller = sellerRepository.save(Seller(name = "seller"))
                    var sku = skuRepository.save(Sku(code = "code", name = "sku", description = "description", BigDecimal(1000)))
                    var sellerProduct = SellerProduct(
                        code = "code",
                        name = "name",
                        description = "description",
                        seller = seller,
                        sku = sku,
                        salesAmount = BigDecimal(1000)
                    )
                    sellerProduct.stock.up(10)
                    entityManager.persist(seller)
                    val item = sellerProductRepository.save(sellerProduct)
                    productSaleService.sale(sellerProductId = item.id, quantity = 1)
                    sellerProductRepository.findById(item.id).get().stock.quantity.shouldBe(9)
                }
            }
        }
    }
}