package com.helloworld

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class CoreWebApplication

fun main(args: Array<String>) {
    runApplication<CoreWebApplication>(*args)
}
