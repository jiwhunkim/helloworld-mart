package com.helloworld.display.domain.product

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity(name = "display_products")
class DisplayProduct(
    productId: Long,
    display: Boolean = true,
    sale: Boolean = true,
    soldOut: Boolean = false,
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0L
        protected set
    var productId: Long = productId
        protected set
    var display: Boolean = display
        protected set
    var sale: Boolean = sale
        protected set
    var soldOut: Boolean = soldOut
        protected set
}
