package com.helloworld.domain.product

import java.math.BigDecimal
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity(name = "skus")
class Sku(
    code: String,
    name: String,
    description: String,
    supplyPrice: BigDecimal
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

    var supplyPrice: BigDecimal = supplyPrice
        protected set
}
