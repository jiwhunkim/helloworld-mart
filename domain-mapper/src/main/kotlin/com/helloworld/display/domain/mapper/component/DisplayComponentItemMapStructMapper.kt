package com.helloworld.display.domain.mapper.component

import com.helloworld.config.mapper.MapperSpringConfig
import com.helloworld.display.domain.component.DisplayComponentItem
import com.helloworld.display.domain.dto.component.DisplayComponentItemDto
import org.mapstruct.Mapper
import org.springframework.core.convert.converter.Converter

@Mapper(
    config = MapperSpringConfig::class,
    uses = [DisplayComponentMapStructMapper::class]
)
interface DisplayComponentItemMapStructMapper : Converter<DisplayComponentItem, DisplayComponentItemDto>
