package com.example.wordcounter.controllers


import com.example.wordcounter.models.Count
import com.example.wordcounter.models.Message
import com.example.wordcounter.services.MessageService
import com.example.wordcounter.services.WordCounterService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
class WordCounterController(
    @Autowired val wordCounterService: WordCounterService
) {
    private val log: Logger = LoggerFactory.getLogger(WordCounterController::class.java)

    /**
     * Receive a message, persist it to the database and return a count of all words persisted so far. Log, but ignore messages with duplicate IDs.
     */
    @PostMapping("/message")
    fun receiveMessage(@Valid @RequestBody  message: Message): Count {
        log.info("Received message with id: ${message.id}")
        return Count(wordCounterService.saveMessageAndGetWordCount(message))
    }
}