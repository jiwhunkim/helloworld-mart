package com.helloworld.product.domain.service

import com.helloworld.config.audit.AuditorAwareImpl
import com.helloworld.rds.config.RdsConfig
import com.helloworld.seller.domain.Seller
import com.helloworld.seller.domain.SellerProduct
import com.helloworld.seller.domain.SellerProductRepository
import com.helloworld.seller.domain.SellerRepository
import com.helloworld.seller.domain.service.SellerProductSaleService
import com.helloworld.sku.domain.Sku
import com.helloworld.sku.domain.SkuRepository
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
@Import(RdsConfig::class, AuditorAwareImpl::class, SellerProductSaleService::class)
@ActiveProfiles("test")
@Transactional
class ProductSaleServiceSpec(
    val entityManager: EntityManager,
    val skuRepository: SkuRepository,
    val sellerRepository: SellerRepository,
    val sellerProductRepository: SellerProductRepository,
    val sellerProductSaleService: SellerProductSaleService
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
                        sellerProductSaleService.sale(sellerProductId = item.id, quantity = 1)
                    }
                }
            }

            describe("재고가 남은 경우") {
                it("재고가 차감된다") {
                    var seller = sellerRepository.save(Seller(name = "seller"))
                    var sku = skuRepository.save(
                        Sku(
                            code = "code",
                            name = "sku",
                            description = "description",
                            BigDecimal(1000)
                        )
                    )
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
                    sellerProductSaleService.sale(sellerProductId = item.id, quantity = 1)
                    sellerProductRepository.findById(item.id).get().stock.quantity.shouldBe(9)
                }
            }
        }
    }
}
