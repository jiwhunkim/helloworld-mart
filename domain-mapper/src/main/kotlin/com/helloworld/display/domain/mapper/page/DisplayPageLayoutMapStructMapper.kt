package com.helloworld.display.domain.mapper.page

import com.helloworld.config.mapper.MapperSpringConfig
import com.helloworld.display.domain.dto.page.DisplayPageLayoutDto
import com.helloworld.display.domain.page.DisplayPageLayout
import org.mapstruct.Mapper
import org.springframework.core.convert.converter.Converter

@Mapper(
    config = MapperSpringConfig::class,
    uses = [DisplayPageMapStructMapper::class]
)
interface DisplayPageLayoutMapStructMapper : Converter<DisplayPageLayout, DisplayPageLayoutDto>
