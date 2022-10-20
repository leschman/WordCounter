package com.example.wordcounter.services

import com.example.wordcounter.models.Message
import org.springframework.stereotype.Service

@Service
class WordCounterService {
    val whiteSpaceRegex = "\\s+".toRegex()
    val nonWordRegex = "\\W".toRegex()

    fun countWordsInMessage(message: Message): Long {
        return getWordCount(message.message).toLong()
    }

    fun getWordCount(text: String): Int {
         return text
             .replace(nonWordRegex, " ")
             .trim()
             .split(whiteSpaceRegex)
             .filter { word -> word.isNotEmpty() }
             .size
    }
}