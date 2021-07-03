package com.helloworld.display.data.dto

data class DisplayPageContentItemDto(
    val id: Long,
    val name: String,
    val content: DisplayPageContentDto,
    val order: Int
)
