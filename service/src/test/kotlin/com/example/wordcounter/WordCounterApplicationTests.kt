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

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class WordCounterApplicationTests(@LocalServerPort val port: Int) {

	@Test
	internal fun `receive count when posting message`() {
		val client = Feign.builder()
			.encoder(JacksonEncoder(mapper()))
			.decoder(JacksonDecoder(mapper()))
			.target(WordCounterClient::class.java, "http://localhost:$port")

		assertEquals(2, client.sendMessage(Message(123, "hello world")).count)
	}

	private fun mapper(): ObjectMapper {
		val objectMapper = ObjectMapper()
		objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
		objectMapper.registerKotlinModule()

		return objectMapper
	}
}
