package com.helloworld

import io.kotest.core.listeners.ProjectListener
import io.kotest.core.spec.AutoScan
import org.testcontainers.containers.MariaDBContainer
import org.testcontainers.utility.DockerImageName

@AutoScan
object RdsProjectListener : ProjectListener {
    val rdsContainer = MariaDBContainer<Nothing>(DockerImageName.parse("mariadb:latest")).apply {
        withCreateContainerCmdModifier { cmd -> cmd.withName("rds-container") }
    }

    override suspend fun beforeProject() {
        super.beforeProject()
        System.setProperty("spring.datasource.hikari.driver-class-name", rdsContainer.driverClassName)
        System.setProperty("spring.datasource.hikari.jdbc-url", rdsContainer.jdbcUrl)
        System.setProperty("spring.datasource.hikari.username", rdsContainer.username)
        System.setProperty("spring.datasource.hikari.password", rdsContainer.password)
    }
}