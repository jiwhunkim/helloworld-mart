package com.helloworld.domain.product

import org.springframework.stereotype.Service
import javax.persistence.EntityNotFoundException

@Service
class DomainProductQueryService(
    private val productRepository: ProductRepository,
    private val productOptionRepository: ProductOptionRepository
) {
    fun findProductById(id: Long): Product {
        return productRepository.findById(id).orElseThrow { EntityNotFoundException("product $id not found") }
    }

    fun findProductOptionById(id: Long): ProductOption {
        return productOptionRepository.findById(id)
            .orElseThrow { EntityNotFoundException("productOption $id not found") }
    }
}