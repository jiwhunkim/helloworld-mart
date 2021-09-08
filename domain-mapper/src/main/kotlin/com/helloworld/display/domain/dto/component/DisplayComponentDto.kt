package com.helloworld.display.domain.dto.component

import com.helloworld.display.domain.dto.page.DisplayPageDto

data class DisplayComponentDto(
    val id: Long,
    val page: DisplayPageDto,
    val name: String,
    val description: String
)
