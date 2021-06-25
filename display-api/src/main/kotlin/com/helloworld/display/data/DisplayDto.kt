package com.helloworld.display.data

import java.time.ZonedDateTime

data class DisplayDto(
    val id: Long,
    val timestamp: ZonedDateTime = ZonedDateTime.now()
)
