package com.example.wordcounter

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import feign.Feign
import feign.jackson.JacksonDecoder
import feign.jackson.JacksonEncoder
import com.example.wordcounter.models.Message
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.server.LocalServerPort
import org.springframework.test.annotation.DirtiesContext
import org.springframework.test.context.ActiveProfiles

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@ActiveProfiles("test")
class WordCounterApplicationTests(@LocalServerPort val port: Int) {

	val client: WordCounterClient = Feign.builder()
		.encoder(JacksonEncoder(mapper()))
		.decoder(JacksonDecoder(mapper()))
		.target(WordCounterClient::class.java, "http://localhost:$port")

	@Test
	internal fun `receive count when posting message`() {
		// given
		val message = Message(123, "hello world")

		// when
		val result = client.sendMessage(message)

		// then
		assertEquals(2, result.count)
	}
	@Test
	internal fun `receive sum of all messages`() {
		// given

		val message1 = Message(123, "hello world")
		val message2 = Message(124, "this is message 2")

		// when
		client.sendMessage(message1)
		val result = client.sendMessage(message2)

		// then
		assertEquals(6, result.count)
	}

	@Test
	internal fun `duplicate message ids are rejected`() {
		// given

		val message1 = Message(123, "hello world")
		val message2 = Message(123, "this is message 2")

		// when
		client.sendMessage(message1)
		val result = client.sendMessage(message2)

		// then
		assertEquals(2, result.count)
	}

	private fun mapper(): ObjectMapper {
		val objectMapper = ObjectMapper()
		objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
		objectMapper.registerKotlinModule()

		return objectMapper
	}
}
