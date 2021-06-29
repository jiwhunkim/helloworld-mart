package com.helloworld.display.data

data class DisplayPageComponentItemDto(
    val id: Long,
    val name: String,
    val title: String,
    val component: DisplayPageComponentDto,
    val contents: List<DisplayPageContentItemDto>,
    val order: Int
)
