package com.helloworld.data.cart.mapper

import com.helloworld.config.mapper.MapperSpringConfig
import com.helloworld.data.cart.CartLineItemDto
import com.helloworld.domain.cart.CartLineItem
import org.mapstruct.Mapper
import org.springframework.core.convert.converter.Converter

@Mapper(config = MapperSpringConfig::class)
interface CartLineItemMapStructMapper : Converter<CartLineItem, CartLineItemDto>
