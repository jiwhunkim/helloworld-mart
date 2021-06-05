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
    }
}