package com.helloworld

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import java.util.*
import javax.annotation.PostConstruct


@SpringBootApplication
class DisplayApplication

fun main(args: Array<String>) {
	runApplication<DisplayApplication>(*args)
}
