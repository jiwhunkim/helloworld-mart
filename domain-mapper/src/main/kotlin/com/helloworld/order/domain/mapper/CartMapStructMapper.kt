package com.helloworld.order.domain.mapper

import com.helloworld.config.mapper.MapperSpringConfig
import com.helloworld.order.domain.Cart
import com.helloworld.order.domain.dto.CartDto
import org.mapstruct.Mapper
import org.springframework.core.convert.converter.Converter

@Mapper(
    config = MapperSpringConfig::class,
    uses = [CartLineItemMapStructMapper::class]
)
interface CartMapStructMapper : Converter<Cart, CartDto>
