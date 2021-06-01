package com.helloworld.domain.order

import com.helloworld.config.DataSourceConfig
import com.helloworld.config.audit.AuditorAwareImpl
import com.helloworld.rds.config.RdsConfig
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.nulls.shouldNotBeNull
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.context.annotation.Import
import org.springframework.test.context.ActiveProfiles
import javax.persistence.EntityManager

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(RdsConfig::class, DataSourceConfig::class, AuditorAwareImpl::class)
@ActiveProfiles("test")
class OrderSpec(entityManager: EntityManager, orderRepository: OrderRepository) : DescribeSpec() {
    init {
        describe("create order test") {
            it("order") {
                val order = OrderEntity()
                order.shouldNotBeNull()
                order.orderLineItems.add(OrderLineItem())
                order.orderLineItems.add(OrderLineItem())
                order.orderLineItems.add(OrderLineItem())
                var result = orderRepository.save(order)
                entityManager.clear()
                var found = orderRepository.findById(result.id)
                println(found.get().id)
                println(found.get().orderLineItems.size)
            }
        }
    }
}