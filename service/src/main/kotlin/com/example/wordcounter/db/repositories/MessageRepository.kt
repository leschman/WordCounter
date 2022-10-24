package com.example.wordcounter.db.repositories

import com.example.wordcounter.db.models.PersistentMessage
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface MessageRepository : JpaRepository<PersistentMessage, Int> {
}