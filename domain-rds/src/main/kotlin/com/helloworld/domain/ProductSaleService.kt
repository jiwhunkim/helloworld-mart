package com.helloworld.domain

import com.helloworld.domain.product.SellerProduct
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
        if (sellerProduct.stock.availableQuantity() <= quantity) {
            throw RuntimeException("sellerProduct ${sellerProduct.id} is not enough quantity")
        }
        sellerProduct.stock.down(quantity)
    }
}