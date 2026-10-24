package com.example.towatch.api.item.exception;

public class ItemNotFoundException extends RuntimeException {
    public ItemNotFoundException() {
        super("Item not found");
    }
}
