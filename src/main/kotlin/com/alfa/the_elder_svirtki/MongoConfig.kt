package com.alfa.the_elder_svirtki

import org.springframework.context.annotation.Configuration
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration

@Configuration
class MongoConfig : AbstractMongoClientConfiguration() {

    override fun getDatabaseName(): String {
        return "elder_svirtki_db"
    }
}