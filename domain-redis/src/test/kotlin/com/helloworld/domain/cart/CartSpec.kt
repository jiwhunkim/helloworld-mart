package com.helloworld.domain.cart

import com.helloworld.config.redis.config.RedisConfig
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import org.springframework.boot.test.autoconfigure.data.redis.DataRedisTest
import org.springframework.context.annotation.Import
import org.springframework.test.context.ActiveProfiles

@DataRedisTest
@Import(RedisConfig::class)
@ActiveProfiles("test")
class CartSpec : DescribeSpec() {
    init {
        describe("cart") {
            it("create cart test") {
                val cart = Cart(1L)
                cart.id.shouldNotBe(null)
                cart.accountId.shouldBe(1L)
            }
        }
    }
}