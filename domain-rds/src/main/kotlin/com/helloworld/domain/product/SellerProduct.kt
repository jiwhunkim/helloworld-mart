package com.helloworld.domain.product

import java.math.BigDecimal
import javax.persistence.*

@Entity(name = "seller_products")
class SellerProduct(
    code: String,
    name: String,
    description: String,
    seller: Seller,
    sku: Sku,
    salesAmount: BigDecimal,
    discountAmount: BigDecimal = BigDecimal.ZERO,
) : BaseEntity() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0L
        protected set

    var code: String = code
        protected set

    var name: String = name
        protected set

    var description: String = description
        protected set

    var salesAmount: BigDecimal = salesAmount
        protected set

    var discountAmount: BigDecimal = discountAmount
        protected set

    var amount: BigDecimal = salesAmount.minus(discountAmount)
        protected set

    @ManyToOne
    @JoinColumn(
        name = "seller_id",
        insertable = true,
        updatable = true,
        foreignKey = ForeignKey(value = ConstraintMode.NO_CONSTRAINT)
    )
    var seller: Seller = seller
        protected set

    @OneToOne
    @JoinColumn(
        name = "sku_id",
        insertable = true,
        updatable = true,
        foreignKey = ForeignKey(value = ConstraintMode.NO_CONSTRAINT)
    )
    var sku: Sku = sku
        protected set

    @OneToOne(mappedBy = "sellerProduct", cascade = [CascadeType.ALL])
    var stock: Stock = Stock(quantity = 0, reservedQuantity = 0, this)
        protected set

    fun checkSaleByQuantity(quantity: Int) {
        if (!stock.ableSale(quantity)) {
            throw RuntimeException("sellerProduct ${this.id} is not enough quantity")
        }
    }
}
