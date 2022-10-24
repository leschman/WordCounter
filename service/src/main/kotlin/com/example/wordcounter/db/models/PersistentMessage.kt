package com.example.wordcounter.db.models

import javax.persistence.*

@Entity
@Table(name = "messages")
open class PersistentMessage() {

    @get: Column(name = "word_count", nullable = false)
    open var wordCount: Int? = null

    @get: Column(name = "message_id", nullable = false, unique = true)
    open var messageId: Int? = null

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @get: Column(name = "id", nullable = true)
    open var id: Long? = null

    constructor(wordCount: Int?, messageId: Int?) : this() {
        this.wordCount = wordCount
        this.messageId = messageId
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || javaClass != other.javaClass) return false
        val that = other as PersistentMessage
        if (messageId != that.messageId) return false
        return true
    }

    override fun hashCode(): Int {
        return if (messageId != null)
            messageId.hashCode()
        else 0
    }
}