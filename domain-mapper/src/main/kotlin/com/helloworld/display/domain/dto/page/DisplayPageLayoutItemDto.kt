package com.helloworld.display.domain.dto.page

import com.helloworld.display.domain.dto.component.DisplayComponentItemDto

data class DisplayPageLayoutItemDto(
    val id: Long,
    val layout: DisplayPageLayoutDto,
    val componentItem: DisplayComponentItemDto
)
