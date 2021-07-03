package com.helloworld.stock.domain

import com.helloworld.common.domain.BaseEntity
import com.helloworld.seller.domain.SellerProduct
import org.hibernate.annotations.DynamicUpdate
import javax.persistence.*

@Entity(name = "stocks")
@DynamicUpdate
class Stock(quantity: Int = 0, reservedQuantity: Int = 0, sellerProduct: SellerProduct) : BaseEntity() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0L
        protected set

    var quantity: Int = quantity
        protected set

    var reservedQuantity: Int = reservedQuantity
        protected set

    @OneToOne(cascade = [CascadeType.ALL])
    @JoinColumn(name = "seller_product_id", foreignKey = ForeignKey(value = ConstraintMode.NO_CONSTRAINT))
    var sellerProduct: SellerProduct = sellerProduct

    fun availableQuantity() = quantity - reservedQuantity

    fun ableSale(quantity: Int): Boolean {
        return availableQuantity() >= quantity
    }

    fun up(quantity: Int) {
        this.quantity += quantity
    }

    fun down(quantity: Int) {
        this.quantity -= quantity
    }
}
