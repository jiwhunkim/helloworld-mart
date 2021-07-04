package com.helloworld.stock.domain

import com.helloworld.seller.domain.SellerProduct
import org.springframework.data.jpa.repository.JpaRepository

interface StockRepository : JpaRepository<Stock, Long> {

}
