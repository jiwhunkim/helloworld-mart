package com.helloworld.domain.order

import com.helloworld.domain.product.BaseEntity
import java.math.BigDecimal
import javax.persistence.*

@Entity(name = "orders")
class OrderEntity(
    accountId: Long,
    orderLineItems: List<OrderLineItem> = emptyList()
) : BaseEntity() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0L
        protected set

    var accountId: Long = accountId
        protected set

    @OneToMany(cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", foreignKey = ForeignKey(value = ConstraintMode.NO_CONSTRAINT))
    var orderLineItems: MutableList<OrderLineItem> = orderLineItems.toMutableList()

    var totalSalesAmount: BigDecimal = orderLineItems.sumOf { it.totalSalesAmount }

    var totalDiscountAmount: BigDecimal = orderLineItems.sumOf { it.totalDiscountAmount }

    var totalAmount: BigDecimal = orderLineItems.sumOf { it.totalAmount }

    var billingAmount: BigDecimal = BigDecimal.ZERO
}
