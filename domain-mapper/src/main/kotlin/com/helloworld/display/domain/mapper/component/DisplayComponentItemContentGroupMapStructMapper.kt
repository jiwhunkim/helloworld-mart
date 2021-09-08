package com.helloworld.display.domain.mapper.component

import com.helloworld.config.mapper.MapperSpringConfig
import com.helloworld.display.domain.component.DisplayComponentItemContentGroup
import com.helloworld.display.domain.dto.component.DisplayComponentItemContentGroupDto
import com.helloworld.display.domain.mapper.content.DisplayContentGroupMapStructMapper
import org.mapstruct.Mapper
import org.springframework.core.convert.converter.Converter

@Mapper(
    config = MapperSpringConfig::class,
    uses = [
        DisplayComponentItemMapStructMapper::class,
        DisplayContentGroupMapStructMapper::class
    ]
)
interface DisplayComponentItemContentGroupMapStructMapper :
    Converter<DisplayComponentItemContentGroup, DisplayComponentItemContentGroupDto>
