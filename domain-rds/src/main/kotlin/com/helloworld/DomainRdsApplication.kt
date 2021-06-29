package com.helloworld

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class DomainRdsApplication

fun main(args: Array<String>) {
    runApplication<DomainRdsApplication>(*args)
}
