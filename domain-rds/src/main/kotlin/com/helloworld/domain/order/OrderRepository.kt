package com.helloworld.domain.order

import org.springframework.data.jpa.repository.JpaRepository

interface OrderRepository : JpaRepository<OrderEntity, Long> {
}