package com.helloworld.stock.domain

class Stocks(
    private val list: List<Stock>
) {
    fun soldOut(): Boolean {
        return list.count { it.availableQuantity() > 0 } == 0
    }
}
