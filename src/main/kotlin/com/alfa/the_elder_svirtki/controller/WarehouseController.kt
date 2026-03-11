package com.alfa.the_elder_svirtki.controller

import com.alfa.the_elder_svirtki.dto.ItemRequest
import com.alfa.the_elder_svirtki.model.Item
import com.alfa.the_elder_svirtki.service.WarehouseService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/warehouse")
class WarehouseController (
    private val warehouseService: WarehouseService
) {
    @PostMapping("/add")
    fun addItem(
        @RequestBody request: ItemRequest
    ): Item {
        return warehouseService.addItem(request.name, request.quantity)
    }

    @PostMapping("/remove")
    fun removeItem(
        @RequestBody request: ItemRequest
    ): Item {
        return warehouseService.removeItem(request.name, request.quantity)
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