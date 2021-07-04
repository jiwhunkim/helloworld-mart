package com.helloworld.display.service

import com.helloworld.display.data.dto.response.DisplayProductResponseDto
import com.helloworld.display.domain.mapper.DisplayProductMapStructMapper
import com.helloworld.display.domain.service.DomainDisplayProductQueryService
import com.helloworld.product.domain.mapper.ProductMapStructMapper
import com.helloworld.product.domain.mapper.ProductOptionMapStructMapper
import com.helloworld.product.domain.service.DomainProductQueryService
import com.helloworld.product.domain.service.ProductOptions
import org.springframework.stereotype.Service

@Service
class ProductApplicationService(
    private val domainDisplayProductQueryService: DomainDisplayProductQueryService,
    private val domainProductQueryService: DomainProductQueryService,
    private val displayProductMapStructMapper: DisplayProductMapStructMapper,
    private val productMapStructMapper: ProductMapStructMapper,
    private val productOptionMapStructMapper: ProductOptionMapStructMapper,
) {
    fun detail(id: Long): DisplayProductResponseDto {
        val displayProduct = domainDisplayProductQueryService.findByProductId(id)
        val displayProductDto = displayProductMapStructMapper.convert(displayProduct)
        val product = domainProductQueryService.findProductById(id)
        val productOptions = ProductOptions(domainProductQueryService.findProductOptionByProductId(product.id))
        val productOption = productOptionMapStructMapper.convert(productOptions.representativeProductOption())
        val productDto = productMapStructMapper.convert(product)
        return DisplayProductResponseDto(
            displayProduct = displayProductDto!!,
            product = productDto!!,
            productOption = productOption!!
        )
    }
}
