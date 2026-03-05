package com.alfa.the_elder_svirtki.controller

import com.alfa.the_elder_svirtki.model.Item
import com.alfa.the_elder_svirtki.service.WarehouseService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/warehouse")
class WarehouseController (
    private val warehouseService: WarehouseService
) {
    @PostMapping("/add")
    fun addItem(
        @RequestParam name: String,
        @RequestParam quantity: Int
    ): Item {
        return warehouseService.addItem(name, quantity)
    }

    @PostMapping("/remove")
    fun removeItem(
        @RequestParam name: String,
        @RequestParam quantity: Int
    ): Item {
        return warehouseService.removeItem(name, quantity)
    }

    @GetMapping("/item")
    fun getItem (
        @RequestParam name: String
    ): Item {
        return warehouseService.getItem(name)
    }

    @GetMapping("/items")
    fun getAllItems(): List<Item> {
        return warehouseService.getAllItems()
    }
}