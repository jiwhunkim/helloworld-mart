package com.helloworld.domain.product

object ProductFixture {
    fun of(
        code: String = "code",
        name: String = "name",
        description: String = "description"
    ) = Product(
        code = code,
        name = name,
        description = description
    )
}