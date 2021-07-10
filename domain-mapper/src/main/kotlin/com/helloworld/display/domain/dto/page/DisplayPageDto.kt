package com.helloworld.display.domain.dto.page

import com.helloworld.display.domain.page.PageLayoutType

data class DisplayPageDto(
    val id: Long,
    val mallId: Long,
    val name: String,
    val code: String,
    val layoutType: PageLayoutType
)
