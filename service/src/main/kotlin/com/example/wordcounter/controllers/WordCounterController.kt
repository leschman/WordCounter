package com.example.wordcounter.controllers


import com.example.wordcounter.models.Count
import com.example.wordcounter.models.Message
import com.example.wordcounter.services.WordCounterService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
class WordCounterController(
    @Autowired val wordCounterService: WordCounterService
) {

    @PostMapping("/message")
    fun receiveMessage(@Valid @RequestBody  message: Message): Count {
        return Count(wordCounterService.countWordsInMessage(message))
    }
}