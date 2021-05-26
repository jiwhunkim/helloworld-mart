package com.helloworld

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class DisplayApplication

fun main(args: Array<String>) {
	runApplication<DisplayApplication>(*args)
}
