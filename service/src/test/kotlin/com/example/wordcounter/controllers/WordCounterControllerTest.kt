package com.example.wordcounter.controllers

import com.example.wordcounter.models.Message
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class WordCounterControllerTest(@Autowired val controller: WordCounterController) {

    @Test
    fun `simple message test`() {
        val message = Message(1, "message")

        assertEquals(1, controller.receiveMessage(message).count)
    }
}