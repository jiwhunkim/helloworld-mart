package com.helloworld.display.data

import com.helloworld.domain.display.PageLayoutType
import java.time.ZonedDateTime

data class DisplayPageItemDto(
    val id: Long,
    val pageId: Long,
    val name: String,
    val layoutType: PageLayoutType,
    val pageComponents: List<DisplayPageComponentItemDto> = emptyList(),
    val startAt: ZonedDateTime = ZonedDateTime.now(),
    val endAt: ZonedDateTime = ZonedDateTime.now()
) {
    constructor() : this(id = 0L, pageId = 0L, layoutType = PageLayoutType.DESKTOP_WEB, name = "name")
}
