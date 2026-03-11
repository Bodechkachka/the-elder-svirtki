package com.alfa.the_elder_svirtki.repository

import com.alfa.the_elder_svirtki.model.AuthToken
import org.springframework.data.mongodb.repository.MongoRepository

interface AuthTokenRepository: MongoRepository<AuthToken, String> {
    fun findByToken(name: String): AuthToken?
}