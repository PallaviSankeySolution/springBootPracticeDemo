package com.example.studGradle.Modal

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
class Department(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var Id :Long,
    var departmentName:String,
    var departmentCity: String
) {
}