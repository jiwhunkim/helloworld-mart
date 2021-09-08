package com.helloworld.display.domain.dto.component

data class DisplayComponentItemDto(
    val id: Long,
    val component: DisplayComponentDto,
    val name: String,
    val description: String
)
