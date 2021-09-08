package com.helloworld.display.domain.page

import com.querydsl.core.BooleanBuilder
import com.querydsl.core.types.Predicate
import com.querydsl.core.types.dsl.Expressions
import java.time.ZonedDateTime

class DisplayPageLayoutCondition(
    val mallId: Long?,
    val pageId: Long?,
    val name: String?,
    val enabled: Boolean?,
    val targetDate: ZonedDateTime?
) {

    private val displayPageLayout: QDisplayPageLayout = QDisplayPageLayout.displayPageLayout

    fun toPredicate(): Predicate {
        return BooleanBuilder().apply {
            mallId?.let { and(displayPageLayout.page.mallId.eq(mallId)) }
            pageId?.let { and(displayPageLayout.page.id.eq(pageId)) }
            name?.let { and(displayPageLayout.name.like(name)) }
            enabled?.let { and(displayPageLayout.enabled.eq(enabled)) }
            targetDate?.let {
                and(
                    Expressions.asDateTime(targetDate).between(displayPageLayout.startAt, displayPageLayout.endAt)
                )
            }
        }
    }
}
