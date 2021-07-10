package com.helloworld.display.domain.service

import com.helloworld.display.domain.page.DisplayPage
import com.helloworld.display.domain.page.DisplayPageCondition
import com.helloworld.display.domain.page.DisplayPageRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class DomainDisplayPageQueryService(
    private val displayPageRepository: DisplayPageRepository
) {
    fun findAll(condition: DisplayPageCondition, pageable: Pageable): Page<DisplayPage> {
        return displayPageRepository.findAll(condition.toPredicate(), pageable)
    }
}
