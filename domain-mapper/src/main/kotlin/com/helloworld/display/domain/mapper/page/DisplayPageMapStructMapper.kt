package com.helloworld.display.domain.mapper.page

import com.helloworld.config.mapper.MapperSpringConfig
import com.helloworld.display.domain.dto.page.DisplayPageDto
import com.helloworld.display.domain.page.DisplayPage
import org.mapstruct.Mapper
import org.springframework.core.convert.converter.Converter

@Mapper(
    config = MapperSpringConfig::class,
    uses = []
)
interface DisplayPageMapStructMapper : Converter<DisplayPage, DisplayPageDto>
