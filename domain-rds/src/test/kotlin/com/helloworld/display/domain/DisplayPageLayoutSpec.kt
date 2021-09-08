package com.helloworld.display.domain

import com.helloworld.display.domain.page.DisplayPage
import com.helloworld.display.domain.page.DisplayPageLayout
import com.helloworld.display.domain.page.PageLayoutType
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.nulls.shouldNotBeNull
import org.springframework.test.context.ActiveProfiles

@ActiveProfiles("test")
class DisplayPageLayoutSpec : DescribeSpec() {
    init {
        describe("create displayPageLayout test") {
            it("displayPageLayout") {
                val displayPage = DisplayPage(
                    mallId = 1L,
                    name = "displayPage",
                    code = "MAIN",
                    layoutType = PageLayoutType.DESKTOP_WEB
                )
                val displayPageLayout = DisplayPageLayout(
                    displayPage,
                    name = "displayPageLayout",
                    description = "description"
                )
                displayPageLayout.shouldNotBeNull()
            }
        }
    }
}
