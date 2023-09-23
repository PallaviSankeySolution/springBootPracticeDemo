package com.example.studGradle.DIO

import com.example.studGradle.Modal.Employee
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface EmployeeRepository:JpaRepository<Employee,Long> {

    fun findByUsername(username:String):Optional<Employee>
}