package com.helloworld.domain.cart

import com.helloworld.DomainApplication
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles

@SpringBootTest(classes = [DomainApplication::class])
@ActiveProfiles("test")
class DomainCartCommandServiceSpec {
}