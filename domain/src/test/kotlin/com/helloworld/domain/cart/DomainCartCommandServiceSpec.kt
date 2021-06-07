package com.helloworld.domain.cart

import com.helloworld.DomainApplication
import com.helloworld.config.DataSourceConfig
import com.helloworld.config.audit.AuditorAwareImpl
import com.helloworld.domain.order.OrderRepository
import com.helloworld.rds.config.RdsConfig
import io.kotest.core.spec.style.DescribeSpec
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import
import org.springframework.test.context.ActiveProfiles

@SpringBootTest(classes = [DomainApplication::class])
@Import(RdsConfig::class, DataSourceConfig::class, AuditorAwareImpl::class)
@ActiveProfiles("test")
class DomainCartCommandServiceSpec(orderRepository: OrderRepository): DescribeSpec() {
    init {
        describe("hello") {
            it("hello2") {
                orderRepository.findById(1L)
            }
        }
    }
}