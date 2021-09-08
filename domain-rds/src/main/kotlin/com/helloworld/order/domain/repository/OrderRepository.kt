package com.helloworld.order.domain.repository

import com.helloworld.order.domain.entity.OrderEntity
import org.springframework.data.jpa.repository.JpaRepository

interface OrderRepository : JpaRepository<OrderEntity, Long>
