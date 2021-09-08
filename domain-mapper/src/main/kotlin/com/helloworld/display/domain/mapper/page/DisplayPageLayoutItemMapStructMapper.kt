package com.helloworld.display.domain.mapper.page

import com.helloworld.config.mapper.MapperSpringConfig
import com.helloworld.display.domain.dto.page.DisplayPageLayoutItemDto
import com.helloworld.display.domain.mapper.component.DisplayComponentItemMapStructMapper
import com.helloworld.display.domain.page.DisplayPageLayoutItem
import org.mapstruct.Mapper
import org.springframework.core.convert.converter.Converter

@Mapper(
    config = MapperSpringConfig::class,
    uses = [
        DisplayPageLayoutMapStructMapper::class,
        DisplayComponentItemMapStructMapper::class
    ]
)
interface DisplayPageLayoutItemMapStructMapper : Converter<DisplayPageLayoutItem, DisplayPageLayoutItemDto>
