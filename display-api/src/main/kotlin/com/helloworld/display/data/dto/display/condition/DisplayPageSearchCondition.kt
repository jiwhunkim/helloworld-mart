package com.helloworld.display.data.dto.display.condition

import com.helloworld.display.domain.page.DisplayPageCondition
import com.helloworld.display.domain.page.PageLayoutType

class DisplayPageSearchCondition(
    val name: String?,
    val code: String?,
    val layoutType: PageLayoutType?
) {
    fun toCondition(mallId: Long): DisplayPageCondition {
        return DisplayPageCondition(mallId, name, code, layoutType)
    }
}
