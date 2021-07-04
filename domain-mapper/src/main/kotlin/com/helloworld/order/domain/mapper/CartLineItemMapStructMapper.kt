package com.helloworld.order.domain.mapper

import com.helloworld.config.mapper.MapperSpringConfig
import com.helloworld.order.domain.CartLineItem
import com.helloworld.order.domain.dto.CartLineItemDto
import org.mapstruct.Mapper
import org.springframework.core.convert.converter.Converter

@Mapper(config = MapperSpringConfig::class)
interface CartLineItemMapStructMapper : Converter<CartLineItem, CartLineItemDto>
