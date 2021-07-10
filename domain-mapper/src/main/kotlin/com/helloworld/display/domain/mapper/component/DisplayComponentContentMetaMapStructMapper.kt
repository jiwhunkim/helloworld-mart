package com.helloworld.display.domain.mapper.component

import com.helloworld.config.mapper.MapperSpringConfig
import com.helloworld.display.domain.component.DisplayComponentContentMeta
import com.helloworld.display.domain.dto.component.DisplayComponentContentMetaDto
import com.helloworld.display.domain.mapper.content.DisplayContentMetaMapStructMapper
import org.mapstruct.Mapper
import org.springframework.core.convert.converter.Converter

@Mapper(
    config = MapperSpringConfig::class,
    uses = [
        DisplayComponentMapStructMapper::class,
        DisplayContentMetaMapStructMapper::class
    ]
)
interface DisplayComponentContentMetaMapStructMapper :
    Converter<DisplayComponentContentMeta, DisplayComponentContentMetaDto>
