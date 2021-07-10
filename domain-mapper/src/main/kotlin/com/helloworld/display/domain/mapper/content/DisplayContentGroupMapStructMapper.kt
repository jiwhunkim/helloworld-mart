package com.helloworld.display.domain.mapper.content

import com.helloworld.config.mapper.MapperSpringConfig
import com.helloworld.display.domain.content.DisplayContentGroup
import com.helloworld.display.domain.dto.content.DisplayContentGroupDto
import org.mapstruct.Mapper
import org.springframework.core.convert.converter.Converter

@Mapper(
    config = MapperSpringConfig::class,
    uses = [DisplayContentMetaMapStructMapper::class]
)
interface DisplayContentGroupMapStructMapper : Converter<DisplayContentGroup, DisplayContentGroupDto>
