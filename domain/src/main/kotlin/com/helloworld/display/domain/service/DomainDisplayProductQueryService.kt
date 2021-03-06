package com.helloworld.display.domain.service

import com.helloworld.display.domain.product.DisplayProduct
import com.helloworld.display.domain.product.DisplayProductRepository
import org.springframework.stereotype.Service
import java.time.ZonedDateTime
import javax.persistence.EntityNotFoundException

@Service
class DomainDisplayProductQueryService(
    private val displayProductRepository: DisplayProductRepository
) {
    fun findByProductId(productId: Long): DisplayProduct {
        try {
            println(ZonedDateTime.now())
            return displayProductRepository.findByProductId(productId)
                .orElseThrow { EntityNotFoundException("productId - $productId not exist") }
        } catch (ex: EntityNotFoundException) {
            throw NoSuchElementException(ex.message)
        }
    }
}
