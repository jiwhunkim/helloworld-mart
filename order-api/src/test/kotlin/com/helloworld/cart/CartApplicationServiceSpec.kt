package com.helloworld.cart

import com.helloworld.OrderApplication
import com.helloworld.cart.data.CreateCartDto
import com.helloworld.config.DataSourceConfig
import com.helloworld.config.audit.AuditorAwareImpl
import com.helloworld.domain.product.DomainProductQueryService
import com.helloworld.domain.product.Product
import com.helloworld.rds.config.RdsConfig
import com.ninjasquad.springmockk.MockkBean
import io.kotest.core.listeners.TestListener
import io.kotest.core.spec.style.DescribeSpec
import io.mockk.every
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import
import org.springframework.test.context.ActiveProfiles

@SpringBootTest(classes = [OrderApplication::class])
@Import(RdsConfig::class, DataSourceConfig::class, AuditorAwareImpl::class)
@ActiveProfiles(profiles = ["test"])
class CartApplicationServiceSpec(cartApplicationService: CartApplicationService) : DescribeSpec() {
    @MockkBean
    private lateinit var domainProductQueryService: DomainProductQueryService

    init {
        describe(".create") {
            context("with proepry data") {
                it("success") {
                    every { domainProductQueryService.findProductById(any()) } returns Product(
                        code = "code",
                        name = "name",
                        description = "description"
                    )
                    val createCartDto = CreateCartDto(
                        1L,
                        1L,
                        1L,
                        1
                    )
                    cartApplicationService.create(1L, createCartDto)
                }
            }
        }
    }
}