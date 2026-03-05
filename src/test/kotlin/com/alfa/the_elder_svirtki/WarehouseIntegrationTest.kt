package com.alfa.the_elder_svirtki

import com.alfa.the_elder_svirtki.repository.ItemRepository
import com.alfa.the_elder_svirtki.service.WarehouseService
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.assertEquals
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.context.DynamicPropertySource
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers
import org.testcontainers.mongodb.MongoDBContainer

@SpringBootTest
@ActiveProfiles("test")
class WarehouseIntegrationTest {

    @Autowired
    lateinit var warehouseService: WarehouseService

    @Autowired
    lateinit var itemRepository: ItemRepository

    @AfterEach
    fun cleanup() {
        itemRepository.deleteAll()
    }

    @Test
    fun `should add and retrieve item`() {

        warehouseService.addItem("TestSvirtok", 5)

        val item = warehouseService.getItem("TestSvirtok")

        assertEquals(5, item.quantity)
    }

    @Test
    fun `should increase quantity when adding same item`() {

        warehouseService.addItem("TestChemodan", 5)
        warehouseService.addItem("TestChemodan", 3)

        val item = warehouseService.getItem("TestChemodan")

        assertEquals(8, item.quantity)
    }

    @Test
    fun `should decrease quantity when writing off item`() {

        warehouseService.addItem("TestBarsetka", 10)

        warehouseService.removeItem("TestBarsetka", 4)

        val item = warehouseService.getItem("TestBarsetka")

        assertEquals(6, item.quantity)
    }

    @Test
    fun `should return correct stock for multiple items`() {

        warehouseService.addItem("TestSvirtok", 5)
        warehouseService.addItem("TestChemodan", 2)

        val items = warehouseService.getAllItems()

        assertEquals(2, items.size)
    }

    @Test
    fun `should throw exception when item not found`() {

        Assertions.assertThrows(RuntimeException::class.java) {
            warehouseService.getItem("Unknown")
        }
    }
}