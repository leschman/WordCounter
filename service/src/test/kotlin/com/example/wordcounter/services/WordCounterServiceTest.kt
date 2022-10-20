package com.example.wordcounter.services

import com.example.wordcounter.models.Message
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
internal class WordCounterServiceTest(@Autowired val service: WordCounterService) {

    @Test
    fun `getWordCount empty text, count 0`() {
        // given
        val text = ""

        // when
        val result = service.getWordCount(text)

        // then
        assertEquals(0, result)
    }

    @Test
    fun `getWordCount one word text, count 1`() {
        // given
        val text = "text"

        // when
        val result = service.getWordCount(text)

        // then
        assertEquals(1, result)
    }

    @Test
    fun `getWordCount two word text, count 2`() {
        // given
        val text = "hello world"

        // when
        val result = service.getWordCount(text)

        // then
        assertEquals(2, result)
    }

    @Test
    fun `getWordCount large text with special characters, count 24`() {
        // given
        val text = """
                @Test
                fun `getWordCount two word text, count 2`() {
                    // given
                    val text = text(123, "hello world")

                    // when
                    val result = service.countWords(text)

                    assertEquals(2, result)
                }
        """.trimIndent()

        // when
        val result = service.getWordCount(text)

        // then
        assertEquals(24, result)
    }
    
    @Test
    fun `countWordsInMessage happy path, converts count to Long`() {
        // given
        val message = Message(123,"hello world")

        // when
        val result = service.countWordsInMessage(message)

        // then
        assertEquals(2L, result)
    }
}