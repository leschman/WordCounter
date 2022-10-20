package com.example.wordcounter.services

import com.example.wordcounter.models.Message
import org.springframework.stereotype.Service

@Service
class WordCounterService {
    val whiteSpaceRegex = "\\s+".toRegex()
    val nonWordRegex = "\\W".toRegex()

    fun countWords(message: Message): Int {
         return message.message
             .replace(nonWordRegex, " ")
             .trim()
             .split(whiteSpaceRegex)
             .filter { word -> word.isNotEmpty() }
             .size
    }
}