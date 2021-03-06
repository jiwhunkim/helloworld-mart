package com.helloworld.order.domain.entity

import com.helloworld.common.domain.BaseEntity
import com.helloworld.product.domain.Product
import com.helloworld.product.domain.ProductOption
import com.helloworld.seller.domain.SellerProduct
import java.math.BigDecimal
import javax.persistence.*

@Entity(name = "order_line_items")
class OrderLineItem(
    product: Product,
    productOption: ProductOption,
    sellerProduct: SellerProduct,
    quantity: Int
) : BaseEntity() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0L
        protected set

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false, foreignKey = ForeignKey(value = ConstraintMode.NO_CONSTRAINT))
    var product: Product = product

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(
        name = "product_option_id",
        nullable = false,
        foreignKey = ForeignKey(value = ConstraintMode.NO_CONSTRAINT)
    )
    var productOption: ProductOption = productOption

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(
        name = "seller_product_id",
        nullable = false,
        foreignKey = ForeignKey(value = ConstraintMode.NO_CONSTRAINT)
    )
    var sellerProduct: SellerProduct = sellerProduct

    var quantity: Int = quantity

    var salesAmount: BigDecimal = sellerProduct.salesAmount

    var discountAmount: BigDecimal = sellerProduct.discountAmount

    var amount: BigDecimal = sellerProduct.amount

    var totalSalesAmount: BigDecimal = salesAmount.multiply(quantity.toBigDecimal())
    var totalDiscountAmount: BigDecimal = discountAmount.multiply(quantity.toBigDecimal())
    var totalAmount: BigDecimal = amount.multiply(quantity.toBigDecimal())
}
