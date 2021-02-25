package com.rate

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling

@EnableScheduling
@SpringBootApplication
class RateApplication

fun main(args: Array<String>) {
  runApplication<RateApplication>(*args)
}
