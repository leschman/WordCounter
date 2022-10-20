package com.example.wordcounter.controllers


import com.example.wordcounter.models.Count
import com.example.wordcounter.models.Message
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
class WordCounterController {

    @PostMapping("/message")
    fun receiveMessage(@Valid @RequestBody  message: Message): Count {
        return Count(message.id)
    }
}