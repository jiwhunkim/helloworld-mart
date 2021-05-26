package com.helloworld

import io.kotest.core.listeners.ProjectListener
import org.testcontainers.containers.GenericContainer

object RedisProjectListener : ProjectListener {
    val redisContainer = GenericContainer<Nothing>("redis:latest").apply {
        withExposedPorts(6379)
        withCreateContainerCmdModifier { cmd -> cmd.withName("redis-container") }
    }

    override suspend fun beforeProject() {
        super.beforeProject()
        val redisHost = redisContainer.host
        val redisPort = redisContainer.getMappedPort(6379)
        System.setProperty("spring.redis.host", redisHost)
        System.setProperty("spring.redis.port", redisPort.toString())
    }
}