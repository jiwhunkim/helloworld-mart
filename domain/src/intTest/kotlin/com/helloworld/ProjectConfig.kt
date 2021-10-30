package com.helloworld

import com.helloworld.RdsProjectListener.rdsContainer
import com.helloworld.RedisProjectListener.redisContainer
import io.kotest.core.config.AbstractProjectConfig
import io.kotest.core.extensions.Extension
import io.kotest.extensions.testcontainers.perProject
import io.kotest.spring.SpringAutowireConstructorExtension
import io.kotest.spring.SpringListener

class ProjectConfig : AbstractProjectConfig() {
    override fun listeners() = listOf(
        SpringListener,
        rdsContainer.perProject("rds-container"),
        redisContainer.perProject("redis-container"),
    )
    override fun extensions(): List<Extension> = listOf(SpringAutowireConstructorExtension)
}
