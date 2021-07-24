package com.helloworld.display.data.dto.display.condition

import com.helloworld.display.domain.page.DisplayPage
import com.helloworld.display.domain.page.DisplayPageCondition
import com.helloworld.display.domain.page.DisplayPageLayoutCondition
import org.springframework.format.annotation.DateTimeFormat
import java.time.ZonedDateTime

class DisplayPageLayoutSearchCondition(
    val name: String?,
    val enabled: Boolean?,
    @field:DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    val targetDate: ZonedDateTime?
) {
    fun toCondition(mallId: Long, pageId: Long): DisplayPageLayoutCondition {
        return DisplayPageLayoutCondition(mallId, pageId, name, enabled, targetDate)
    }
}
