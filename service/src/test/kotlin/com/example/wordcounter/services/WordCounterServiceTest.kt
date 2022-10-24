package com.example.wordcounter.services

import com.example.wordcounter.models.Message
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.kotlin.inOrder
import org.mockito.kotlin.whenever
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
internal class WordCounterServiceTest {

    @Mock
    lateinit var messageService: MessageService

    @InjectMocks
    lateinit var service: WordCounterService

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
    fun `saveMessageAndGetWordCount happy path, calls expected services`() {
        // given
        val message = Message(123,"hello world")
        whenever(messageService.sumWords()).thenReturn(3L)

        // when
        val result = service.saveMessageAndGetWordCount(message)

        // then
        assertEquals(3L, result)
        val inOrder = inOrder(messageService)
        inOrder.verify(messageService).saveMessage(message, 2)
        inOrder.verify(messageService).sumWords()
    }
}