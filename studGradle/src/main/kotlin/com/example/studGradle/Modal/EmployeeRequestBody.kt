package com.example.studGradle.Modal

import com.example.studGradle.Modal.Department
import org.springframework.web.multipart.MultipartFile

class EmployeeRequestBody(
    var name:String,
    var age:Long,
    var imagedata:MultipartFile,
    val department: Department?
) {

}