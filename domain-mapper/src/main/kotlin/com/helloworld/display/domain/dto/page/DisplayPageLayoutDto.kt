package com.helloworld.display.domain.dto.page

import java.time.ZonedDateTime

data class DisplayPageLayoutDto(
    val id: Long,
    val page: DisplayPageDto,
    val name: String,
    val enabled: Boolean,
    val startAt: ZonedDateTime,
    val endAt: ZonedDateTime,
    val createdAt: ZonedDateTime,
    val updatedAt: ZonedDateTime,
    val createdBy: String,
    val updatedBy: String
)
