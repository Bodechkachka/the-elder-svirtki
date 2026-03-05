package com.alfa.the_elder_svirtki.service

import com.alfa.the_elder_svirtki.model.Item
import com.alfa.the_elder_svirtki.repository.ItemRepository
import jakarta.annotation.PostConstruct
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.stereotype.Service

@Service
class WarehouseService (
    private val itemRepository: ItemRepository,
    private val mongoTemplate: MongoTemplate
) {
    fun addItem(name: String, quantity: Int): Item {
        require(quantity > 0) { "Quantity must be positive" }

        val existingItem = itemRepository.findByName(name)
        return if (existingItem.isPresent){
            val item = existingItem.get()
            val updatedItem = item.copy(quantity = item.quantity + quantity)
            itemRepository.save(updatedItem)
        } else {
            val newItem = Item(
                name = name,
                quantity = quantity
            )
            itemRepository.save(newItem)
        }
    }

    fun removeItem(name: String, quantity: Int): Item {
        require(quantity > 0) { "Quantity must be positive" }

        val item = itemRepository.findByName(name)
            .orElseThrow{ RuntimeException("Item not found") }

        if(item.quantity < quantity) {
            throw RuntimeException("Not enough items in stock")
        }
        val updatedItem = item.copy(quantity = item.quantity - quantity)

        return itemRepository.save(updatedItem)
    }

    fun getItem(name: String): Item {
        return itemRepository.findByName(name)
            .orElseThrow{ RuntimeException("Item not found") }
    }

    fun getAllItems(): List<Item>{
        return  itemRepository.findAll()
    }

    @PostConstruct
    fun printDatabaseName(){
        println("=============DATABASE NAME: ${mongoTemplate.db.name}")
    }
}