package com.example.studGradle.interceptor

import com.example.studGradle.config.JwtTokenFilter
import com.example.studGradle.config.JwtTokenProvider
import com.example.studGradle.service.TokenService
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
//import lombok.extern.slf4j.Slf4j
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import org.springframework.web.servlet.HandlerInterceptor
import org.springframework.web.servlet.ModelAndView


@Component
class RequestHandlingInterceptor( private val jwtTokenFilter: JwtTokenFilter,
                                  private val jwtTokenProvider :JwtTokenProvider,
                                  private val tokenService:TokenService):HandlerInterceptor {

    override fun preHandle(request: HttpServletRequest,
                           response: HttpServletResponse,
                           handler: Any): Boolean
    {
        println("Intercepted request URL: " + request.getRequestURL() + "  " + handler.toString());
        val url = request.getRequestURI() // This gives you the path portion of the URL

        if (url == "/employee/login" || url == "/employee/add")
        { return true }

       val token = jwtTokenFilter.extractToken(request)// Extract token from request
       if (token != null && jwtTokenProvider.validateToken(token) && tokenService.isTokenExist(token)) {
           return true;
       } else {

           // Set the response status code to 401 Unauthorized
           response.status = HttpStatus.UNAUTHORIZED.value()
           response.writer.write("Unauthorized access. Please provide a valid token.")
           return false
       }

    }

    override fun postHandle(
        request: HttpServletRequest,
        response: HttpServletResponse,
        handler: Any,
        modelAndView: ModelAndView?
    ) {
        println("postHandle @@@@@@@@@@@@@@@@@@@2")
        super.postHandle(request, response, handler, modelAndView)
    }

    override fun afterCompletion(
        request: HttpServletRequest,
        response: HttpServletResponse,
        handler: Any,
        ex: Exception?
    ) {
        println("after complition @@@@@@@@@@@@@@@@@@@")
        super.afterCompletion(request, response, handler, ex)
    }
}