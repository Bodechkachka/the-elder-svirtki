package com.alfa.the_elder_svirtki.model

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document("tokens")
data class AuthToken (

    @Id val id: ObjectId = ObjectId(),
    val token: String
)