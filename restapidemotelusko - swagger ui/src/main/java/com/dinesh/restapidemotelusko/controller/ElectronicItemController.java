package com.dinesh.restapidemotelusko.controller;

import com.dinesh.restapidemotelusko.model.ElectronicItem;
import com.dinesh.restapidemotelusko.service.ElectronicItemService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/electronics")
@Tag(name = "Electronic Items", description = "Operations related to electronic items")
public class ElectronicItemController {

    private final ElectronicItemService service;

    @Autowired
    public ElectronicItemController(ElectronicItemService service) {
        this.service = service;

    }

    @GetMapping
    @Operation(summary = "Get all items", description = "Retrieve a list of all electronic items")
    public ResponseEntity<List<ElectronicItem>> getAllItems() {
        return new ResponseEntity<>(service.getAllItems(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get item by ID", description = "Retrieve a specific electronic item by its ID")
    public ResponseEntity<ElectronicItem> getItemById(@PathVariable Long id) {
        ElectronicItem item = service.getItemById(id);
        return new ResponseEntity<>(item, HttpStatus.OK);
    }

    @PostMapping
    @Operation(summary = "Create a new item", description = "Add a new electronic item to the inventory")
    public ResponseEntity<ElectronicItem> createItem(@RequestBody ElectronicItem item) {
        ElectronicItem createdItem = service.createItem(item);
        return new ResponseEntity<>(createdItem, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an item", description = "Update the details of an existing electronic item")
    public ResponseEntity<ElectronicItem> updateItem(@PathVariable Long id, @RequestBody ElectronicItem itemDetails) {
        ElectronicItem updatedItem = service.updateItem(id, itemDetails);
        return new ResponseEntity<>(updatedItem, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete an item", description = "Remove an electronic item from the inventory")
    public ResponseEntity<Void> deleteItem(@PathVariable Long id) {
        service.deleteItem(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
