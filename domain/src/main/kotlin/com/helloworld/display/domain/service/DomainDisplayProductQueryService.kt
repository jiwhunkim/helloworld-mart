package com.helloworld.display.domain.service

import com.helloworld.display.domain.DisplayProduct
import com.helloworld.display.domain.DisplayProductRepository
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
            return displayProductRepository.findByProductId(productId).orElseThrow { EntityNotFoundException() }
        } catch (ex: EntityNotFoundException) {
            throw NoSuchElementException()
        }
    }
}
