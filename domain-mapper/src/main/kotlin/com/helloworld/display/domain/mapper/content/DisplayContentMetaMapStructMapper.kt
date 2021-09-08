package com.helloworld.display.domain.mapper.content

import com.helloworld.config.mapper.MapperSpringConfig
import com.helloworld.display.domain.content.DisplayContentMeta
import com.helloworld.display.domain.dto.content.DisplayContentMetaDto
import org.mapstruct.Mapper
import org.springframework.core.convert.converter.Converter

@Mapper(
    config = MapperSpringConfig::class,
    uses = []
)
interface DisplayContentMetaMapStructMapper : Converter<DisplayContentMeta, DisplayContentMetaDto>
