
import com.example.studGradle.config.CustomAuthenticationEntryPoint
import com.example.studGradle.config.JwtTokenFilter
import com.example.studGradle.config.JwtTokenProvider
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
//import org.springframework.security.config.annotation.web.builders.HttpSecurity
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
//import org.springframework.security.config.http.SessionCreationPolicy
//import org.springframework.security.web.SecurityFilterChain
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter


@Configuration
//@EnableWebSecurity
class SecurityConfig(private val jwtTokenProvider: JwtTokenProvider,private  val jwtfilter :JwtTokenFilter) {

    @Bean
    fun jwtFilter() = JwtTokenFilter(jwtTokenProvider)

    @Bean
    fun customAuthenticationEntryPoint() = CustomAuthenticationEntryPoint()

//    @Bean
//    fun configureHttpSecurity(http: HttpSecurity): SecurityFilterChain {
//        http
//            .csrf { it.disable() } // Disable CSRF protection
//            .cors {it.disable()}
//            .authorizeHttpRequests{ it.requestMatchers("/employee/**").permitAll()}
//            .exceptionHandling { exceptionHandling ->
//                println("security config file **************")
//                exceptionHandling.authenticationEntryPoint(customAuthenticationEntryPoint())
//            }
//            .addFilterBefore(jwtFilter(), UsernamePasswordAuthenticationFilter::class.java)
//
//        return http.build()
//
//    }
//@Bean
//fun filterChain(http: HttpSecurity): SecurityFilterChain {
//    http
//        .csrf{it.disable()}
//        .exceptionHandling { it.authenticationEntryPoint(customAuthenticationEntryPoint()) }   // for handlig exception
//        .sessionManagement{ it.sessionCreationPolicy(SessionCreationPolicy.STATELESS) }  // for using jwt
//        .authorizeHttpRequests { it.requestMatchers("/employee/**").permitAll() }
//       // .authorizeHttpRequests{ it.requestMatchers("/api/private/**").hasAuthority("USER") }
//        //.logout { it.logoutUrl("/logout").logoutSuccessUrl("/login?logout=true").permitAll(); }
//        .httpBasic{}
//        .addFilterBefore(jwtfilter, UsernamePasswordAuthenticationFilter::class.java)
//
//    return  http.build()
//}

}

