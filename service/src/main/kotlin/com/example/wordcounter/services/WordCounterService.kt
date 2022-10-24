package com.example.wordcounter.services

import com.example.wordcounter.models.Message
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class WordCounterService(
    @Autowired val messageService: MessageService
) {
    val whiteSpaceRegex = "\\s+".toRegex()
    val nonWordRegex = "\\W".toRegex()

    /**
     * Persist a message to the database and return the count of all words recieved so far.
     */
    fun saveMessageAndGetWordCount(message: Message): Long {
        val messageWordCount = getWordCount(message.message)
        messageService.saveMessage(message, messageWordCount)

        return messageService.sumWords()
    }

    /**
     * Count the words in the text.
     */
    fun getWordCount(text: String): Int {
         return text
             .replace(nonWordRegex, " ")
             .trim()
             .split(whiteSpaceRegex)
             .filter { word -> word.isNotEmpty() }
             .size
    }
}