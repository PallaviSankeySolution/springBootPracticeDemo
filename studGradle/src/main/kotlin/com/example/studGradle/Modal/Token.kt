package com.example.studGradle.Modal

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
class Token (
    @Id
    @GeneratedValue(strategy =  GenerationType.AUTO)
    var Id :Int?=null,
    val token:String


){
}

