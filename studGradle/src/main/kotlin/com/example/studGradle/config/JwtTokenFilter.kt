package com.example.studGradle.config
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
//import org.springframework.security.core.Authentication
//import org.springframework.security.core.context.SecurityContextHolder
import jakarta.servlet.http.HttpServletRequest
import org.springframework.stereotype.Component

@Component
class JwtTokenFilter(private val jwtTokenProvider: JwtTokenProvider) //: OncePerRequestFilter()
{
//    override fun doFilterInternal(
//
//        request: HttpServletRequest,
//        response: HttpServletResponse,
//        filterChain: FilterChain
//    ) {
//        println("/n into jwt filter ###############################################/n")
//        val token = extractToken(request)
//        if (token != null && jwtTokenProvider.validateToken(token)) {
//            val username = jwtTokenProvider.extractUsername(token)
//           // val authorities = jwtTokenProvider.extractAuthorities(token)
////
////            val authentication: Authentication = UsernamePasswordAuthenticationToken(username, null, authorities)
////            SecurityContextHolder.getContext().authentication = authentication
//        }
//        filterChain.doFilter(request, response)
//    }

     fun extractToken(request: HttpServletRequest): String? {
        val header = request.getHeader("Authorization")
        return if (header != null && header.startsWith("Bearer ")) {
            header.substring(7)
        } else null
    }
}
