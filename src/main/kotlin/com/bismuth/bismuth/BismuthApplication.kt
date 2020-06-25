package com.bismuth.bismuth

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaAuditing

@SpringBootApplication
@EnableJpaAuditing
class BismuthApplication

fun main(args: Array<String>) {
	runApplication<BismuthApplication>(*args)
}
