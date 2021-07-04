package com.helloworld.product.domain.dto

data class ProductDto(
    val id: Long,
    val code: String,
    val name: String,
    val description: String,
    val mallId: Long
) {

}
