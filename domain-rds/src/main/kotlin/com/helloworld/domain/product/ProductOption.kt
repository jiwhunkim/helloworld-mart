package com.helloworld.domain.product

import javax.persistence.*

@Entity(name = "product_options")
class ProductOption(
    code: String,
    name: String,
    description: String,
    sellerProduct: SellerProduct
) {
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
        name = "seller_product_id",
        insertable = true,
        updatable = true,
        foreignKey = ForeignKey(value = ConstraintMode.NO_CONSTRAINT)
    )
    var sellerProduct: SellerProduct = sellerProduct
        protected set
}