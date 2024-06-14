package com.cline

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class CLineApplication

fun main(args: Array<String>) {
	runApplication<CLineApplication>(*args)
}
