package com.helloworld.domain.product

import org.springframework.context.event.EventListener
import org.springframework.data.domain.AbstractAggregateRoot
import javax.persistence.*


@Entity(name = "seller_products")
class SellerProduct(
    code: String,
    name: String,
    description: String,
    seller: Seller,
    sku: Sku
): BaseEntity() {
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

    @ManyToOne
    @JoinColumn(
        name = "sellerId",
        insertable = true,
        updatable = true,
        foreignKey = ForeignKey(value = ConstraintMode.NO_CONSTRAINT)
    )
    var seller: Seller = seller
        protected set

    @OneToOne
    @JoinColumn(
        name = "skuId",
        insertable = true,
        updatable = true,
        foreignKey = ForeignKey(value = ConstraintMode.NO_CONSTRAINT)
    )
    var sku: Sku = sku
        protected set

    @OneToOne(mappedBy = "sellerProduct", cascade = [CascadeType.ALL])
    var stock: Stock = Stock(quantity = 0, reservedQuantity = 0, this)
        protected set
}