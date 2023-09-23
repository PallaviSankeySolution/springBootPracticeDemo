package com.example.studGradle.service

import org.springframework.stereotype.Component
import org.springframework.web.multipart.MultipartFile
import java.io.File
import java.nio.file.Files
import java.nio.file.Paths
import java.nio.file.StandardCopyOption

@Component
class FileUploadHelper {
    val UPLOAD_DIR: String = "C:\\Users\\Admin\\Downloads\\studGradle\\studGradle\\src\\main\\" +
            "resources\\static\\images"
    fun UploadFile(file: MultipartFile):Boolean  {

       Files.copy(file.inputStream, Paths.get(UPLOAD_DIR+ File.separator +file.originalFilename) ,StandardCopyOption.REPLACE_EXISTING)
        return true
    }
}