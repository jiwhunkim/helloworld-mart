package com.helloworld.seller.domain.service

import com.helloworld.seller.domain.SellerProductRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import javax.persistence.EntityNotFoundException

@Service
class SellerProductSaleService(
    val sellerProductRepository: SellerProductRepository
) {
    @Transactional
    fun sale(sellerProductId: Long, quantity: Int) {
        val sellerProduct = sellerProductRepository.findById(sellerProductId).orElseThrow { EntityNotFoundException() }
        sellerProduct.checkSaleByQuantity(quantity)
        sellerProduct.stock.down(quantity)
    }
}
