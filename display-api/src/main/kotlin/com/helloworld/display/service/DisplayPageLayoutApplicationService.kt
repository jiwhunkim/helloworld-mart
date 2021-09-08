package com.helloworld.display.service

import com.helloworld.display.data.dto.display.condition.DisplayPageLayoutSearchCondition
import com.helloworld.display.domain.dto.page.DisplayPageLayoutDto
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class DisplayPageLayoutApplicationService() {
    fun findAll(
        mallId: Long,
        pageId: Long,
        condition: DisplayPageLayoutSearchCondition,
        pageable: Pageable
    ): Page<DisplayPageLayoutDto> {
        return PageImpl(emptyList(), pageable, 0)
    }
}
