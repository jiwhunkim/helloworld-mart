package com.helloworld.display.domain.service

import com.helloworld.display.domain.DisplayPageFixture
import com.helloworld.display.domain.page.DisplayPageCondition
import com.helloworld.display.domain.page.DisplayPageRepository
import com.helloworld.display.domain.page.PageLayoutType
import com.querydsl.core.types.Predicate
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.collections.shouldNotBeEmpty
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.junit.jupiter.SpringExtension

@ExtendWith(SpringExtension::class)
@ActiveProfiles("test")
class DomainDisplayPageQueryServiceSpec() : DescribeSpec() {
    init {
        val displayPageRepository: DisplayPageRepository = mockk()
        val domainDisplayPageQueryService = DomainDisplayPageQueryService(displayPageRepository)

        describe(".findAll") {
            it("predicate") {
                every { displayPageRepository.findAll(any() as Predicate, any() as Pageable) }.returns(
                    PageImpl(listOf(DisplayPageFixture.of()), PageRequest.of(0, 10), 1)
                )
                val result = domainDisplayPageQueryService.findAll(
                    DisplayPageCondition(
                        mallId = 1L,
                        name = "name",
                        code = "code",
                        layoutType = PageLayoutType.DESKTOP_WEB
                    ),
                    PageRequest.of(0, 10)
                )
                result.shouldNotBeEmpty()
            }
        }
    }
}
