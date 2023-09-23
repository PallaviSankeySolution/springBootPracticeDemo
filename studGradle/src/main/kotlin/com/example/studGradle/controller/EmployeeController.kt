package com.example.studGradle.controller

//import org.springframework.security.core.GrantedAuthority
//import org.springframework.security.core.authority.SimpleGrantedAuthority
import com.example.studGradle.Modal.Employee
import com.example.studGradle.Modal.Token
import com.example.studGradle.config.CustomUserDetails
import com.example.studGradle.config.JwtTokenFilter
import com.example.studGradle.config.JwtTokenProvider
import com.example.studGradle.service.FileUploadHelper
import com.example.studGradle.service.TokenService
import com.example.studGradle.service.employeeService
import com.example.studGradle.valiadtion.ValidateEmployee
import jakarta.servlet.http.HttpServletRequest
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import kotlin.io.encoding.ExperimentalEncodingApi


@RestController
@RequestMapping("/employee")
class employeeController(private  val EmployeeService: employeeService,
                         private val  fileUploadHelper: FileUploadHelper,
                         private val checkValidation: ValidateEmployee,
                         private val jwtTokenProvider: JwtTokenProvider,
                         private val tokenService :TokenService,
                          private val jwtTokenFilter: JwtTokenFilter,
                          private  val customeuserdetails:CustomUserDetails
) {
    @GetMapping("/login")
    @CrossOrigin
    fun welcome():ResponseEntity<Any>{

        val username = "admin"

      // customeuserdetails.loadUserByUsername(username)
       // val authorities: List<GrantedAuthority> = listOf(SimpleGrantedAuthority("USER")) // Define user authorities here
        val ganeratedToken = jwtTokenProvider.generateToken(username)
        val token = Token(token = ganeratedToken)
        tokenService.saveToken(token) //save token in database
        return ResponseEntity.ok(ganeratedToken)
    }

    @GetMapping("/show")
    @CrossOrigin
    fun getAllEmployees(
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "100") size: Int,
        @RequestParam(defaultValue = "Id") field:String

    ): ResponseEntity<Any> {

            val pageable = PageRequest.of(page, size, Sort.by(field))
            val employees = EmployeeService.getAllEmployees(pageable)
            return ResponseEntity.ok(employees)

    }

    @GetMapping("/logout")
    fun logout(request: HttpServletRequest):ResponseEntity<String>{
        val token = jwtTokenFilter.extractToken(request)

        if (token != null) {
            tokenService.deleteToken(token)
            return  ResponseEntity.ok("lodded out ...")
        }
        return ResponseEntity.ok("token not found")
    }

    @GetMapping("/{id}")
    fun getStudentById(@PathVariable id: Long): Employee {
        return EmployeeService.getStudentById(id)
    }

    @OptIn(ExperimentalEncodingApi::class)

    @PostMapping("/add")
    @CrossOrigin
    fun saveStudent(@RequestBody employee: Employee): ResponseEntity<Any> {
//        println("**********\n"+ request +"/n------------/n")
//        val employee = Employee(
//            id = null,
//            name = request.name,
//            age = request.age,
//            image = request.imagedata.bytes,
//            department = null,
//
//        )
       val result= checkValidation.isValide(employee)
        if(result.isValid) {
            return ResponseEntity.ok().body(EmployeeService.saveStudent(employee))
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result.errors)
        }
    }

    @PatchMapping("/{id}/update-image")
    @CrossOrigin
    fun updateEmployeeImage(
        @PathVariable id: Long,
        @RequestParam("imageFile") imageFile: MultipartFile
    ): ResponseEntity<String> {
        try {
            fileUploadHelper.UploadFile(imageFile)
            EmployeeService.updateEmployeeImage(id, imageFile.bytes)
            return ResponseEntity.ok("Image updated successfully")
        } catch (e: Exception) {
            return ResponseEntity.status(500).body("Failed to update image")
        }
    }

    @PutMapping("/{id}")
    @CrossOrigin
    fun updateStudent(@PathVariable id:Long ,@RequestBody employee: Employee):
            ResponseEntity<Any> {
        return EmployeeService.updateStudent(id,employee)
    }


    @DeleteMapping("/{id}")
    @CrossOrigin
    fun deleteStudent(@PathVariable id: Long):ResponseEntity<Any> {

      return EmployeeService.deleteStudent(id)
    }

    @PostMapping("/file-upload")
    @CrossOrigin
    fun uploadFile(@RequestParam("file") file:MultipartFile):ResponseEntity<Any> {
        println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@")
        println(file.contentType)
        println(file.name)
        println(file.size)
        println(file.originalFilename)

        if (file.isEmpty) {
          return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Request must contain file")
        }else {

            return ResponseEntity.ok("f")
        }
    }


}