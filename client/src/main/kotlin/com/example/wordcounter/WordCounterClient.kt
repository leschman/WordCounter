package com.example.wordcounter

import feign.Headers
import feign.RequestLine
import com.example.wordcounter.models.Count
import com.example.wordcounter.models.Message

interface WordCounterClient {

    @RequestLine("POST /message")
    @Headers("Content-Type: application/json", "Accept: */*")
    fun sendMessage(message: Message): Count
}