package com.helloworld.domain

import com.helloworld.domain.product.SellerProductRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import javax.persistence.EntityNotFoundException

@Service
class ProductSaleService(
    val sellerProductRepository: SellerProductRepository
) {
    @Transactional
    fun sale(sellerProductId: Long, quantity: Int) {
        val sellerProduct = sellerProductRepository.findById(sellerProductId).orElseThrow { EntityNotFoundException() }
        sellerProduct.checkSaleByQuantity(quantity)
        sellerProduct.stock.down(quantity)
    }
}
