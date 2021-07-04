package com.helloworld.display.domain.mapper

import com.helloworld.config.mapper.MapperSpringConfig
import com.helloworld.display.domain.DisplayProduct
import com.helloworld.display.domain.dto.DisplayProductDto
import org.mapstruct.Mapper
import org.springframework.core.convert.converter.Converter

@Mapper(
    config = MapperSpringConfig::class,
    uses = []
)
interface DisplayProductMapStructMapper : Converter<DisplayProduct, DisplayProductDto>
