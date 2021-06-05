package com.helloworld

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class DomainMapperApplication

fun main(args: Array<String>) {
    runApplication<DomainMapperApplication>(*args)
}
