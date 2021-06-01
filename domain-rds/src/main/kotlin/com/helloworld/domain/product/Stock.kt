package com.helloworld.domain.product

import org.springframework.data.domain.AbstractAggregateRoot
import org.springframework.data.domain.AfterDomainEventPublication
import javax.persistence.*

@Entity(name = "stocks")
class Stock(quantity: Int = 0, reservedQuantity: Int = 0, sellerProduct: SellerProduct)
    : AbstractAggregateRoot<Stock>() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0L
        protected set

    var quantity: Int = quantity
        protected set

    var reservedQuantity: Int = reservedQuantity
        protected set

    @OneToOne(cascade = [CascadeType.ALL])
    @JoinColumn(name = "sellerProductId", foreignKey = ForeignKey(value = ConstraintMode.NO_CONSTRAINT))
    var sellerProduct: SellerProduct = sellerProduct

    fun availableQuantity() = quantity - reservedQuantity

    fun up(quantity: Int) {
        this.quantity += quantity
    }

    fun down(quantity: Int) {
        this.quantity -= quantity
    }
}