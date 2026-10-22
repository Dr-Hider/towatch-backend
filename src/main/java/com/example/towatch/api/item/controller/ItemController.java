package com.example.towatch.api.item.controller;

import com.example.towatch.api.item.dto.ItemRequest;
import com.example.towatch.api.item.dto.ItemResponse;
import com.example.towatch.api.item.service.ItemService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/items")
public class ItemController {
    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping
    public ResponseEntity<List<ItemResponse>> getAllItems(Authentication authentication) {
        Long userId = Long.valueOf(authentication.getName());

        List<ItemResponse> response = itemService.getAll(userId);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping()
    public ResponseEntity<ItemResponse> createItem(
            @Valid @RequestBody ItemRequest request,
            Authentication authentication
    ) {
        Long userId = Long.valueOf(authentication.getName());

        ItemResponse response = itemService.createItem(request, userId);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ItemResponse> updateItem(
            @PathVariable Long id,
            @Valid @RequestBody ItemRequest request,
            Authentication authentication
    ) {
        Long userId = Long.valueOf(authentication.getName());

        ItemResponse response = itemService.updateItem(id, request, userId);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity.BodyBuilder deleteItem(
            @PathVariable Long id,
            Authentication authentication
    ) {
        Long userId = Long.valueOf(authentication.getName());

        itemService.deleteItem(id, userId);

        return ResponseEntity.status(HttpStatus.OK);
    }
}
