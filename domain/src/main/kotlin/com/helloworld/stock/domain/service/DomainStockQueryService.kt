package com.helloworld.stock.domain.service

import com.helloworld.stock.domain.StockRepository
import org.springframework.stereotype.Service

@Service
class DomainStockQueryService(
    private val stockRepository: StockRepository
)
