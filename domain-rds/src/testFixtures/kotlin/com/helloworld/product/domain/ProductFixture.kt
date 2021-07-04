package com.helloworld.product.domain

object ProductFixture {
    fun of(
        code: String = "code",
        name: String = "name",
        description: String = "description",
        mallId: Long = 0L
    ) = Product(
        code = code,
        name = name,
        description = description,
        mallId = mallId
    )
}
