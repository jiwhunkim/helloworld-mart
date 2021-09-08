package com.helloworld.product.domain.service

import com.helloworld.product.domain.ProductOption

class ProductOptions(private val list: List<ProductOption>) {
    fun representativeProductOption(): ProductOption {
        return list.first { it.representative }
    }
}
