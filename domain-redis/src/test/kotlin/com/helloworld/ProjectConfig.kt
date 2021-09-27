package com.helloworld

import io.kotest.core.config.AbstractProjectConfig
import io.kotest.core.extensions.Extension
import io.kotest.core.listeners.Listener
import io.kotest.spring.SpringListener

class ProjectConfig : AbstractProjectConfig() {
    override fun listeners(): List<Listener> = listOf(
        SpringListener
    )
    override fun extensions(): List<Extension> = listOf()
}
