package com.helloworld.display.domain.dto.component

import com.helloworld.display.domain.dto.content.DisplayContentGroupDto

data class DisplayComponentItemContentGroupDto(
    val id: Long,
    val componentItem: DisplayComponentItemDto,
    val contentGroup: DisplayContentGroupDto?,
    val position: Int
)
