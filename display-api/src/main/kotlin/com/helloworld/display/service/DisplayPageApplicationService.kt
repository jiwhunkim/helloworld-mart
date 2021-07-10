package com.helloworld.display.service

import com.helloworld.display.data.dto.display.condition.DisplayPageSearchCondition
import com.helloworld.display.domain.dto.page.DisplayPageDto
import com.helloworld.display.domain.mapper.page.DisplayPageMapStructMapper
import com.helloworld.display.domain.service.DomainDisplayPageQueryService
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class DisplayPageApplicationService(
    private val domainDisplayPageQueryService: DomainDisplayPageQueryService,
    private val displayPageMapStructMapper: DisplayPageMapStructMapper
) {
    fun findAll(condition: DisplayPageSearchCondition, pageable: Pageable): Page<DisplayPageDto> {
        val page = domainDisplayPageQueryService.findAll(condition = condition.toCondition(), pageable = pageable)
        val list = page.content.map { displayPageMapStructMapper.convert(it) }.toList()
        return PageImpl(list, page.pageable, page.totalElements)
    }
}
