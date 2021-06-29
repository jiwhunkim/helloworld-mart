package com.helloworld.data.cart.mapper

import com.helloworld.config.mapper.MapperSpringConfig
import com.helloworld.data.cart.CartDto
import com.helloworld.domain.cart.Cart
import org.mapstruct.Mapper
import org.springframework.core.convert.converter.Converter

@Mapper(
    config = MapperSpringConfig::class,
    uses = [CartLineItemMapStructMapper::class]
)
interface CartMapStructMapper : Converter<Cart, CartDto>
