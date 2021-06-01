package com.helloworld.domain.order

import javax.persistence.*

@Entity(name = "orders")
class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0L
        protected set

    @OneToMany(cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    @JoinColumn(name = "orderId", foreignKey = ForeignKey(value = ConstraintMode.NO_CONSTRAINT))
    var orderLineItems: MutableList<OrderLineItem> = mutableListOf()
        protected set
}