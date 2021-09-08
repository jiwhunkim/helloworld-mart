package com.helloworld.display.domain.mapper.component

import com.helloworld.config.mapper.MapperSpringConfig
import com.helloworld.display.domain.component.DisplayComponent
import com.helloworld.display.domain.dto.component.DisplayComponentDto
import com.helloworld.display.domain.mapper.page.DisplayPageMapStructMapper
import org.mapstruct.Mapper
import org.springframework.core.convert.converter.Converter

@Mapper(
    config = MapperSpringConfig::class,
    uses = [DisplayPageMapStructMapper::class]
)
interface DisplayComponentMapStructMapper : Converter<DisplayComponent, DisplayComponentDto>
