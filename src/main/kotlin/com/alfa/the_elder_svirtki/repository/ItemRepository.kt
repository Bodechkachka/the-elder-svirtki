package com.alfa.the_elder_svirtki.repository

import com.alfa.the_elder_svirtki.model.Item
import org.springframework.data.mongodb.repository.MongoRepository
import java.util.*

interface ItemRepository: MongoRepository<Item, String> {
    fun findByName(name: String): Optional<Item>
}