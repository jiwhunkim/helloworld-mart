package com.helloworld.display.domain.page

import com.querydsl.core.BooleanBuilder
import com.querydsl.core.types.Predicate

class DisplayPageCondition(
    val mallId: Long?,
    val name: String?,
    val code: String?,
    val layoutType: PageLayoutType?
) {

    val displayPage: QDisplayPage = QDisplayPage.displayPage

    fun toPredicate(): Predicate {
        return BooleanBuilder().apply {
            mallId?.let { and(displayPage.mallId.eq(mallId)) }
            name?.let { and(displayPage.name.like(name)) }
            code?.let { and(displayPage.code.eq(code)) }
            layoutType?.let { and(displayPage.layoutType.eq(layoutType)) }
        }
    }
}
