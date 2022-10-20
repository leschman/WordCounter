package com.example.wordcounter.services

import com.example.wordcounter.models.Message
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
internal class WordCounterServiceTest(@Autowired val service: WordCounterService) {

    @Test
    fun `count empty message, count 0`() {
        // given
        val message = Message(1, "")

        // when
        val result = service.countWords(message)

        // then
        assertEquals(0, result)
    }

    @Test
    fun `count one word message, count 1`() {
        // given
        val message = Message(123, "message")

        // when
        val result = service.countWords(message)

        // then
        assertEquals(1, result)
    }

    @Test
    fun `count two word message, count 2`() {
        // given
        val message = Message(123, "hello world")

        // when
        val result = service.countWords(message)

        // then
        assertEquals(2, result)
    }

    @Test
    fun `count large message with special characters, count 24`() {
        // given
        val text = """
                @Test
                fun `count two word message, count 2`() {
                    // given
                    val message = Message(123, "hello world")

                    // when
                    val result = service.countWords(message)

                    assertEquals(2, result)
                }
        """.trimIndent()

        val message = Message(123, text)

        // when
        val result = service.countWords(message)

        // then
        assertEquals(24, result)
    }
}