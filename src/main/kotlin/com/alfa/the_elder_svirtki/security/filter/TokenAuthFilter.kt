package com.alfa.the_elder_svirtki.security.filter

import com.alfa.the_elder_svirtki.repository.AuthTokenRepository
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

@Component
class TokenAuthFilter (
    private val authTokenRepository: AuthTokenRepository
) : OncePerRequestFilter() {
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val header = request.getHeader("Authorization")

        if (header != null && header.startsWith("Bearer ")) {
            val token = header.substring(7)
            val authToken = authTokenRepository.findByToken(token)
            if (authToken != null) {
                val authentication = UsernamePasswordAuthenticationToken(
                    token,
                    null,
                    emptyList()
                )
                SecurityContextHolder.getContext().authentication = authentication
            }
        }

        filterChain.doFilter(request, response)
    }
}