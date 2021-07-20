package com.helloworld.display.domain.dto.page

import com.helloworld.display.domain.page.PageLayoutType
import java.time.ZonedDateTime

data class DisplayPageDto(
    val id: Long,
    val mallId: Long,
    val name: String,
    val code: String,
    val layoutType: PageLayoutType,
    val createdAt: ZonedDateTime,
    val updatedAt: ZonedDateTime,
    val createdBy: String,
    val updatedBy: String
)
