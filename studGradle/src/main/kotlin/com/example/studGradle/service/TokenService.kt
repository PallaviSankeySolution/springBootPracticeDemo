package com.example.studGradle.service

import com.example.studGradle.DIO.TokenRepository
import com.example.studGradle.Modal.Token
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.ControllerAdvice

@ControllerAdvice
@Service
class TokenService(private  val tokenRepository :TokenRepository) {

    fun deleteToken(token :String){
       return  tokenRepository.deleteToken(token)
    }

    fun isTokenExist(token:String):Boolean{
       return  tokenRepository.existsByToken(token)
    }

    fun saveToken(token:Token):Token{
        return tokenRepository.save(token)
    }
}