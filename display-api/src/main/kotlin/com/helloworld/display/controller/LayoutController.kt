package com.helloworld.display.controller

import com.helloworld.display.data.dto.display.condition.DisplayPageLayoutSearchCondition
import com.helloworld.display.domain.dto.page.DisplayPageLayoutDto
import com.helloworld.display.service.DisplayPageLayoutApplicationService
import com.helloworld.web.data.Response
import org.springdoc.api.annotations.ParameterObject
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.web.PageableDefault
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController(value = "DisplayLayoutlaControllerV1")
@RequestMapping(
    value = ["/display"],
    produces = ["application/vnd.helloworld.api.v1+json; charset=UTF-8"]
)
class LayoutController(
    private val displayPageLayoutApplicationService: DisplayPageLayoutApplicationService
) {
    @GetMapping("/mall/{mallId}/pages/{pageId}/layouts")
    fun index(
        @PathVariable mallId: Long, @PathVariable pageId: Long,
        @PageableDefault(sort = ["id"], direction = Sort.Direction.DESC) @ParameterObject pageable: Pageable,
        @ParameterObject condition: DisplayPageLayoutSearchCondition
    ): Response<Page<DisplayPageLayoutDto>> {
        return Response(displayPageLayoutApplicationService.findAll(mallId, pageId, condition, pageable));
    }
}
