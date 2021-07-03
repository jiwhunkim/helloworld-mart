package com.helloworld.display.service

import com.helloworld.display.data.dto.response.DisplayProductResponseDto
import com.helloworld.display.domain.dto.DisplayProductDto
import com.helloworld.product.domain.service.DomainProductQueryService
import com.helloworld.seller.domain.dto.SellerDto
import com.helloworld.stock.domain.service.DomainStockQueryService
import org.springframework.stereotype.Service

@Service
class ProductApplicationService(
    val domainProductQueryService: DomainProductQueryService,
    val domainStockQueryService: DomainStockQueryService
) {
    fun detail(id: Long): DisplayProductResponseDto {
        // get product domain productDetail
        val product = domainProductQueryService.findProductById(id)
        val productOptions = domainProductQueryService.findProductOptionByProductId(id)
        // get sku domain sku
        // get stock domain stock
        // get seller domain seller
        return DisplayProductResponseDto(DisplayProductDto(), SellerDto())
    }
}
