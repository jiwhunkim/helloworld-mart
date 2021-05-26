package com.helloworld.config.audit

import org.springframework.boot.test.context.TestConfiguration
import org.springframework.data.domain.AuditorAware
import org.springframework.data.jpa.repository.config.EnableJpaAuditing
import java.util.*

@TestConfiguration
@EnableJpaAuditing
class AuditorAwareImpl : AuditorAware<String> {
    override fun getCurrentAuditor(): Optional<String> {
        return Optional.of("SYSTEM")
    }
}