package com.example.wordcounter

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class WordCounterApplication

fun main(args: Array<String>) {
	runApplication<WordCounterApplication>(*args)
}
