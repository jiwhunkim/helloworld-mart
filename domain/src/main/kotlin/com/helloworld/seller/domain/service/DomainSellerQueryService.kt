package com.helloworld.seller.domain.service

import com.helloworld.seller.domain.Seller
import com.helloworld.seller.domain.SellerRepository
import org.springframework.stereotype.Service
import javax.persistence.EntityNotFoundException

@Service
class DomainSellerQueryService(
    private val sellerRepository: SellerRepository
) {
    fun findById(sellerId: Long): Seller {
        return sellerRepository.findById(sellerId).orElseThrow { EntityNotFoundException() }
    }
}
