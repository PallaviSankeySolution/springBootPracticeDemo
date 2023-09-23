package com.example.studGradle.valiadtion

import com.example.studGradle.Modal.Employee
import org.springframework.stereotype.Service

@Service
class ValidateEmployee ( val isValid:Boolean =false,
                         val errors: List<String> = emptyList()){


    fun isValide(employee: Employee): ValidateEmployee {
            val errors = mutableListOf<String>()

            if (!isNameValid(employee.name)) {
                errors.add("Name is invalid")
            }

            if (!isAgeValid(employee.age)) {
                errors.add("age must be between 18 to 60")
            }
            if(!(employee.department?.let { isValidDepartName(it.departmentName) })!!)   {
                errors.add("department Name is invalid")
            }

            return ValidateEmployee(errors.isEmpty(), errors)
    }

    private fun isNameValid(name: String): Boolean {
        // Add name validation logic here
        return name.isNotBlank() && name.length <= 100
    }

    private fun isAgeValid(age: Long): Boolean {
        // Add age validation logic here
        return age >= 18 && age <= 60
    }
    private  fun isValidDepartName(departmentName:String):Boolean{
        return departmentName.isNotBlank() && departmentName.length <= 100
    }
}