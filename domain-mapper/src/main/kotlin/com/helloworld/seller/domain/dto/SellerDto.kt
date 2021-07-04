package com.helloworld.seller.domain.dto

data class SellerDto(
    val id: Long,
    val name: String
) {
    constructor() : this(id = 0L, name = "name")
}
