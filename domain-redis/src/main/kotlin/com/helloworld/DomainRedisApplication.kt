package com.helloworld

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class DomainRedisApplication

fun main(args: Array<String>) {
	runApplication<DomainRedisApplication>(*args)
}
