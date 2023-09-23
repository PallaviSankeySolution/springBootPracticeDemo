package com.example.studGradle.config

import com.example.studGradle.DIO.EmployeeRepository
//import org.springframework.security.core.userdetails.User
//import org.springframework.security.core.userdetails.UserDetails
//import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service


@Service
class CustomUserDetails(val repo:EmployeeRepository) {//}: UserDetailsService {

//    override fun loadUserByUsername(username: String): UserDetails {
////            println("from loadUserByUsername from CustomUser Details")
////            println("from above ${repo.existsById(username)}")
//        val user = repo.findByUsername(username)
////            println("From CustomUserDetails user: $user and username:${user.get().email} and password ${user.get().password}")
//        return User.builder()
//            .username(user.get().username)
//            .password(user.get().password)
//            .authorities("USER")
//            .build()
//
//    }


}