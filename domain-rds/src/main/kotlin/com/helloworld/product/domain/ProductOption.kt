package com.helloworld.product.domain

import com.helloworld.seller.domain.SellerProduct
import java.math.BigDecimal
import javax.persistence.*

@Entity(name = "product_options")
class ProductOption(
    code: String,
    name: String,
    description: String,
    productId: Long,
    sellerProduct: SellerProduct,
    salesAmount: BigDecimal,
    discountAmount: BigDecimal,
    amount: BigDecimal
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

    var productId: Long = productId
        protected set

    @ManyToOne
    @JoinColumn(
        name = "seller_product_id",
        nullable = false,
        insertable = true,
        updatable = true,
        foreignKey = ForeignKey(value = ConstraintMode.NO_CONSTRAINT)
    )
    var sellerProduct: SellerProduct = sellerProduct
        protected set

    var salesAmount: BigDecimal = salesAmount
        protected set
    var discountAmount: BigDecimal = discountAmount
        protected set
    var amount: BigDecimal = amount
        protected set
}
