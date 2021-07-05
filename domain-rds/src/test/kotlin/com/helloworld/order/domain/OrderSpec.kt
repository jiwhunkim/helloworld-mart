package com.helloworld.order.domain

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.nulls.shouldNotBeNull
import org.springframework.test.context.ActiveProfiles

@ActiveProfiles("test")
class OrderSpec(
) : DescribeSpec() {
    init {
        describe("create order test") {
            it("order") {
                val order = OrderEntityFixture.of()
                order.shouldNotBeNull()
            }
        }
    }
}
