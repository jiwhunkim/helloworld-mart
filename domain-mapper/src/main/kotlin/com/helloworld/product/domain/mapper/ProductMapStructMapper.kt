package com.helloworld.product.domain.mapper

import com.helloworld.config.mapper.MapperSpringConfig
import com.helloworld.product.domain.Product
import com.helloworld.product.domain.dto.ProductDto
import org.mapstruct.Mapper
import org.springframework.core.convert.converter.Converter

@Mapper(
    config = MapperSpringConfig::class,
    uses = []
)
interface ProductMapStructMapper : Converter<Product, ProductDto>
