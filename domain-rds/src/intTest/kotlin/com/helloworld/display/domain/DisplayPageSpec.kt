package com.helloworld.display.domain

import com.helloworld.config.audit.AuditorAwareImpl
import com.helloworld.display.domain.page.DisplayPageRepository
import com.helloworld.rds.config.RdsConfig
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.nulls.shouldNotBeNull
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.context.annotation.Import
import org.springframework.test.context.ActiveProfiles
import javax.persistence.EntityManager

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(RdsConfig::class, AuditorAwareImpl::class)
@ActiveProfiles("test")
class DisplayPageSpec(
    entityManager: EntityManager,
    displayPageRepository: DisplayPageRepository
) : DescribeSpec() {
    init {
        describe("create display page test") {
            it("page") {
                val displayPage = DisplayPageFixture.of()
                displayPage.shouldNotBeNull()
                var result = displayPageRepository.save(displayPage)
                entityManager.clear()
                var found = displayPageRepository.findById(result.id)
                println(found.get().id)
            }
        }
    }
}
