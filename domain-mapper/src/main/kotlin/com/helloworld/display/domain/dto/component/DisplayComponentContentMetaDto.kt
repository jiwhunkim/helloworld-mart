package com.helloworld.display.domain.dto.component

import com.helloworld.display.domain.dto.content.DisplayContentMetaDto

data class DisplayComponentContentMetaDto(
    val id: Long,
    val component: DisplayComponentDto,
    val contentMeta: DisplayContentMetaDto
)
