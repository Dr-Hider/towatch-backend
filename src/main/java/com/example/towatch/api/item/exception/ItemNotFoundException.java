package com.example.towatch.api.item.exception;

public class ItemNotFoundException extends RuntimeException {
    public ItemNotFoundException(Long id) {
        super("Item not found with id " + id);
    }
}
