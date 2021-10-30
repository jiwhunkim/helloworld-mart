package com.helloworld

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles

@ActiveProfiles(value = ["redis", "rds", "test"])
@SpringBootTest(classes = [DomainApplication::class])
class SpringBootSpec : DescribeSpec() {
    init {
        describe("hello") {
            it("world") {
                "1".shouldBe("1")
            }
        }
    }
}
