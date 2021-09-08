package com.helloworld.display.domain

import com.helloworld.display.domain.page.DisplayPage
import com.helloworld.display.domain.page.PageLayoutType

object DisplayPageFixture {
    fun of() = DisplayPage(
        mallId = 1L,
        name = "name",
        code = "code",
        layoutType = PageLayoutType.DESKTOP_WEB
    )
}
