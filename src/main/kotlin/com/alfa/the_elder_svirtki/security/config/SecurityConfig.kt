package com.alfa.the_elder_svirtki.security.config

import com.alfa.the_elder_svirtki.security.filter.TokenAuthFilter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@Configuration
@EnableWebSecurity
class SecurityConfig(
    private val tokenAuthFilter: TokenAuthFilter
) {
    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain{
        http
            .csrf { it.disable() }
            .authorizeHttpRequests { it.anyRequest().authenticated() }
            .addFilterBefore(tokenAuthFilter, UsernamePasswordAuthenticationFilter::class.java)
        return http.build()
    }
}