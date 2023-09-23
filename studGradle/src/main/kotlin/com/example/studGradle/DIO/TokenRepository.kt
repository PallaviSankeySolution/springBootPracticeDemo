package com.example.studGradle.DIO

import com.example.studGradle.Modal.Token
import jakarta.transaction.Transactional
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository


@Repository
interface TokenRepository:JpaRepository<Token, Int> {
    @Transactional
    @Modifying
    @Query("DELETE FROM Token t WHERE t.token = :token")
    fun deleteToken(token: String?)


    @Query("SELECT CASE WHEN COUNT(t) > 0 THEN true ELSE false END FROM Token t WHERE t.token = :token")
    fun existsByToken(token: String): Boolean
}