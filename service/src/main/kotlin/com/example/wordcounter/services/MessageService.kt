package com.example.wordcounter.services

import com.example.wordcounter.db.models.PersistentMessage
import com.example.wordcounter.db.repositories.MessageRepository
import com.example.wordcounter.models.Message
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.stereotype.Service

@Service
class MessageService(
    @Autowired val messageRepository: MessageRepository
) {
    private val log: Logger = LoggerFactory.getLogger(MessageService::class.java)

    /**
     * persist a message to the database. Ignore if a duplicate message id is received.
     */
    fun saveMessage(message: Message, wordCount: Int) {
        try {
            messageRepository.saveAndFlush(PersistentMessage(wordCount, message.id))
        } catch ( e: DataIntegrityViolationException) {
            // Log but ignore if duplicate messages are received.
            log.info("Rejecting message with duplicate message id: ${message.id}")
        }
    }

    /**
     * get the total of all the words from all recieved messages.
     */
    fun sumWords(): Long {
        var totalWords = 0L
        messageRepository.findAll().forEach {
            totalWords += it.wordCount!!
        }

        return totalWords
    }
}