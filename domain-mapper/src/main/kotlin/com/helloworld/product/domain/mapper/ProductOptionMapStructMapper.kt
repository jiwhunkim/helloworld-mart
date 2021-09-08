package com.helloworld.product.domain.mapper

import com.helloworld.config.mapper.MapperSpringConfig
import com.helloworld.product.domain.ProductOption
import com.helloworld.product.domain.dto.ProductOptionDto
import org.mapstruct.Mapper
import org.springframework.core.convert.converter.Converter

@Mapper(
    config = MapperSpringConfig::class,
    uses = []
)
interface ProductOptionMapStructMapper : Converter<ProductOption, ProductOptionDto>
